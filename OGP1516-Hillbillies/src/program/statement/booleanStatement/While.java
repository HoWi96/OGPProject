package program.statement.booleanStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class While extends Statement {
 

	private Statement body;

	public While(Expression<Boolean> condition, Statement body, SourceLocation sourceLocation) {
		super(condition,sourceLocation);
		this.body = body;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		if((boolean) getExpression().evaluate(taskHandler))
			getBody().setExecuted(false);
		else
			setExecuted(true);	
	}
	
	@Override
	public void setExecuted(boolean executed) {
		super.setExecuted(executed);
		if(executed == false)
			getBody().setExecuted(false);
	}
	
	@Override
	public Statement getNext(TaskHandler taskHandler) {
		if((boolean) getExpression().evaluate(taskHandler))
			return getBody();
		else
			return null;
	}

	/**
	 * @return the body
	 */
	public Statement getBody() {
		return body;
	}




}
