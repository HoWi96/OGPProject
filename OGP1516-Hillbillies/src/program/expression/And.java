package program.expression;

import hillbillies.part3.programs.SourceLocation;

public class And extends BinairyOperation {

	public And(Expression<Boolean> left, Expression<Boolean> right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		// TODO Auto-generated method stub
		return null;
	}

}
