package hillbillies.statement.unitStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statement.Statement;

public class Follow extends Statement {

	public Follow(Expression<Unit> unit, SourceLocation sourceLocation) {
		super(unit, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit leader = taskHandler.getUnit();
		Unit stalker = (Unit) getExpression().evaluate(taskHandler);
		try{
			stalker.follow(leader);
		
		} catch(Exception e){
			taskHandler.interruptTask();
			throw new Error("follow not executable");
		}
	}
}