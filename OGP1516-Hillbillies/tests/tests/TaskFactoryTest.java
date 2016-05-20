package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.Expression;
import hillbillies.expression.ReadVariable;
import hillbillies.expression.booleanExpression.IsSolid;
import hillbillies.expression.booleanExpression.Not;
import hillbillies.expression.booleanExpression.Or;
import hillbillies.expression.booleanExpression.True;
import hillbillies.expression.booleanExpression.And;
import hillbillies.expression.booleanExpression.CarriesItem;
import hillbillies.expression.booleanExpression.IsAlive;
import hillbillies.expression.booleanExpression.IsFriend;
import hillbillies.expression.positionExpression.BoulderPosition;
import hillbillies.expression.positionExpression.Here;
import hillbillies.expression.positionExpression.ItemPosition;
import hillbillies.expression.positionExpression.LiteralPosition;
import hillbillies.expression.positionExpression.LogPosition;
import hillbillies.expression.positionExpression.NextToPosition;
import hillbillies.expression.positionExpression.PositionOfUnit;
import hillbillies.expression.positionExpression.Workshop;
import hillbillies.expression.unitExpression.Any;
import hillbillies.expression.unitExpression.Enemy;
import hillbillies.expression.unitExpression.Friend;
import hillbillies.expression.unitExpression.This;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Task;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.Utils;
import hillbillies.model.World;
import hillbillies.model.position.CubePosition;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.statement.Sequence;
import hillbillies.statement.Statement;
import hillbillies.statement.booleanStatement.If;
import hillbillies.statement.booleanStatement.While;
import hillbillies.statement.positionStatement.MoveTo;
import hillbillies.statement.positionStatement.Work;
import hillbillies.statement.unitStatement.Attack;
import hillbillies.statement.unitStatement.Follow;
import hillbillies.statement.wildcardStatement.Assignment;
import hillbillies.statement.wildcardStatement.Print;

public class TaskFactoryTest {
	
	//private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static World world;
	private static Unit unit;
	private static Unit enemy;
	private static Unit friend;
	private static Faction faction;
	private static Log log;
	private static Boulder boulder;
	private static TaskHandler taskHandler;
	private static CubePosition rock;
	private static CubePosition tree;
	private static CubePosition workshop;
	private static CubePosition unitPosition;
	private static Task task;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int[][][] types = new int[3][4][5];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		rock = new CubePosition(1,1,0);
		tree = new CubePosition(1,1,1);
		workshop = new CubePosition(1,1,2);
		unitPosition = new CubePosition(0,0,0);
		
		
		world = new World(types, new DefaultTerrainChangeListener());
		unit = new Unit("Unit", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
		enemy = new Unit("Enemy", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
		friend = new Unit("Friend", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		world.addUnit(friend);
		Faction other = friend.getFaction();
		
		other.removeUnit(friend);
		other.terminate();
		world.removeFaction(other);
		
		faction = unit.getFaction();
		faction.addUnit(friend);
		log = new Log(new int[] { 2, 2, 0 }, world);
		boulder = new Boulder(new int[] { 2, 3, 0 }, world);
		task = new Task("Task",100,new Print(new LiteralPosition(0, 0, 0)));
		taskHandler = new TaskHandler(unit, world, task);
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Unit actionUnit;

	@Before
	public void setUp() throws Exception {
		actionUnit = new Unit("ActionUnit", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
		world.addUnit(actionUnit);

	}

	@After
	public void tearDown() throws Exception {
		if(world.hasAsUnit(actionUnit))
			world.removeUnit(actionUnit);
	}
	
	//STATEMENTS

	@Test
	public final void testCreateAssignment() {
		Statement s = new Assignment("True",new True());
		Task taskTest = new Task("Task",100,s);
		taskTest.addUnit(actionUnit);
		taskTest.addAsScheduler(actionUnit.getFaction().getScheduler());
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		handler.executeTask();
		Expression<?> e = new ReadVariable("True");
		assertEquals(true,e.evaluate(handler));
	}

	@Test
	public final void testCreateWhile() {
		Statement inner = new Print(new True());
		Statement s = new While(new True(),inner);
		Task taskTest = new Task("Task",100,s);
		taskTest.addUnit(actionUnit);
		taskTest.addAsScheduler(actionUnit.getFaction().getScheduler());
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		handler.executeTask();
		assertEquals(inner,handler.getCurrentStatement());
		handler.executeTask();
		assertEquals(s,handler.getCurrentStatement());
		handler.executeTask();
		assertEquals(inner,handler.getCurrentStatement());
		handler.executeTask();
		assertEquals(s,handler.getCurrentStatement());
		handler.executeTask();
		assertTrue(actionUnit.hasTask());
	}

	@Test
	public final void testCreateIf() {
		Statement ifS = new Print(new True());
		Statement elseS = new Print(new Not(new True()));
		Statement s1 = new If(new True(),ifS,elseS);
		
		Task taskTest1 = new Task("Task",100,s1);
		taskTest1.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest1);;
		TaskHandler handler1 = new TaskHandler(actionUnit,world,taskTest1);
		
		handler1.executeTask();
		assertEquals(ifS,handler1.getCurrentStatement());
		handler1.executeTask();
		assertEquals(null,handler1.getCurrentStatement());
		handler1.executeTask();
		assertFalse(actionUnit.hasTask());
		
		Statement s2 = new If(new Not(new True()),ifS,elseS);
		Task taskTest2 = new Task("Task",100,s2);
		taskTest2.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest2);;
		TaskHandler handler2 = new TaskHandler(actionUnit,world,taskTest2);
		
		
		handler2.executeTask();
		assertEquals(elseS,handler2.getCurrentStatement());
		handler2.executeTask();
		assertEquals(null,handler2.getCurrentStatement());
		handler2.executeTask();
		assertFalse(actionUnit.hasTask());
		
	}

//	@Test
//	public final void testCreateBreak() {
//		fail("Not yet implemented");
//	}

	@Test
	public final void testCreatePrint() {
		Statement s = new Print(new True());
		Task taskTest = new Task("Task",100,s);
		
		taskTest.addUnit(actionUnit);
		taskTest.addAsScheduler(actionUnit.getFaction().getScheduler());
		
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		s.execute(handler);
	}

	@Test
	public final void testCreateSequence() {
		Statement ifS = new Print(new True());
		Statement elseS = new Print(new Not(new True()));
		List<Statement> list = Arrays.asList(ifS,elseS);
		Statement s1 = new Sequence(list);
		
		Task taskTest1 = new Task("Task",100,s1);
		taskTest1.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest1);;
		TaskHandler handler1 = new TaskHandler(actionUnit,world,taskTest1);
		
		handler1.executeTask();
		assertEquals(ifS,handler1.getCurrentStatement());
		handler1.executeTask();
		assertEquals(s1,handler1.getCurrentStatement());
		handler1.executeTask();
		assertEquals(elseS,handler1.getCurrentStatement());
		handler1.executeTask();
		assertEquals(s1,handler1.getCurrentStatement());
		handler1.executeTask();
		assertEquals(null,handler1.getCurrentStatement());
		handler1.executeTask();
		assertFalse(actionUnit.hasTask());
		
		
	}

	@Test
	public final void testCreateMoveTo() {
		Statement s = new MoveTo(new LiteralPosition(1,0,0));
		Task taskTest = new Task("Task",100,s);	
		taskTest.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest);
		
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		handler.executeTask();
		assertEquals(null,handler.getCurrentStatement());
		assertTrue(actionUnit.isMoving());
		
		handler.executeTask();
	}

