package hillbillies.statement.wildcardStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.statement.Statement;

public class Print extends Statement {
	
	public Print(Expression<?> expression) {
		super(expression);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		System.out.println(getExpression().evaluate(taskHandler).toString());
	}

}
