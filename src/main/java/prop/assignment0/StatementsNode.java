package prop.assignment0;

public class StatementsNode implements INode {
    public INode assignmentNode;
    public INode statementsNode;
    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        for(int i = 0; i < tabs; i++)
            builder.append("\t");
        tabs++;
        builder.append("StatementsNode\n");
        if(assignmentNode != null)
            assignmentNode.buildString(builder, tabs);
        if(statementsNode != null)
            statementsNode.buildString(builder, tabs);
    }
}
