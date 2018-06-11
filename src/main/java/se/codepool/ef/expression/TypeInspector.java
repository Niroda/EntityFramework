package se.codepool.ef.expression;

class TypeInspector {

	static FunctionalMethod getFunctionalInterface(Object type) {
		if (type == null)
			throw new NullPointerException("Functional type can't be null.");
		if (type instanceof String) {
			String typeName = (String) type;
			switch (typeName.trim()) {
				case "java/util/function/Predicate":
					return FunctionalMethod.PREDICATE;
				case "java/util/function/Function":
					return FunctionalMethod.FUNCTION;
				case "java/util/function/Consumer":
					return FunctionalMethod.CONSUMER;
				case "java/util/function/Supplier":
					return FunctionalMethod.SUPPLIER;
			}
		}
		throw new IllegalArgumentException(type.getClass().getName() + " is not supported");
	}

	static Class<?> getMethodReturnType(String byteCodeLine) {
		String returnType = byteCodeLine.split("\\)")[1];
		if(returnType.contains("/"))
			returnType = returnType.replaceAll("/", ".");
		return getReturnType(returnType);
	}

	static LambdaParameter[] getLambdaParameter(String parameters) {
		String[] splitedParameters = parameters.split(";");
		LambdaParameter[] params = new LambdaParameter[splitedParameters.length];
		for (int i = 0; i < params.length; i++) {
			params[i] = new LambdaParameter(i, getParameterType(splitedParameters[i]));
		}
		return params;
	}

	static Class<?> getReturnType(String type) {
		switch (type) {
			case "Z": return Boolean.class;
			case "V": return Void.class;
			case "B": return Byte.class;
			case "S": return Short.class;
			case "I": return Integer.class;
			case "J": return Long.class;
			case "F": return Float.class;
			case "D": return Double.class;
			case "C": return Character.class;
		}
		try {
			if (type.startsWith("L")) {
				return Class.forName(type.substring(1));
			} else if (type.startsWith("[L")) {
				return Class.forName(type);
			}
			throw new RuntimeException(type + " can't be resolved to a type.");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static Class<?> getParameterType(String param) {
		try {
			System.out.println("PARAM: " + param);
			param = param.substring(1).replaceAll("/", ".");
			return Class.forName(param);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
