package prop.assignment0;

public class ExpressionNode implements INode {
    public INode left;
    public INode right;
	public Token value;

	@Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
