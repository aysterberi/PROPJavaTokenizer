package prop.assignment0;

import java.io.IOException;


public class Parser implements IParser {

    private Tokenizer tokenizer;

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
        tokenizer.close();
    }

    //all the methods for building statements
    private INode constructBlock() throws ParserException, IOException, TokenizerException {
        BlockNode bNode = new BlockNode();
        if (tokenizer.current().token() == Token.EOF)
            return bNode;
        if (tokenizer.current().token() != Token.LEFT_CURLY) { //invalid start of a block
            throw new ParserException("Invalid block initialisation. Are you missing a '{' ?");
        }
        if (tokenizer.current().token() == Token.LEFT_CURLY) {
            bNode.left_curly = tokenizer.current();
        }

        tokenizer.moveNext(); //fetch next token
        bNode.right = constructStatement(); //point right
        if (tokenizer.current().token() != Token.RIGHT_CURLY &&
                tokenizer.current().token() != Token.EOF) { //invalid closing of a block
            throw new ParserException("Invalid block closure. Are you missing a '}' ?");
        }
        bNode.right_curly = tokenizer.current();
        return bNode;
    }

    private INode constructStatement() throws IOException, ParserException, TokenizerException {
        StatementsNode sNode = new StatementsNode();
        if (tokenizer.current().token() == Token.RIGHT_CURLY || tokenizer.current().token() == Token.NULL) {
            return sNode;
        }
        sNode.assignmentNode = constructAssignment();
        //	tokenizer.moveNext();
        sNode.statementsNode = (StatementsNode) constructStatement();
        return sNode;
    }

    private INode constructAssignment() throws ParserException, TokenizerException, IOException {
        AssignmentNode aNode = new AssignmentNode();
        aNode.ident = tokenizer.current();
        tokenizer.moveNext();
        if (tokenizer.current().token() == Token.ASSIGN_OP) {
            aNode.operator = tokenizer.current();
            tokenizer.moveNext();
        } else {
            throw new ParserException("Lack of operator in assignment node");

        }
        aNode.expressionNode = constructExpression();
        if (tokenizer.current().token() == Token.SEMICOLON) {
            aNode.semicolon = tokenizer.current();
            tokenizer.moveNext();
        } else {
            throw new ParserException("No semicolon!");
        }
        return aNode;
    }

    private INode constructExpression() throws TokenizerException, ParserException, IOException {
        ExpressionNode eNode = new ExpressionNode();
        eNode.termNode = constructTerm();
        Lexeme lex = tokenizer.current();
        if (lex.token() != Token.ADD_OP && lex.token() != Token.SUB_OP) //is it a valid expression?
        {
            return eNode;
        }
        eNode.operator = tokenizer.current();
        tokenizer.moveNext(); //dags för expr
        eNode.expressionNode = constructExpression();
        return eNode;
    }

    private INode constructTerm() throws IOException, TokenizerException, ParserException {
        TermNode tNode = new TermNode();
        tNode.factorNode = constructFactor();
        tokenizer.moveNext(); //parse op
        Lexeme lex = tokenizer.current(); //fetch next
        //if it's a operator, we should expect a term after
        //if it's not an operator, return
        if (lex.token() != Token.MULT_OP && lex.token() != Token.DIV_OP) {
            return tNode;
        }
        tNode.operator = tokenizer.current();
        tokenizer.moveNext();
        tNode.termNode = constructTerm();
        return tNode;

    }

    private INode constructFactor() throws IOException, ParserException, TokenizerException {
        FactorNode fNode = new FactorNode();
        Lexeme lex = tokenizer.current();
        if (lex.token() == Token.INT_LIT || lex.token() == Token.IDENT) {
            fNode.ident = lex; //store our lexeme
        }
        if (lex.token() == Token.LEFT_PAREN) {
            fNode.leftParen = lex;
            tokenizer.moveNext();
            fNode.expressionNode = constructExpression();
            if (tokenizer.current().token() == Token.RIGHT_PAREN) {
                fNode.rightParen = tokenizer.current();
            }
        }
        return fNode;
    }
}
