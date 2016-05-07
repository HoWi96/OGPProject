package program.expression;

import hillbillies.part3.programs.SourceLocation;
import program.expression.booleanExpression.TaskHandler;

public abstract class Expression<T> {
	
	private SourceLocation sourceLocation;

	public Expression(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	
	public abstract T evaluate(TaskHandler taskHandler);

}
