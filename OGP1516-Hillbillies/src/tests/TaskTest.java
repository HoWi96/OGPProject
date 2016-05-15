package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.positionExpression.LiteralPosition;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.statement.wildcardStatement.Print;

public class TaskTest {

	private static Unit unit;
	private static Scheduler scheduler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		unit = new Unit("UnitOne", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		scheduler = unit.getFaction().getScheduler();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Task task;

	@Before
	public void setUp() throws Exception {
		 task = new Task("task",100,new Print(new LiteralPosition(0,0,0,null),null));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testTask() {
		assertFalse(task.isTerminated());
		assertTrue(task.getAllSchedulers().isEmpty());
		assertEquals("task",task.getName());
		assertEquals(100,task.getPriority());
	}

	@Test
	public final void testTerminate() {
		scheduler.addAsTask(task);
		task.addUnit(unit);
		
		assertTrue(task.hasUnit());
		assertTrue(task.hasAsScheduler(scheduler));
		assertTrue(unit.hasTask());
		assertTrue(scheduler.hasAsTask(task));
		assertTrue(!task.isTerminated());
		
		task.terminate();
		
		assertTrue(!task.hasUnit());
		assertTrue(!task.hasAsScheduler(scheduler));
		assertTrue(!unit.hasTask());
		assertTrue(!scheduler.hasAsTask(task));
		assertTrue(task.isTerminated());
		

	}

	@Test
	public final void testIsValidPriority() {
		assertFalse(Task.isValidPriority(Integer.MIN_VALUE+100));
		assertFalse(Task.isValidPriority(Integer.MIN_VALUE));
		assertTrue(Task.isValidPriority(Integer.MIN_VALUE+200));
	}

	@Test
	public final void testSetPriority() {
		task.setPriority(Integer.MIN_VALUE+200);
		task.setPriority(task.getPriority()-100);
		assertEquals(Integer.MIN_VALUE+200, task.getPriority());
	}
	
	
	@Test
	public final void testCanHaveAsActivity() {
		assertFalse(task.canHaveAsActivity(null));
	}

	@Test
	public final void testCanHaveAsUnit() {
		assertTrue(task.canHaveAsUnit(null));
		assertTrue(task.canHaveAsUnit(unit));
	}

	@Test
	public final void testAddAndRemoveUnit() {
		task.addUnit(unit);
		assertEquals(unit,task.getUnit());
		assertEquals(task,unit.getTask());
		assertTrue(task.hasUnit());
		assertTrue(unit.hasTask());
		task.removeUnit();
		assertEquals(null,task.getUnit());
		assertEquals(null,unit.getTask());
		assertTrue(!task.hasUnit());
		assertTrue(!unit.hasTask());
	}

}
