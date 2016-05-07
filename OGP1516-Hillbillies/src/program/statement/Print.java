package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.E;
import program.expression.TaskHandler;

public class Print extends S {

	private final E<?> value;

	public Print(E<?> value, SourceLocation sourceLocation) {
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
	public E<?> getValue() {
		return value;
	}

}
