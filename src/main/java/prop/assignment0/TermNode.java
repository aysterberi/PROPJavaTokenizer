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
        for(int i = 0; i < tabs; i++)
            builder.append("\t");
        tabs++;
        builder.append("TermNode\n");
        if (factorNode != null)
            factorNode.buildString(builder, tabs);
        builder.append(operator).
                append("\n");
        if (termNode != null)
            termNode.buildString(builder, tabs);
    }
}
