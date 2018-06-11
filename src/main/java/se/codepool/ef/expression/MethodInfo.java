package se.codepool.ef.expression;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;


class MethodInfo {
	
	public final LambdaParameter[] parameters;
	
	public final Class<?> returnType;
	
	public final String wrapperClass;
	
	public final InvocationType invokcationType;
	
	private MethodInfo(LambdaParameter[] parameters, Class<?> returnType, String wrraper, InvocationType inType) {
		this.parameters = parameters;
		this.wrapperClass = wrraper;
		this.returnType = returnType;
		this.invokcationType = inType;
	}
	
	static class InvokedLambdaClass {
		public final String lambdaClassPath;
		public final String lambdaMethodName;
		private LambdaParameter[] parameters;
		private Class<?> returnType;
		InvokedLambdaClass(String className, String methodName, String byteCodeLine) {
			this.lambdaClassPath = className;
			this.lambdaMethodName = methodName;
			this.parseParameters(byteCodeLine);
		}
		
		public LambdaParameter[] getParameters() {
			return this.parameters;
		}
		
		public Class<?> getReturnType() {
			return this.returnType;
		}
		
		private void parseParameters(String byteCodeLine) {
			final String regex = "\\((.*?)\\)";
			final Pattern pattern = Pattern.compile(regex);
			final Matcher matcher = pattern.matcher(byteCodeLine);
			String found = null;
			while (matcher.find()) {
			    found = matcher.group(1);
			}
			this.parameters = TypeInspector.getLambdaParameter(found);
			this.returnType = TypeInspector.getMethodReturnType(byteCodeLine);
		}
	}
	
	

	static InvokedLambdaClass getInvokedLambdaClass(MethodNode methodNode) {
		InvokedLambdaClass invokedLambda = null;
		@SuppressWarnings("unchecked")
		Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
		while(iterator.hasNext()) {
			invokedLambda = parseLambdaClass(iterator.next());
			if(invokedLambda != null)
				return invokedLambda;
		}
		throw new RuntimeException("Couldn't find invoked lambda.");
	}
	
	private static InvokedLambdaClass parseLambdaClass(AbstractInsnNode node) {
		String op = LambdaParser.insnToString(node).trim();
		System.out.println("METHOD INFO 89: " + op);
		if (op.startsWith("INVOKESTATIC")) {
			String lambdaClassName = "lambda$" + op.split("\\$")[1].split(" ")[0];
			String classPath = op.substring(13).split(".lambda")[0].replaceAll("/", ".");
			return new InvokedLambdaClass(classPath, lambdaClassName, op);
		}
		return null;
	}
}
