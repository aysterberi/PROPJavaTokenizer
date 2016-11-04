package prop.assignment0;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class Tokenizer implements ITokenizer {

	private Scanner scanner;
	private BufferedReader bufferedReader;
	private LinkedList<Token> seenTokens;

	public Tokenizer(String fileName) throws IOException, TokenizerException {
		seenTokens = new LinkedList<>();
		this.open(fileName);
	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner = new Scanner();
		scanner.open(fileName);

		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null)
			stringBuilder.append(line);
		tokenize(stringBuilder.toString().replaceAll("\\s", "").toCharArray());
	}

	@Override
	public Lexeme current() {
		return null;
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {
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

	private void tokenize(char[] chars) {
		Token current;
		switch (chars[0]) {
			case '{':
				current  = Token.LEFT_CURLY;
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
			default:
				current = Token.NULL;

		}
		Lexeme lex = new Lexeme(chars[0], current);
	}
}
