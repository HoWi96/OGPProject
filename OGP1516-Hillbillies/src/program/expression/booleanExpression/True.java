package program.expression.booleanExpression;

import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class True extends Expression<Boolean> {

	public True(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return true;
	}

}
