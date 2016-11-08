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
        builder.append("BlockNode\n");
        tabs++;
        applyTabs(builder, tabs);
        builder.append(left_curly).
                append("\n");
        if (right != null)
            right.buildString(builder, tabs);
        applyTabs(builder, tabs);
        builder.append(right_curly).
                append("\n");
    }

    private void applyTabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
