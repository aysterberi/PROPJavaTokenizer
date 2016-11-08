package prop.assignment0;

import java.util.HashMap;
import java.util.Map;

public class BlockNode implements INode {
	public Lexeme left_curly;
	public Lexeme right_curly;
	public INode right;
	private Map<String, Object> varMap;

	@Override
	public Object evaluate(Object[] args) throws Exception {
		//create a map to store IDENT and their calculations
		varMap = new HashMap<>();
		Object[] newArgs = new Object[10];
		newArgs[0] = varMap;
		right.evaluate(newArgs); //evaluate right
		//
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<String, Object> var : varMap.entrySet()) {
			String varKey = var.getKey();
			String varValue = "";
			if (var.getValue() != null) {
				varValue = var.getValue().toString();
			}
			stringBuilder.append(varKey).append("=").append(varValue);
		}
		return stringBuilder.toString();
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("BlockNode\n").append(left_curly.toString()).append("\n");
		tabs++;
		if (right != null)
			right.buildString(builder, tabs);
		builder.append(right_curly.toString()).append("\n");
	}
}
