package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {
	public static final int MAX_IDENTIFIER_SIZE = 126;
    private Scanner scanner;
    private Lexeme current;

    public Tokenizer()  {
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
        removeWhitespace();
        char c = scanner.current();
        // if c == a || t
        // current.produceDeterminer();
    }


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

	//	private void tokenize(char ch) {
//		switch (ch) {
//			case '{':
//				current = Token.LEFT_CURLY;
//				break;
//			case '}':
//				current = Token.RIGHT_CURLY;
//				break;
//			case '(':
//				current = Token.LEFT_PAREN;
//				break;
//			case ')':
//				current = Token.RIGHT_PAREN;
//				break;
//			case ';':
//				current = Token.SEMICOLON;
//				break;
//			case '+':
//				current = Token.ADD_OP;
//				break;
//			case '-':
//				current = Token.SUB_OP;
//				break;
//			case '/':
//				current = Token.DIV_OP;
//				break;
//			case '=':
//				current = Token.ASSIGN_OP;
//				break;
//			case '*':
//				current = Token.MULT_OP;
//				break;
//			default:
//				if (Character.isDigit((chars[0]))) {
//					current = Token.INT_LIT;
//
//					if (Character.isLowerCase(chars[0])) {
//						current = Token.IDENT;
//					}
//				}
//
//		}
//		//Lexeme lex = new Lexeme(chars[0], current);
//		//	currentLexeme = lex;
//	}
//
	private void constructIDENT() throws IOException {
		char ch = scanner.current();
		int i = 0;
		char[] chars = new char[MAX_IDENTIFIER_SIZE];
		while (Character.isLowerCase(ch) && i < MAX_IDENTIFIER_SIZE) {
			chars[i] = ch;
			scanner.moveNext();
		}
		String val = new String(chars);
		currentLexeme = new Lexeme(val, Token.IDENT);
	}

	private void constructINT_LIT() throws IOException {
		char ch = scanner.current();
		int i = 0;
		char[] chars = new char[Integer.MAX_VALUE];
		while (Character.isDigit(ch) && i < Integer.MAX_VALUE) {
			chars[i] = ch;
			scanner.moveNext();
		}
		int val = Integer.parseInt(new String(chars));
		currentLexeme = new Lexeme(val, Token.INT_LIT);
	}
}
