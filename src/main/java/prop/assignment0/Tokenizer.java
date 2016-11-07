package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {

    private Scanner scanner;
    private Lexeme current;

    public Tokenizer() throws IOException, TokenizerException {
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

    @Override
    public void close() throws IOException {

    }

    private void removeWhitespace() throws IOException {
        while (scanner.current() == Character.DIRECTIONALITY_WHITESPACE) {
            scanner.moveNext();
        }
    }
}
