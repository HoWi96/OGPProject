package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.booleanExpression.TaskHandler;

public class While extends Statement {
 
	private Expression<Boolean> condition;
	private Statement body;

	public While(Expression<Boolean> condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setCondition(condition);
		this.setBody(body);
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
	 * @param condition the condition to set
	 */
	public void setCondition(Expression<Boolean> condition) {
		this.condition = condition;
	}

	/**
	 * @return the body
	 */
	public Statement getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(Statement body) {
		this.body = body;
	}


}
