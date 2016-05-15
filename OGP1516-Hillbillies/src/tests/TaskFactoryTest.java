package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.booleanExpression.IsSolid;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.TaskHandler;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

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
	private static TaskHandler expressionTaskHandler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int[][][] types = new int[3][4][5];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
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
		faction = unit.getFaction();
		faction.addUnit(friend);
		log = new Log(new int[] { 2, 2, 0 }, world);
		boulder = new Boulder(new int[] { 2, 3, 0 }, world);
		expressionTaskHandler = new TaskHandler(unit, world, null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateTasks() {
		fail("Not yet implemented"); // TODO
	}

	//STATEMENTS
	
	@Test
	public final void testCreateAssignment() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateWhile() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateIf() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateBreak() {
		fail("Not yet implemented"); // TODO
	}

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

	@Test
	public final void testCreateReadVariable() {
		fail("Not yet implemented"); // TODO
	}

	//EXPRESSIONS
	
	@Test
	public final void testCreateIsSolid() {
		Expression<Boolean> e = new IsSolid(null, null);
	}

	@Test
	public final void testCreateIsPassable() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateIsFriend() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateIsEnemy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateIsAlive() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateCarriesItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateNot() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateAnd() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateOr() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateHerePosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateLogPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateBoulderPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateWorkshopPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateSelectedPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateNextToPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreatePositionOf() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateLiteralPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateThis() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateFriend() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateEnemy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateAny() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateTrue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreateFalse() {
		fail("Not yet implemented"); // TODO
	}

}
