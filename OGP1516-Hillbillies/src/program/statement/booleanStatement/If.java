package program.statement.booleanStatement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.statement.Statement;

public class If extends Statement {

	private final Expression<Boolean> condition;
	private final Statement ifBody;
	private final Statement elseBody;


	public If(Expression<Boolean> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the condition
	 */
	public final Expression<Boolean> getCondition() {
		return condition;
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


}
