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

	private INode constructStatement() throws IOException, ParserException, TokenizerException {
		while (tokenizer.current().token() != Token.SEMICOLON) {
			constructAssignment();
		}
		return null;
	}

	private INode constructAssignment() throws ParserException, TokenizerException, IOException {
		AssignmentNode aNode = new AssignmentNode();
		if (tokenizer.current().token() != Token.IDENT) {
			throw new ParserException("Missing or incorrect first operand for assignment.");
		}
		aNode.left = tokenizer.current().token(); //first operand
		tokenizer.moveNext(); //take a step
		if (tokenizer.current().token() != Token.ASSIGN_OP) {
			throw new ParserException("Missing or incorrect assignment operator.");
		}
		aNode.value = tokenizer.current().token();
		aNode.right = constructExpression();

		return aNode;
	}

	private INode constructExpression() throws TokenizerException, ParserException, IOException {
		ExpressionNode eNode = new ExpressionNode();
		eNode.left = constructTerm();
		Token current = tokenizer.current().token();
		if (current != Token.ADD_OP || current != Token.SUB_OP) {
			throw new ParserException("Missing or incorrect operator for expression.");
		}
		eNode.value = current;
		tokenizer.moveNext();
		eNode.right = constructExpression();
		return eNode;
	}

	private INode constructTerm() throws IOException, TokenizerException, ParserException {
		TermNode tNode = new TermNode();
		tNode.left = constructFactor(); //parse left
		tokenizer.moveNext(); //parse op
		if (tokenizer.current().token() != Token.MULT_OP || tokenizer.current().token() != Token.DIV_OP) {
			throw new ParserException("Missing or incorrect operator for term .");
		} else {
			tNode.value = tokenizer.current().token();
		}
		tokenizer.moveNext();
		tNode.right = constructTerm();
		return tNode;

	}

	private INode constructFactor() {
		FactorNode fNode = new FactorNode();
		Lexeme lex = tokenizer.current();
		if (lex.token() == Token.INT_LIT || lex.token() == Token.IDENT) {
			fNode.leaf = lex;
		}
		//TODO: handle Expression as Factor
		return null;
	}
}
