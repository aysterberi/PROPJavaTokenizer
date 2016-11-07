package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {
    public static final int MAX_IDENTIFIER_SIZE = 126;
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
        char c = scanner.current();
        lookupTable(c);
        scanner.moveNext();
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

    private void lookupTable(char ch) throws IOException {
        switch (ch) {
            case '{':
                current = new Lexeme('{', Token.LEFT_CURLY);
                break;
            case '}':
                current = new Lexeme('}', Token.RIGHT_CURLY);
                break;
            case '(':
                current = new Lexeme('(', Token.LEFT_PAREN);
                break;
            case ')':
                current = new Lexeme(')', Token.RIGHT_PAREN);
                break;
            case ';':
                current = new Lexeme(';', Token.SEMICOLON);
                break;
            case '+':
                current = new Lexeme('+', Token.ADD_OP);
                break;
            case '-':
                current = new Lexeme('-', Token.SUB_OP);
                break;
            case '*':
                current = new Lexeme('*', Token.MULT_OP);
                break;
            case '/':
                current = new Lexeme('/', Token.DIV_OP);
                break;
            case '=':
                current = new Lexeme('=', Token.ASSIGN_OP);
                break;
            default:
                controlTokenEnum(ch);
                break;
        }
    }

    private void controlTokenEnum(char ch) throws IOException {
        if (Character.isDigit(ch))
            constructIntegerLiteral();
        else
            constructIdentifier();
    }

    private void constructIdentifier() throws IOException {
        char ch = scanner.current();
        int i = 0;
        char[] chars = new char[MAX_IDENTIFIER_SIZE];
        while (Character.isLowerCase(ch) && i < MAX_IDENTIFIER_SIZE) {
            chars[i] = ch;
            scanner.moveNext();
            ch = scanner.current();
        }
        String val = new String(chars).trim();
        this.current = new Lexeme(val, Token.IDENT);
    }

    private void constructIntegerLiteral() throws IOException {
        char ch = scanner.current();
        int i = 0;
        char[] chars = new char[Integer.MAX_VALUE];
        while (Character.isDigit(ch) && i < Integer.MAX_VALUE) {
            chars[i] = ch;
            scanner.moveNext();
            ch = scanner.current();
        }
        int val = Integer.parseInt(new String(chars).trim());
        this.current = new Lexeme(val, Token.INT_LIT);
    }
}
