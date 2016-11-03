package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {

    private Tokenizer tokenizer;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tokenizer = new Tokenizer(fileName);
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
