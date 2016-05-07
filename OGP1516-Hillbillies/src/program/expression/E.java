package program.expression;

import hillbillies.part3.programs.SourceLocation;

public abstract class E<T> {
	
	private SourceLocation sourceLocation;

	public E(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	
	public abstract T evaluate(TaskHandler taskHandler);
	

}
