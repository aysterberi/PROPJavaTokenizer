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
        for(int i = 0; i < tabs; i++)
            builder.append("\t");
        tabs++;
        builder.append("FactorNode\n");
        if (expressionNode != null) {
            builder.append(leftParen).
                    append("\n");
            expressionNode.buildString(builder, tabs);
            builder.append(rightParen).
                    append("\n");
        } else
            builder.append(ident).
                    append("\n");
    }
}
