package prop.assignment0;

public class TermNode implements INode {
    public INode factorNode;
    public Lexeme operator;
	public INode termNode;

	@Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
