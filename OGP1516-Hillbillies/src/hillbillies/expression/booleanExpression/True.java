package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;

public class True extends Expression<Boolean> {

	public True(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return true;
	}

}
