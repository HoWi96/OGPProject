package program.statement;

import java.util.List;

import hillbillies.part3.programs.SourceLocation;
import program.expression.TaskHandler;

public class Sequence extends Statement {

	private final List<Statement> statements;

	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
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
	public List<Statement> getStatements() {
		return statements;
	}

}
