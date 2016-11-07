package prop.assignment0;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class Tokenizer implements ITokenizer {

	public static final int MAX_IDENTIFIER_SIZE = 126;
	private Scanner scanner;
	private BufferedReader bufferedReader;
	private LinkedList<Token> seenTokens;
	private Lexeme currentLexeme;

	public Tokenizer(String fileName) throws IOException, TokenizerException {
		seenTokens = new LinkedList<>();
		this.open(fileName);
	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner = new Scanner();
		scanner.open(fileName);
		scanner.moveNext();
	}

	@Override
	public Lexeme current() {

		return currentLexeme;

	}

	@Override
	public void moveNext() throws IOException, TokenizerException {
		consumeWhitespace();
		char c = scanner.current();
		boolean tokenFound = false;
		char[] chars = new char[126]; //limit identifier names to max 126 chars
		int i = 0;
		while ((chars[i] = scanner.current()) != Scanner.EOF) {
			while (!tokenFound) {
				chars[i] = scanner.current(); //fetch next char
				if (chars[i] == ' ') { //have we hit whitespace or EOF?
					tokenFound = true; //we've finished collecting a token
				}
				scanner.moveNext(); //get next char
				i++; //increase array item ptr
			}
			tokenize(chars);
		}

	}

	@Override
	public void close() throws IOException {

	}

	private void consumeWhitespace() throws IOException {
		while ((Character.isWhitespace(scanner.current()))) {
			scanner.moveNext();
		}
	}

	private void tokenize(char[] chars) {
		/*
		We need to add support for arbitrary
		length variable names.
		Perhaps regex?
		 */
		Token current;
		switch (chars[0]) {
			case '{':
				current = Token.LEFT_CURLY;
				break;
			case '}':
				current = Token.RIGHT_CURLY;
				break;
			case '(':
				current = Token.LEFT_PAREN;
				break;
			case ')':
				current = Token.RIGHT_PAREN;
				break;
			case ';':
				current = Token.SEMICOLON;
				break;
			case '+':
				current = Token.ADD_OP;
				break;
			case '-':
				current = Token.SUB_OP;
				break;
			case '/':
				current = Token.DIV_OP;
				break;
			case '=':
				current = Token.ASSIGN_OP;
				break;
			case '*':
				current = Token.MULT_OP;
				break;
			default:
				if (Character.isDigit((chars[0]))) {
					current = Token.INT_LIT;

					if (Character.isLowerCase(chars[0])) {
						current = Token.IDENT;
					}
				}

		}
		//Lexeme lex = new Lexeme(chars[0], current);
	//	currentLexeme = lex;
	}

	private void constructIDENT() throws IOException {
		char current = scanner.current();
		int i = 0;
		char[] chars = new char[MAX_IDENTIFIER_SIZE];
		while (Character.isLowerCase(current) && i < MAX_IDENTIFIER_SIZE) {
			chars[i] = current;
			scanner.moveNext();
		}
		String val = new String(chars);
		currentLexeme = new Lexeme(val, Token.IDENT);
	}
}
