package program.statement.booleanStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class If extends Statement {

	private final Statement ifBody;
	private final Statement elseBody;


	public If(Expression<Boolean> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(condition,sourceLocation);
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		this.setExecuted(true);
	}
	
	@Override
	public Statement getNext(TaskHandler taskHandler) {
		if((boolean) getExpression().evaluate(taskHandler))
			return getIfBody();
		else if(getElseBody()!=null)
			return getElseBody();
		else
			return null;
	}

	/**
	 * @return the ifBody
	 */
	public final Statement getIfBody() {
		return ifBody;
	}
	
	/**
	 * @return the elseBody
	 */
	public final Statement getElseBody() {
		return elseBody;
	}
	
	@Override
	public void setExecuted(boolean executed){
		super.setExecuted(executed);
		if(executed == false){
			getIfBody().setExecuted(executed);
			if(getElseBody()!= null)
				getElseBody().setExecuted(executed);
		}
	}
}
