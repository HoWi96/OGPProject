package program.statement;

import java.util.List;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.booleanExpression.True;

public class Sequence extends Statement {

	private final List<Statement> statements;

	@SuppressWarnings("unchecked")
	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(new True(sourceLocation),sourceLocation);
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
