package hillbillies.statement.booleanStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.statement.Statement;

public class If extends Statement {

	private final Statement ifBody;
	private final Statement elseBody;


	public If(Expression<Boolean> condition, Statement ifBody, Statement elseBody) {
		super(condition);
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		
		if((boolean) getExpression().evaluate(taskHandler)){
			setNext(getIfBody());
			getIfBody().setPrevious(getPrevious());
			
		}else if(getElseBody()!=null){
			setNext(getElseBody());
			getElseBody().setPrevious(getPrevious());
		} else
			setNext(null);
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
