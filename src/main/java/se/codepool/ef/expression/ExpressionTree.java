package se.codepool.ef.expression;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import se.codepool.ef.expression.MethodInfo.InvokedLambdaClass;

public class ExpressionTree {
	
	private FunctionalMethod _functionalMethod;
	private InvokedLambdaClass invokedLambda;
	
	ExpressionTree(ClassNode classNode) {
		this.parseFunctionalInterface(classNode);
	}
	

	static class Code {
		List<String> codes = new ArrayList<>();
		private Code() {}
		private void addLine(String line) {
			this.codes.add(line);
		}
	}
	
	/**
	 * @return the lambdaClassName
	 */
	public InvokedLambdaClass getInvokedLambda() {
		return invokedLambda;
	}
	
	void parseLambdaBody(String path) {
		ClassNode classNode = LambdaParser.getClassNode(path);
		@SuppressWarnings("unchecked")
		Optional<MethodNode> method = ((List<MethodNode>)classNode.methods)
										.stream()
										.filter(x -> 
											x.name.equals(this.invokedLambda.lambdaMethodName)
										).findFirst();
		
		
		if(method.isPresent()) {
			Code code = new Code();
			AbstractInsnNode[] nodes = method.get().instructions.toArray();
			for(int i = 0; i < nodes.length; i++) {
				String op = LambdaParser.insnToString(nodes[i]).trim();
				//if(!op.replaceAll("L[0-9]+", "").isEmpty() && !op.toLowerCase().startsWith("linenumber")) {
					System.out.println("LINE 55: " + op);
					if(op.startsWith(InvocationType.GETSTATIC.toString())) {
						String classPath = op.split(" ")[1];
						String[] classPathArr = classPath.split("\\.");
						String fieldName = classPathArr[1];
						try {
							String className = classPathArr[0].replaceAll("\\/", ".");
							Field field = Class.forName(className).getDeclaredField(fieldName);
							field.setAccessible(true);
							System.out.println("STATIC FIELD VALUE: " + field.get(null));
						} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					OpcodeTransformer transformed = OpcodeTransformer.parseOpcode(op);
					code.addLine(transformed.correspondingCode(op));
				//}
			}
			System.out.println(code.codes);
		}
	}

	void parseOpcode(String opCode) {
		opCode = opCode.trim();
		if(opCode.startsWith("ALOAD")) {
			
		} else if(opCode.startsWith("INVOKEVIRTUAL")) {
			
		} else if(opCode.startsWith("LDC")) {
			
		}
	}
	
	private void parseFunctionalInterface(ClassNode classNode) {
		if(classNode.interfaces != null && classNode.interfaces.size() > 0) {
			this._functionalMethod = TypeInspector.getFunctionalInterface(classNode.interfaces.get(0));

			@SuppressWarnings("unchecked")
			Optional<MethodNode> method = ((List<MethodNode>)classNode.methods)
											.stream()
											.filter(x -> 
												x.name.equals(this._functionalMethod.getMethodName())
											).findFirst();
			if(method.isPresent()) {
				this.invokedLambda = MethodInfo.getInvokedLambdaClass(method.get());
			}
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		LambdaParameter[] params = this.invokedLambda.getParameters();
		for(int i = 0;i < params.length; i++) {
			if(i == 0) str += "(";
			str += params[i].type.getSimpleName() + " p" + params[i].index;
			str += (i == params.length - 1) ? ")" : ", ";
		}
		str += " -> ";
		return str;
	}

	 
}
