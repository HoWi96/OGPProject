package program.statement.wildcardStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class Print extends Statement {
	
	public Print(Expression<?> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		System.out.println(getExpression().evaluate(taskHandler).toString());
		setExecuted(true);
	}

}
