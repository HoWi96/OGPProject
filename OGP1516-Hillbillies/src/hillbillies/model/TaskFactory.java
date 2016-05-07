package hillbillies.model;

import java.util.List;


import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import program.expression.Expression;
import program.expression.ReadVariable;
import program.expression.booleanExpression.*;
import program.expression.unitExpression.*;
import program.expression.positionExpression.*;
import program.expression.positionExpression.Boulder;
import program.expression.positionExpression.Log;
import program.statement.booleanStatement.*;
import program.statement.positionStatement.*;
import program.statement.unitStatement.*;
import program.statement.wildcardStatement.Assignment;
import program.statement.wildcardStatement.Print;
import program.statement.Statement;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {

	public TaskFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement<?> createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, value, sourceLocation);
	}

	@Override
	public Statement<Boolean> createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement<Boolean> createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new If(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement<?> createBreak(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Statement<?> createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new program.statement.Sequence(statements, sourceLocation);
	}

	@Override
	public Statement<CubePosition> createMoveTo(Expression position, SourceLocation sourceLocation) {
		return new MoveTo(position, sourceLocation);
	}

	@Override
	public Statement<CubePosition> createWork(Expression position, SourceLocation sourceLocation) {
		return new Work(position, sourceLocation);
	}

	@Override
	public Statement<Unit> createFollow(Expression unit, SourceLocation sourceLocation) {
		return new Follow(unit, sourceLocation);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new Attack(unit, sourceLocation);
	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariable<>(variableName, sourceLocation);
	}

	@Override
	public Expression<Boolean> createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolid(position, sourceLocation);
	}

	@Override
	public Expression<Boolean> createIsPassable(Expression position, SourceLocation sourceLocation) {
		return createNot(createIsSolid(position, sourceLocation), sourceLocation);
	}

	@Override
	public Expression<Boolean> createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new isFriend(unit, sourceLocation);
	}

	@Override
	public Expression<Boolean> createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return createNot(createIsFriend(unit, sourceLocation), sourceLocation);
	}

	@Override
	public Expression<Boolean> createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAlive(unit, sourceLocation);
	}

	@Override
	public Expression<Boolean> createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItem(unit, sourceLocation);
	}

	@Override
	public Expression<Boolean> createNot(Expression expression, SourceLocation sourceLocation) {
		return new Not(expression, sourceLocation);
	}

	@Override
	public Expression<Boolean> createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new And(left, right, sourceLocation);
	}

	@Override
	public Expression<Boolean> createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Or(left, right, sourceLocation);
	}

	@Override
	public Expression<CubePosition> createHerePosition(SourceLocation sourceLocation) {
		return new Here(sourceLocation);
	}

	@Override
	public Expression<CubePosition> createLogPosition(SourceLocation sourceLocation) {
		return new Log(sourceLocation);
	}

	@Override
	public Expression<CubePosition> createBoulderPosition(SourceLocation sourceLocation) {
		return new Boulder(sourceLocation);
	}

	@Override
	public Expression<CubePosition> createWorkshopPosition(SourceLocation sourceLocation) {
		return new Workshop(sourceLocation);
	}

	@Override
	public Expression<CubePosition> createSelectedPosition(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression<CubePosition> createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new NextToPosition(position, sourceLocation);
	}

	@Override
	public Expression<CubePosition> createPositionOf(Expression unit, SourceLocation sourceLocation) {
		return new PositionOfUnit(unit, sourceLocation);
	}

	@Override
	public Expression<CubePosition> createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPosition(x, y, z, sourceLocation);
	}

	@Override
	public Expression<Unit> createThis(SourceLocation sourceLocation) {
		return new This(sourceLocation);
	}

	@Override
	public Expression<Unit> createFriend(SourceLocation sourceLocation) {
		return new Friend(sourceLocation);
	}

	@Override
	public Expression<Unit> createEnemy(SourceLocation sourceLocation) {
		return new Enemy(sourceLocation);
	}

	@Override
	public Expression<Unit> createAny(SourceLocation sourceLocation) {
		return new Any(sourceLocation);
	}

	@Override
	public Expression<Boolean> createTrue(SourceLocation sourceLocation) {
		return new True(sourceLocation);
	}

	@Override
	public Expression<Boolean> createFalse(SourceLocation sourceLocation) {
		return createNot(createTrue(sourceLocation), sourceLocation);
	}

}
