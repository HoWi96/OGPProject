package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.Expression;
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
import hillbillies.statement.Statement;
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

	@Test
	public final void testCreateTasks() {
		fail("Not yet implemented"); // TODO
	}

	//STATEMENTS
	
	@Test
	public final void testCreateAssignment() {
		
	}

	@Test
	public final void testCreateWhile() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateIf() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
//	public final void testCreateBreak() {
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	public final void testCreatePrint() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateSequence() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateMoveTo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateWork() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateFollow() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateAttack() {
		fail("Not yet implemented"); // TODO
	}

	//EXPRESSIONS
	
	@Test
	public final void testCreateReadVariable() {
		fail("Not yet implemented"); // TODO
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
		Expression<CubePosition> e1 = new LogPosition();
		assertEquals(log.getCubePosition(),e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateBoulderPosition() {
		Expression<CubePosition> e1 = new BoulderPosition();
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
		assertEquals(enemy,e1.evaluate(taskHandler));
	}

	@Test
	public final void testCreateAny() {
		Expression<Unit> e1 = new Any();
		Unit u = e1.evaluate(taskHandler);
		assertTrue((u==unit)||(u ==friend) || (u== enemy));
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
