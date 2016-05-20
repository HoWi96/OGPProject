package tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.unitExpression.Any;
import hillbillies.expression.unitExpression.Enemy;
import hillbillies.model.Faction;
import hillbillies.model.ITerrainType;
import hillbillies.model.Log;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.TaskFactory;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.helper.CubePosition;
import hillbillies.model.helper.Utils;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.facade.IFacade;

import hillbillies.part3.programs.TaskParser;
import hillbillies.statement.Statement;
import hillbillies.statement.unitStatement.Attack;
import hillbillies.statement.unitStatement.Follow;
import ogp.framework.util.ModelException;

public class Part3Test implements ITerrainType {
	

	private TaskFactory  factory;
	private  Facade facade;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 *Set up before each test a new Facade and TaskFactory
	 */
	@Before
	public void setUp() throws Exception {
		factory = new TaskFactory();
		facade = new Facade();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * A test to let the unit dig a certain amount of blocks, controlled by statements
	 * (White box testing)
	 */
	@Test
	public final void testDigging() throws Exception{
		int[][][] types = new int[16][16][16];
		for(int i=0; i<=15;i++){
			types[i][10][9] = TYPE_ROCK;
		}
		for(int i=11; i<=15;i++){
			types[i][10][10] = TYPE_ROCK;
		}

		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 10, 10 }, 50, 50, 50, 50, true);
		
		world.addUnit(unit);
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);
		

		List<Task> tasks = TaskParser.parseTasksFromFile("resources/tasks/digtunnel.txt", factory, Collections.emptyList());

		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("dig tunnel from (11, 10, 10) to (14, 10, 10)", facade.getName(task));
		// test priority
		assertEquals(1000, facade.getPriority(task));

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 150, 0.02);

		// work task has been executed
		for(int i=11; i<=14;i++)
			assertEquals(TYPE_AIR, facade.getCubeType(world, i, 10, 10));
		// work task is removed from scheduler
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}
	
	/**
	 * A test to let the unit follow another unit, controlled by statements
	 * (white box testing)
	 */
	@Test
	public final void testFollowing() throws Exception{
		int[][][] types = new int[16][16][16];
		for(int i=0; i<=15;i++){
			types[i][10][9] = TYPE_ROCK;
		}
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 10, 10 }, 50, 50, 50, 50, true);
		Unit leader = new Unit("Test", new int[] { 14, 10, 10 }, 50, 50, 50, 50, false);
		
		world.addUnit(unit);
		world.addUnit(leader);
		
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);
		
		
		Statement statement = new Follow(new Any());
		Task task = new Task("follow", 100, statement);
		
		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 20, 0.02);

		// follow task has been executed
		assertTrue(Utils.areAdjacent(unit.getCubePosition().toArray(),leader.getCubePosition().toArray()));
		// follow task is removed from scheduler
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}
	
	/**
	 * A test to let the unit attack another unit, controlled by statements
	 * (white box testing)
	 */
	@Test
	public final void testAttack() throws Exception{
		int[][][] types = new int[16][16][16];
		for(int i=0; i<=15;i++){
			types[i][10][9] = TYPE_ROCK;
		}
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 10, 10 }, 50, 50, 50, 50, true);
		Unit leader = new Unit("Test", new int[] { 1, 10, 10 }, 50, 50, 50, 50, false);
		
		world.addUnit(unit);
		world.addUnit(leader);
		
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);
		
		
		Statement statement = new Attack(new Enemy());
		Task task = new Task("attack", 100, statement);
		
		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 0.06, 0.02);
		assertTrue(unit.hasTask());
		assertTrue(unit.isAttacking());
		advanceTimeFor(facade, world, 2, 0.02);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}
	
	/**
	 * A test to let the unit find a workshop in the world, controlled by statements
	 * 
	 * @throws ModelException
	 */
	@Test
	public void testFindWorkshop() throws ModelException{
		int[][][] types = new int[10][10][10];
		types[0][5][0] = TYPE_WORKSHOP;
		
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		
		Scheduler scheduler = unit.getFaction().getScheduler();
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"move to workshop\"\npriority: 10000\nactivities: moveTo workshop;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		 Task moveToWorkshop = tasks.get(0);
		 
		 scheduler.addAsTask(moveToWorkshop);
		 advanceTimeFor(facade, world, 1, 0.02);
		 assertEquals(moveToWorkshop,unit.getTask());
		 advanceTimeFor(facade, world, 20, 0.02);
		 assertEquals(new CubePosition(0,5,0),unit.getCubePosition());
	}
	
	/**
	 * A test to let the unit find a log in the world, controlled by statements
	 * 
	 * @throws ModelException
	 */
	@Test
	public void testFindLog() throws ModelException{
		int[][][] types = new int[10][10][10];
		
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		new Log(new int[]{0, 5,0},world);
		
		
		Scheduler scheduler = unit.getFaction().getScheduler();
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"move to workshop\"\npriority: 10000\nactivities: moveTo log;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		 Task moveToLog = tasks.get(0);
		 
		 scheduler.addAsTask(moveToLog);
		 advanceTimeFor(facade, world, 1, 0.02);
		 assertEquals(moveToLog,unit.getTask());
		 advanceTimeFor(facade, world, 20, 0.02);
		 assertEquals(new CubePosition(0,5,0),unit.getCubePosition());
	}


	
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(IFacade facade, World world, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			facade.advanceTime(world, step);
		facade.advanceTime(world, time - n * step);
	}

}
