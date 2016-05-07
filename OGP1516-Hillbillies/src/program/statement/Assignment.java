package program.statement;

import hillbillies.part3.programs.SourceLocation;
import program.expression.E;
import program.expression.TaskHandler;

public class Assignment extends S {
	
	private final String variableName;
	private final E<?> value;

	public Assignment(String variableName, E<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;

	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return the variableName
	 */
	public final String getVariableName() {
		return variableName;
	}

	/**
	 * @return the value
	 */
	public final E<?> getValue() {
		return value;
	}


}
