package hillbillies.statement.positionStatement;


import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.position.CubePosition;
import hillbillies.statement.Statement;

public class Work extends Statement {

	public Work(Expression<CubePosition> position) {
		super(position);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		try{
			int[] cube = ((CubePosition) getExpression().evaluate(taskHandler)).toArray();
			unit.workAt(cube);
		} catch(Exception e){
			taskHandler.interruptTask();
			throw new Error("work not executable");
		}

	}

}
