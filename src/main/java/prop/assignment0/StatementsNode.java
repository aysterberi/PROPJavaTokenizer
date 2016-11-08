package prop.assignment0;

public class StatementsNode implements INode {
    public INode assignmentNode;
    public INode statementsNode;
    @Override
    public Object evaluate(Object[] args) throws Exception {
        Object result = null;
        if (assignmentNode != null)
        {
            result = assignmentNode.evaluate(args);
            //call eval and store result
                //recurse eval next statement
            if(statementsNode != null)
            {

            }
        }
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
