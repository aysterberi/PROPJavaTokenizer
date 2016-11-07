package prop.assignment0;

import java.io.IOException;


public class Parser implements IParser {

	private Tokenizer tokenizer;
	private Lexeme last;

	public Parser() {

	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
		tokenizer.moveNext(); //initialise our tokenizer
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		return constructBlock();
	}

	@Override
	public void close() throws IOException {

	}

	//all the methods for building statements
	private INode constructBlock() throws ParserException, IOException, TokenizerException {
		BlockNode bNode = new BlockNode();
		if (tokenizer.current().token() != Token.LEFT_CURLY) {
			//invalid start of a block
			throw new ParserException("Invalid block initialisation. Are you missing a '{' ?");
		}

		tokenizer.moveNext(); //fetch next token
		bNode.right = constructStatement(); //point right
		tokenizer.moveNext(); //fetch next, which ought to be a right curly brace
		if (tokenizer.current().token() != Token.RIGHT_CURLY) {
			//invalid closing of a block
			throw new ParserException("Invalid block closure. Are you missing a '}' ?");
		}
		bNode.left = tokenizer.current();

		return bNode;
	}

	private INode constructStatement() throws IOException, ParserException, TokenizerException {
		StatementsNode sNode = new StatementsNode();
		if (tokenizer.current().token() != Token.IDENT || tokenizer.current().token() != Token.IDENT) {
			throw new ParserException("Invalid statement syntax.");
		}
		sNode.left = constructAssignment();
		tokenizer.moveNext();
		if (tokenizer.current().token() == Token.EOF) {
			sNode.right = null;
			return sNode;
		}
		sNode.right = constructStatement();
		return sNode;
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
		tokenizer.moveNext(); //always take a step before calling submethod
		aNode.right = constructExpression();
		tokenizer.moveNext(); //we have to check if we have a ;
		if (tokenizer.current().token() != Token.SEMICOLON) {
			throw new ParserException("Missing semicolon on assignment. Make sure you haven't missed a ;");
		}
		return aNode;
	}

	private INode constructExpression() throws TokenizerException, ParserException, IOException {
		ExpressionNode eNode = new ExpressionNode();
		eNode.left = constructTerm();
		Token current = tokenizer.current().token();
		if (current != Token.ADD_OP && current != Token.SUB_OP) {
			throw new ParserException("Missing or incorrect operator for expression.");
		}
		eNode.value = current;
		tokenizer.moveNext();  //always take a step before calling submethod
		eNode.right = constructExpression();
		return eNode;
	}

	private INode constructTerm() throws IOException, TokenizerException, ParserException {
		TermNode tNode = new TermNode();
		tNode.left = constructFactor(); //parse left
		tokenizer.moveNext(); //parse op
		if (tokenizer.current().token() != Token.MULT_OP && tokenizer.current().token() != Token.DIV_OP) {
			throw new ParserException("Missing or incorrect operator for term .");
		} else {
			tNode.value = tokenizer.current().token();
		}
		tokenizer.moveNext();  //always take a step before calling submethod
		if (tokenizer.current().token() != Token.INT_LIT || tokenizer.current().token() != Token.IDENT) {
			tNode.right = constructTerm();
		}
		tNode.right = null;
		return tNode;

	}

	private INode constructFactor() {
		FactorNode fNode = new FactorNode();
		Lexeme lex = tokenizer.current();
		if (lex.token() == Token.INT_LIT || lex.token() == Token.IDENT) {
			fNode.leaf = lex;
		}
		//TODO: handle Expression as Factor
		return fNode;
	}
}
