package se.codepool.ef;

import java.util.function.Predicate;

import se.codepool.ef.expression.ExpressionTree;
import se.codepool.ef.expression.LambdaParser;
import se.codepool.entities.User;

public class App {

	static int id = 11;
	public static void main(String[] args)  {
		
		LambdaParser.prepare();
		StringBuilder sb = new StringBuilder();
		Predicate<User> str = x ->  x.getId() == DbContext.id && id == sb.length();
		
		ExpressionTree tree = LambdaParser.getLambdaCode(str);
		
		System.out.println(tree);
		
	}
}