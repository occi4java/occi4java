package occi.http.check;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

public class Grammar {

	public void check(String string) {
		String input = string;
		CharStream cs = new ANTLRStringStream(input);
		OcciLexer lexer = new OcciLexer(cs);
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(lexer);
		OcciParser parser = new OcciParser(tokens);
		// RuleReturnScope result = parser.compilationUnit();
	}

}