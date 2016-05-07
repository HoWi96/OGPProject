package program.expression;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public class This extends Expression<Unit> {

	public This(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Unit evaluate(TaskHandler taskHandler) {
		return null;
	}

}
