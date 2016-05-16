package hillbillies.statement.unitStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.statement.Statement;

public class Follow extends Statement {

	public Follow(Expression<Unit> unit) {
		super(unit);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit stalker = taskHandler.getUnit();
		try{
			Unit leader = (Unit) getExpression().evaluate(taskHandler);
			stalker.follow(leader);
		} catch(Exception e){
			taskHandler.interruptTask();
			System.out.println(e.toString());
			throw new Error("follow not executable");
		}
	}
}