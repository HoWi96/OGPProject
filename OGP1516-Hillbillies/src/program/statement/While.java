package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.E;
import program.expression.TaskHandler;

public class While extends S {
 
	private E<Boolean> condition;
	private S body;

	public While(E<Boolean> condition, S body, SourceLocation sourceLocation) {
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
	public E<Boolean> getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(E<Boolean> condition) {
		this.condition = condition;
	}

	/**
	 * @return the body
	 */
	public S getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(S body) {
		this.body = body;
	}


}
