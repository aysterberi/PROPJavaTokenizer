package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {
    public static final int MAX_IDENTIFIER_SIZE = 126;
    public static final int MAX_INTEGER_SIZE = 100;
    private Scanner scanner;
    private Lexeme current;

    public Tokenizer() {
    }

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        scanner = new Scanner();
        scanner.open(fileName);
        scanner.moveNext();
    }

    @Override
    public Lexeme current() {
        return current;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        consumeWhitespace();
        lookupTable(scanner.current());
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }

    private void consumeWhitespace() throws IOException {
        while ((Character.isWhitespace(scanner.current()))) {
            scanner.moveNext();
        }
    }

    private void lookupTable(char ch) throws IOException, TokenizerException {
        switch (ch) {
            case '{':
                current = new Lexeme('{', Token.LEFT_CURLY);
                scanner.moveNext();
                break;
            case '}':
                current = new Lexeme('}', Token.RIGHT_CURLY);
                scanner.moveNext();
                break;
            case '(':
                current = new Lexeme('(', Token.LEFT_PAREN);
                scanner.moveNext();
                break;
            case ')':
                current = new Lexeme(')', Token.RIGHT_PAREN);
                scanner.moveNext();
                break;
            case ';':
                current = new Lexeme(';', Token.SEMICOLON);
                scanner.moveNext();
                break;
            case '+':
                current = new Lexeme('+', Token.ADD_OP);
                scanner.moveNext();
                break;
            case '-':
                current = new Lexeme('-', Token.SUB_OP);
                scanner.moveNext();
                break;
            case '*':
                current = new Lexeme('*', Token.MULT_OP);
                scanner.moveNext();
                break;
            case '/':
                current = new Lexeme('/', Token.DIV_OP);
                scanner.moveNext();
                break;
            case '=':
                current = new Lexeme('=', Token.ASSIGN_OP);
                scanner.moveNext();
                break;
            default:
                controlTokenEnum(ch);
                break;
        }
    }

    private void controlTokenEnum(char ch) throws IOException, TokenizerException {
        if (Character.isDigit(ch))
            constructIntegerLiteral();
        else if(Character.isLowerCase(ch))
            constructIdentifier();
        else
            throw new TokenizerException("Character not part of language");
    }

    private void constructIdentifier() throws IOException {
        char ch = scanner.current();
        int i = 0;
        char[] chars = new char[MAX_IDENTIFIER_SIZE];
        while (Character.isLowerCase(ch) && i < MAX_IDENTIFIER_SIZE) {
            chars[i] = ch;
            scanner.moveNext();
            ch = scanner.current();
            i++;
        }
        String val = new String(chars).trim();
        this.current = new Lexeme(val, Token.IDENT);
    }

    private void constructIntegerLiteral() throws IOException {
        char ch = scanner.current();
        int i = 0;
        char[] chars = new char[MAX_INTEGER_SIZE];
        while (Character.isDigit(ch) && i < MAX_INTEGER_SIZE) {
            chars[i] = ch;
            scanner.moveNext();
            ch = scanner.current();
            i++;
        }
        double val = Double.parseDouble(new String(chars).trim());
        this.current = new Lexeme(val, Token.INT_LIT);
    }
}
