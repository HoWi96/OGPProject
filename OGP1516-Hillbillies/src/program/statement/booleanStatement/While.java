package program.statement.booleanStatement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.booleanExpression.TaskHandler;
import program.statement.Statement;

public class While extends Statement {
 
	private Expression<Boolean> condition;
	private Statement body;

	public While(Expression<Boolean> condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return the condition
	 */
	public Expression<Boolean> getCondition() {
		return condition;
	}

	/**
	 * @return the body
	 */
	public Statement getBody() {
		return body;
	}



}
