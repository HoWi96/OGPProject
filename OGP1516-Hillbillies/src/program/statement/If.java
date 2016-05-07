package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.E;
import program.expression.TaskHandler;

public class If extends S {

	private final E<Boolean> condition;
	private final S ifBody;
	private final S elseBody;


	public If(E<Boolean> condition, S ifBody, S elseBody, SourceLocation sourceLocation) {
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
	public final E<Boolean> getCondition() {
		return condition;
	}
	
	/**
	 * @return the ifBody
	 */
	public final S getIfBody() {
		return ifBody;
	}


	/**
	 * @return the elseBody
	 */
	public final S getElseBody() {
		return elseBody;
	}


}
