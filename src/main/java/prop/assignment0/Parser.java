package prop.assignment0;

import java.io.IOException;


public class Parser implements IParser {

    private Tokenizer tokenizer;
    private Lexeme last;
    private BlockNode bNode;

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
        if(tokenizer.current().token() == Token.EOF)
            return bNode;
        bNode = new BlockNode();
        if (tokenizer.current().token() != Token.LEFT_CURLY) {
            //invalid start of a block
            throw new ParserException("Invalid block initialisation. Are you missing a '{' ?");
        }

        tokenizer.moveNext(); //fetch next token
        bNode.right = constructStatement(); //point right
        tokenizer.moveNext(); //fetch next, which ought to be a right curly brace
        if (tokenizer.current().token() != Token.RIGHT_CURLY &&
                tokenizer.current().token() != Token.EOF) {
            //invalid closing of a block
            throw new ParserException("Invalid block closure. Are you missing a '}' ?");
        }
        bNode.left = tokenizer.current();

        return bNode;
    }

    private INode constructStatement() throws IOException, ParserException, TokenizerException {
        StatementsNode sNode = new StatementsNode();
        if (tokenizer.current().token() == Token.ADD_OP ||
                tokenizer.current().token() == Token.SUB_OP ||
                tokenizer.current().token() == Token.DIV_OP ||
                tokenizer.current().token() == Token.MULT_OP) { // if an operator comes after an assignment
                                                                // a new statement should be constructed
            // TODO: left/right?
        } else if (tokenizer.current().token() != Token.IDENT && //TODO duplication
                tokenizer.current().token() != Token.INT_LIT) {
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
        if (tokenizer.current().token() == Token.SEMICOLON) {
            // TODO: left/right
        } else if (tokenizer.current().token() != Token.SEMICOLON &&
                tokenizer.current().token() != Token.EOF) {
			throw new ParserException("Missing semicolon on assignment. Make sure you haven't missed a ;");
		}
        return aNode;
    }

    private INode constructExpression() throws TokenizerException, ParserException, IOException {
        System.out.println("Building an expression!");
        ExpressionNode eNode = new ExpressionNode();
        eNode.left = constructTerm(); //parse the left [should be an ID]
        tokenizer.moveNext(); //parse OP [let's get our operator
        Lexeme lex = tokenizer.current();
        if (lex.token() == Token.ADD_OP || lex.token() == Token.SUB_OP) //is it a valid expression?
        {
            //we have an operator!
            eNode.value = lex.token();
            tokenizer.moveNext();  //fetch next
            eNode.right = constructExpression();
            tokenizer.moveNext();
            return eNode;
        }
        //we only have a term in our expr, return;
        tokenizer.moveNext();
        return eNode;
    }

    private INode constructTerm() throws IOException, TokenizerException, ParserException {
        TermNode tNode = new TermNode();
        tNode.left = constructFactor(); //parse the left
        tokenizer.moveNext(); //parse op
        Lexeme lex = tokenizer.current(); //fetch next
        //if it's a operator, we should expect a term after
        //if it's not an operator, return
        if (lex.token() == Token.MULT_OP || lex.token() == Token.DIV_OP) {
            tNode.value = lex.token();
            tokenizer.moveNext(); //take a step
            tNode.right = constructTerm();
            tokenizer.moveNext();
            return tNode;
        }
        tokenizer.moveNext();
        return tNode;

    }

    private INode constructFactor() throws IOException, ParserException, TokenizerException {
        FactorNode fNode = new FactorNode();
        Lexeme lex = tokenizer.current();
        if (lex.token() == Token.INT_LIT || lex.token() == Token.IDENT) {
            fNode.value = lex; //store our lexeme
        }
        if (lex.token() != Token.IDENT
                && lex.token() != Token.INT_LIT && lex.token() == Token.LEFT_PAREN) {
            tokenizer.moveNext();
            fNode.right = constructExpression();
            tokenizer.moveNext(); //consume
            if (tokenizer.current().token() == Token.RIGHT_PAREN)
                return fNode;
        }
        if (lex.token() != Token.IDENT &&
                lex.token() != Token.INT_LIT &&
                lex.token() != Token.LEFT_PAREN) {
            throw new ParserException("Invalid factor");
        }
        return fNode;
    }
}
