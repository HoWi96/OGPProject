package hillbillies.statement.positionStatement;


import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statement.Statement;

public class Work extends Statement {

	public Work(Expression<CubePosition> position, SourceLocation sourceLocation) {
		super(position, sourceLocation);
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		Unit unit = taskHandler.getUnit();
		int[] cube = ((CubePosition) getExpression().evaluate(taskHandler)).toArray();
		try{
			unit.workAt(cube);
		} catch(Exception e){
			taskHandler.interruptTask();
			throw new Error("work not executable");
		}

	}

}
