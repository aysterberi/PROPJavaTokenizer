package prop.assignment0;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Parser implements IParser {

	private Tokenizer tokenizer;
	private Lexeme last;

	public Parser() {

	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		return null;
	}

	@Override
	public void close() throws IOException {

	}

	//all the methods for building statements
	private INode constructBlock() throws ParserException, IOException, TokenizerException {
		List<INode> nodeList = new LinkedList<>();
		if (tokenizer.current().token() == (Token.LEFT_CURLY)) {
			while (tokenizer.current().token() != Token.RIGHT_CURLY) {
				tokenizer.moveNext(); //consume our left curly
				constructStatement();
			}
		} else {
			throw new ParserException("No left curly brace!");
		}
		return null;
	}

	private INode constructStatement() {
		while(tokenizer.current().token() != Token.SEMICOLON)
		{
			constructAssignment();
		}
		return null;
	}

	private INode constructAssignment() {

		return null;
	}

	private INode constructExpression() {
		constructTerm();
		constructExpression();
		return null;
	}

	private INode constructTerm() {
		constructFactor();
		return null;

	}

	private INode constructFactor() {
		constructExpression();
		return null;
	}
}