	@Test
	public final void testCreateWork() {
		Statement s = new Work(new LiteralPosition(0,0,0));
		Task taskTest = new Task("Task",100,s);	
		taskTest.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest);
		
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		handler.executeTask();
		assertEquals(null,handler.getCurrentStatement());

		assertTrue(actionUnit.isWorking());
		handler.executeTask();
	}

	@Test
	public final void testCreateFollow() {
		Statement s = new Follow(new Any());
		Task taskTest = new Task("Task",100,s);	
		taskTest.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest);
		
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		handler.executeTask();
		assertEquals(null,handler.getCurrentStatement());

		assertTrue(actionUnit.hasLeader());
		handler.executeTask();
	}

	@Test
	public final void testCreateAttack() {
		Statement s = new Attack(new Enemy());
		Task taskTest = new Task("Task",100,s);	
		taskTest.addUnit(actionUnit);
		actionUnit.getFaction().getScheduler().addAsTask(taskTest);
		
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		handler.executeTask();
		assertEquals(null,handler.getCurrentStatement());

		assertTrue(actionUnit.isAttacking());
		handler.executeTask();
	}
	
	
	//EXPRESSIONS
	
	@Test
	public final void testCreateReadVariable() {
		Statement s = new Assignment("True",new True());
		Task taskTest = new Task("Task",100,s);
		taskTest.addUnit(actionUnit);
		taskTest.addAsScheduler(actionUnit.getFaction().getScheduler());
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		s.execute(handler);
		Expression<?> e = new ReadVariable("True");
		assertEquals(true,e.evaluate(handler));
	}
	
	@Test (expected = Error.class)
	public final void testCreateIllegalReadVariable() {
		Statement s = new Assignment("True",new True());
		Task taskTest = new Task("Task",100,s);
		taskTest.addUnit(actionUnit);
		
		taskTest.addAsScheduler(actionUnit.getFaction().getScheduler());
		TaskHandler handler = new TaskHandler(actionUnit,world,taskTest);
		
		s.execute(handler);
		Expression<?> e = new ReadVariable("False");
		e.evaluate(handler);
	}
	
	@Test
	public final void testCreateIsSolid() {
		Expression<Boolean> e1 = new IsSolid(new LiteralPosition(rock.getX(), rock.getY(), rock.getZ()));
		assertTrue(e1.evaluate(taskHandler));
		Expression<Boolean> e2 = new IsSolid(new LiteralPosition(workshop.getX(), workshop.getY(), workshop.getZ()));
		assertFalse(e2.evaluate(taskHandler));
	}

