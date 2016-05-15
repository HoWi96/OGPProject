package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

import hillbillies.expression.Expression;
import hillbillies.expression.ReadVariable;
import hillbillies.expression.booleanExpression.*;
import hillbillies.expression.positionExpression.*;
import hillbillies.expression.unitExpression.*;

import hillbillies.model.position.CubePosition;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

import hillbillies.statement.Statement;
import hillbillies.statement.booleanStatement.*;
import hillbillies.statement.positionStatement.*;
import hillbillies.statement.unitStatement.*;
import hillbillies.statement.wildcardStatement.Assignment;
import hillbillies.statement.wildcardStatement.Print;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		 List<Task> tasks = new ArrayList<>();
//	        for(int[] cube : selectedCubes)
	            tasks.add(new Task(name, priority, activity));
	        return tasks;
	}

	@Override
	public Statement createAssignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {
		return new Assignment(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new If(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Statement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new hillbillies.statement.Sequence(statements, sourceLocation);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		return new MoveTo(position, sourceLocation);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		return new Work(position, sourceLocation);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new Follow(unit, sourceLocation);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new Attack(unit, sourceLocation);
	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariable(variableName, sourceLocation);
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
		return new LogExpression(sourceLocation);
	}

	@Override
	public Expression<CubePosition> createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression(sourceLocation);
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
