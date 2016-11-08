package prop.assignment0;

public class FactorNode implements INode {
    public Lexeme leftParen;
	public Lexeme rightParen;
	public Lexeme ident;
	public INode expressionNode;
	@Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