//	@Test
//	public final void testCreateIsPassable() {
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	public final void testCreateIsFriend() {
		Expression<Boolean> e1 = new IsFriend(new Friend());
		assertTrue(e1.evaluate(taskHandler));
		Expression<Boolean> e2 = new IsFriend(new Enemy());
		assertFalse(e2.evaluate(taskHandler));
		Expression<Boolean> e3 = new IsFriend(new This());
		assertTrue(e3.evaluate(taskHandler));
	}

//	@Test
//	public final void testCreateIsEnemy() {
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	public final void testCreateIsAlive() {
		TaskHandler t = new TaskHandler(actionUnit, world, task);
		
		Expression<Boolean> e1 = new IsAlive(new This());
		assertTrue(e1.evaluate(t));
		
		actionUnit.terminate();
		Expression<Boolean> e2 = new IsAlive(new This());
		assertFalse(e2.evaluate(t));
	}

	@Test
	public final void testCreateCarriesItem() {
		TaskHandler t = new TaskHandler(actionUnit, world, task);
		Expression<Boolean> e1 = new CarriesItem(new This());
		assertFalse(e1.evaluate(t));
		new Log(new int[]{0,0,0},world);
		actionUnit.pickUpItem(log);
		Expression<Boolean> e2 = new CarriesItem(new This());
		assertTrue(e2.evaluate(t));
	}

	@Test
	public final void testCreateNot() {
		Expression<Boolean> e1 = new True();
		assertTrue(e1.evaluate(taskHandler));
		Expression<Boolean> e2 = new Not(e1);
		assertFalse(e2.evaluate(taskHandler));
		
	}

	@Test
	public final void testCreateAnd() {
		Expression<Boolean> e1 = new True();
		Expression<Boolean> e2 = new Not(e1);
		
		Expression<Boolean> e3 = new And(e1,e1);
		Expression<Boolean> e4 = new And(e2,e1);
		Expression<Boolean> e5 = new And(e1,e2);
		Expression<Boolean> e6 = new And(e2,e2);
		
	
		assertTrue(e3.evaluate(taskHandler));
		assertFalse(e4.evaluate(taskHandler));
		assertFalse(e5.evaluate(taskHandler));
		assertFalse(e6.evaluate(taskHandler));
	}

	@Test
	public final void testCreateOr() {
		Expression<Boolean> e1 = new True();
		Expression<Boolean> e2 = new Not(e1);
		
		Expression<Boolean> e3 = new Or(e1,e1);
		Expression<Boolean> e4 = new Or(e2,e1);
		Expression<Boolean> e5 = new Or(e1,e2);
		Expression<Boolean> e6 = new Or(e2,e2);
		
	
		assertTrue(e3.evaluate(taskHandler));
		assertTrue(e4.evaluate(taskHandler));
		assertTrue(e5.evaluate(taskHandler));
		assertFalse(e6.evaluate(taskHandler));
	}

	@Test
	public final void testCreateHerePosition() {
		Expression<CubePosition> e1 = new Here();
		assertEquals(unitPosition,e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateLogPosition() {
		Expression<CubePosition> e1 = new ItemPosition<>(Log.class);
		assertEquals(log.getCubePosition(),e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateBoulderPosition() {
		Expression<CubePosition> e1 = new ItemPosition<>(Boulder.class);
		assertEquals(boulder.getCubePosition(),e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateWorkshopPosition() {
		Expression<CubePosition> e1 = new Workshop();
		assertEquals(workshop,e1.evaluate(taskHandler));
	}

//	@Test
//	public final void testCreateSelectedPosition() {
//		fail("Not yet implemented");
//	}

	@Test
	public final void testCreateNextToPosition() {
		Expression<CubePosition> e1 = new NextToPosition(new Here());
		int[] cube = e1.evaluate(taskHandler).toArray();
		assertTrue(Utils.areAdjacent(unitPosition.toArray(), cube));
	}

	@Test
	public final void testCreatePositionOf() {
		Expression<CubePosition> e1 = new PositionOfUnit(new This());
		Expression<CubePosition> e2 = new Here();
		
		assertEquals(e1.evaluate(taskHandler),e2.evaluate(taskHandler));
	}

	@Test
	public final void testCreateLiteralPosition() {
		Expression<CubePosition> e1 = new LiteralPosition(2, 7, 3);
		assertEquals(new CubePosition(2,7,3),e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateThis() {
		Expression<Unit> e1 = new This();
		assertEquals(unit,e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateFriend() {
		Expression<Unit> e1 = new Friend();
		assertEquals(friend,e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateEnemy() {
		Expression<Unit> e1 = new Enemy();
		assertTrue(enemy == e1.evaluate(taskHandler)|| actionUnit == e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateAny() {
		Expression<Unit> e1 = new Any();
		Unit u = e1.evaluate(taskHandler);
		assertTrue((u==unit)||(u ==friend) || (u== enemy) || (u==actionUnit));
	}

	@Test
	public final void testCreateTrue() {
		Expression<Boolean> e = new True();
		assertTrue(e.evaluate(taskHandler));
	}

	@Test
	public final void testCreateFalse() {
		Expression<Boolean> e = new Not(new True());
		assertFalse(e.evaluate(taskHandler));
		
	}

}
