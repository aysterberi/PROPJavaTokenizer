package prop.assignment0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tokenizer implements ITokenizer {

    private BufferedReader bufferedReader;

    public Tokenizer(String fileName) throws IOException, TokenizerException{
        this.open(fileName);
    }

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        bufferedReader = new BufferedReader(new FileReader(fileName));
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

    }

    @Override
    public void close() throws IOException {

    }

    private void tokenize(char[] chars) {

    }
}
