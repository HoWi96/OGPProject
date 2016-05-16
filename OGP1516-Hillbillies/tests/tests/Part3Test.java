package tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.Expression;
import hillbillies.model.Faction;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.TaskFactory;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.TaskParser;
import hillbillies.statement.Statement;
import ogp.framework.util.ModelException;

public class Part3Test {
	

	private TaskFactory  factory;
	private TaskParser<Expression, Statement, Task> parser;
	private  Facade facade;

	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new TaskFactory();
		parser = TaskParser.create(factory);
		facade = new Facade();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() throws Exception{
		int[][][] types = new int[16][16][16];
		for(int i=0; i<=15;i++){
			types[i][10][9] = TYPE_ROCK;
		}
		for(int i=11; i<=15;i++){
			types[i][10][10] = TYPE_ROCK;
		}

		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 10, 10 }, 50, 50, 50, 50, true);
		assertFalse(unit == null);
		assertFalse(world == null);
		
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
