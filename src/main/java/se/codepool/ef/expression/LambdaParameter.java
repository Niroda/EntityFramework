package se.codepool.ef.expression;

class LambdaParameter {
	
	public final int index;
	public final Class<?> type;
	
	LambdaParameter(int index, Class<?> type) {
		this.index = index;
		this.type = type;
	}
}
