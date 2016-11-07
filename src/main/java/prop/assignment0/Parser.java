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
	private void constructBlock() throws ParserException, IOException, TokenizerException {
		List<INode> nodeList = new LinkedList<>();
		if (tokenizer.current().token().equals(Token.LEFT_CURLY)) {
			while (tokenizer.current().token() != Token.RIGHT_CURLY) {
				tokenizer.moveNext();
				constructStatement();
			}
		} else {
			throw new ParserException("No left curly brace!");
		}
	}

	private void constructStatement() {
	}
}
