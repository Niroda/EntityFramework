package se.codepool.ef.expression;

import static java.nio.file.Files.createTempDirectory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

public class LambdaParser {
	/**
	 * holds dump proxy folder path
	 */
	private static final String DUMP_FOLDER = "jdk.internal.lambda.dumpProxyClasses";
	/**
	 * reads lambda class loader path
	 */
	private static URLClassLoader LAMBDA_CLASS_LOADER;
	/**
	 * stores occured error during creation of the class loader url
	 */
	private static String CREATION_ERROR;
	/**
	 * A {@link Printer} that prints a disassembled view of the classes it visits.
	 * 
	 * @author Eric Bruneton
	 */
	private static Printer printer = new Textifier();
	/**
	 * A {@link jdk.internal.org.objectweb.asm.MethodVisitor MethodVisitor} that
	 * prints the methods it visits with a {@link Printer}
	 * 
	 * @author Eric Bruneton
	 */
	private static TraceMethodVisitor mp = new TraceMethodVisitor(printer);

	public static void prepare() {
		// get temp folder path
		String folderPath = System.getProperty(DUMP_FOLDER);
		// if it's null, create a new temp folder
		if (System.getProperty(DUMP_FOLDER) == null) {
			try {
				System.setProperty(DUMP_FOLDER, createTempDirectory("lambda").toString());
				folderPath = System.getProperty(DUMP_FOLDER);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		if (folderPath == null) {
			// set error message in case the temp folder is null
			CREATION_ERROR = "Ensure that the '" + DUMP_FOLDER + "' system property is properly set.";
			LAMBDA_CLASS_LOADER = null;
		} else {
			// read the temp folder
			File folder = new File(folderPath);
			// in case File object is not a valid path to a folder, set error message
			if (!folder.isDirectory()) {
				CREATION_ERROR = "Ensure that the '" + DUMP_FOLDER + "' system property is properly set (" + folderPath
						+ " does not exist).";
				LAMBDA_CLASS_LOADER = null;
			} else {
				URL folderURL;
				try {
					folderURL = folder.toURI().toURL();
				} catch (MalformedURLException mue) {
					throw new RuntimeException(mue);
				}

				CREATION_ERROR = null;
				LAMBDA_CLASS_LOADER = new URLClassLoader(new URL[] { folderURL });
			}
		}
		if (CREATION_ERROR != null)
			throw new RuntimeException(CREATION_ERROR);
	}

	public static ExpressionTree getLambdaCode(Object lambda) {
		try {
			String path = getLambdaClassPath(lambda.getClass());
			ExpressionTree tree = getExpressionTree(path);
			String lambdaClassPath = tree.getInvokedLambda().lambdaClassPath;
			Class<?> clazz = Class.forName(lambdaClassPath);
			path = getLambdaClassPath(clazz);
			tree.parseLambdaBody(path);
			return tree;
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	static String insnToString(AbstractInsnNode insn) {
		insn.accept(mp);
		StringWriter sw = new StringWriter();
		printer.print(new PrintWriter(sw));
		printer.getText().clear();
		return sw.toString();
	}

	static ClassNode getClassNode(String path) {
		try {
			InputStream in = getResourceAsStream(LAMBDA_CLASS_LOADER, path);
			ClassReader reader = new ClassReader(in);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, 0);
			return classNode;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static String getLambdaClassPath(Class<?> lambdaType) {
		String className = lambdaType.getName();
		int lastIndex = className.lastIndexOf('/');
		className = className.substring(0, lastIndex > -1 ? lastIndex : className.length());
		return replaceDotsWithSlashes(className);
	}

	private static String replaceDotsWithSlashes(String className) {
		return className.replace('.', '/') + ".class";
	}

	private static ExpressionTree getExpressionTree(String path) {
		ClassNode classNode = getClassNode(path);
		return new ExpressionTree(classNode);
	}

	private static InputStream getResourceAsStream(ClassLoader classLoader, String classPath) {
		return classLoader.getResourceAsStream(classPath);
	}

}
