package hillbillies.statement.positionStatement;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.position.CubePosition;
import hillbillies.statement.Statement;

public class MoveTo extends Statement {

	public MoveTo(Expression<CubePosition> position) {
		super(position);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		try{
			int[] cube = ((CubePosition) getExpression().evaluate(taskHandler)).toArray();
			unit.moveTo(cube);
		}catch(Exception e){
			taskHandler.interruptTask();
			throw new Error("moveTo not executable");
		}
	}

}
