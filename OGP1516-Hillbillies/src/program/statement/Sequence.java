package program.statement;

import java.util.List;

import hillbillies.model.TaskHandler;
import hillbillies.part3.programs.SourceLocation;
import program.expression.booleanExpression.True;

public class Sequence extends Statement {

	private final List<Statement> statements;


	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(new True(sourceLocation),sourceLocation);
		this.statements = statements;
	}

	@Override
	public void execute(TaskHandler taskHandler) {
		return;
			
	}

	@Override
	public void setExecuted(boolean executed) {
		super.setExecuted(executed);
		if(executed == false)
			for (Statement statement: statements){
				statement.setExecuted(false);
			}
	}
	
	@Override
	public Statement getNext(TaskHandler taskHandler) {
		for (Statement statement: statements){
			if(!statement.isExecuted())
				return statement;
		}
		this.setExecuted(true);
		return null;
		
		
	}

	/**
	 * @return the statements
	 */
	public List<Statement> getStatements() {
		return statements;
	}
	

}
