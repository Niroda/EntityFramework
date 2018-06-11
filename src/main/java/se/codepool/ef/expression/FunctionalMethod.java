package se.codepool.ef.expression;

enum FunctionalMethod {
	
	PREDICATE("test"), FUNCTION("apply"), CONSUMER("accept"), SUPPLIER("get");
	
	private final String functionName;
	private FunctionalMethod(String functionName) {
		this.functionName = functionName;
	}
	
	public boolean isFunctionalMethod(String functionName) {
		return this.functionName.equals(functionName);
	}
	
	public String getMethodName() {
		return this.functionName;
	}
}
