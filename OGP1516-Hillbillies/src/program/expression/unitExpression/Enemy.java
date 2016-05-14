package program.expression.unitExpression;

import hillbillies.model.Faction;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Enemy extends Expression<Unit> {

	public Enemy(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		Faction faction = taskHandler.getUnit().getFaction();
		Unit unit = taskHandler.getUnit();
		return taskHandler.getWorld().getAllFactions()
				
				.stream()
				.filter((Faction f)-> f != faction)
				.findAny()
				.get()
				.getAllUnits()
				
				.stream()
				.filter((Unit u)-> u != unit)
				.findAny()
				.get();
		
	}

}
