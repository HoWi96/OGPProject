package program.statement;

import java.util.List;

import hillbillies.part3.programs.SourceLocation;
import program.expression.TaskHandler;

public class Sequence extends S {

	private final List<S> statements;

	public Sequence(List<S> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the statements
	 */
	public List<S> getStatements() {
		return statements;
	}

}
