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

    }
}
