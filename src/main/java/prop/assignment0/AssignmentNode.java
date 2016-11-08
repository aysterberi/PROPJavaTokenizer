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
        applyTabs(builder, tabs);
        tabs++;
        builder.append("AssignmentNode\n");
        applyTabs(builder, tabs);
        builder.append(ident).append("\n");
        applyTabs(builder, tabs);
        builder.append(operator).append("\n");
        if(expressionNode != null)
            expressionNode.buildString(builder, tabs);
        applyTabs(builder, tabs);
        builder.append(semicolon).append("\n");
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
