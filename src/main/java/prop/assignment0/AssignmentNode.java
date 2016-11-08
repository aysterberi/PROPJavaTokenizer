package prop.assignment0;

public class AssignmentNode implements INode {
    public Lexeme ident;
    public Lexeme operator;
	public INode expressionNode;
    public Lexeme semicolon;
    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        for(int i = 0; i < tabs; i++)
            builder.append("\t");
        tabs++;
        builder.append("AssignmentNode\n").
                append(ident).
                append("\n").
                append(operator).
                append("\n");
        if(expressionNode != null)
            expressionNode.buildString(builder, tabs);
        builder.append(semicolon);
    }
}
