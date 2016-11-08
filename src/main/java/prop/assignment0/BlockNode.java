package prop.assignment0;

public class BlockNode implements INode {
    public Lexeme left_curly;
    public Lexeme right_curly;
    public INode right;
    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
