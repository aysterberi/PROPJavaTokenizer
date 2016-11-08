package prop.assignment0;

public class StatementsNode implements INode {
    public INode assignmentNode;
    public StatementsNode statementsNode;
    @Override
    public Object evaluate(Object[] args) throws Exception {
        Object result = null;
        if (assignmentNode != null)
        {
            result = assignmentNode.evaluate(args);
            //call eval and store result
                //recurse eval next statement
            if(statementsNode != null && statementsNode.assignmentNode!= null )
            //make sure the statement a) exists, b) is not null!
            //else we will call eval on an empty StatementsNode
            {
              statementsNode.evaluate(args);
            }
        }
        return result;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}