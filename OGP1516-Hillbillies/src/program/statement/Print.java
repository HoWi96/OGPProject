package program.statement;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;

public class Print extends Statement {

	private final Expression<?> value;

	public Print(Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the value
	 */
	public Expression<?> getValue() {
		return value;
	}

}
