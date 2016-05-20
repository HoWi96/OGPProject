package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.expression.booleanExpression.True;
import hillbillies.expression.positionExpression.LiteralPosition;
import hillbillies.model.Faction;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;

import hillbillies.statement.wildcardStatement.Print;

public class SchedulerTest {

	private static Unit unit1, unit2;
    private static Faction faction1, faction2;
    private static Scheduler scheduler1, scheduler2;
    private static Task task1, task2;

    @BeforeClass
    public static void setUpClass() throws Exception {
		unit1 = new Unit("UnitOne", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		unit2 = new Unit("UnitTwo", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
        faction1 = unit1.getFaction();
        faction2 = unit2.getFaction();
        scheduler1 = faction1.getScheduler();
        scheduler2 = faction2.getScheduler();
        task1 = new Task("task1",100,new Print(new LiteralPosition(0,0,0)));
        task2 = new Task("task2",200,new Print(new LiteralPosition(0,0,0)));
    }


	@Before
	public void setUp() throws Exception {
	     if(scheduler1.hasAsTask(task1))
	            scheduler1.removeAsTask(task1);
	     if(scheduler1.hasAsTask(task2))
	            scheduler1.removeAsTask(task2);
	     if(scheduler2.hasAsTask(task1))
	          	scheduler2.removeAsTask(task1);
	     if(scheduler2.hasAsTask(task2))
	    	 	scheduler2.removeAsTask(task2);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the constructor of scheduler
	 */
	@Test
	public void testConstructor(){
		Faction f = new Faction();
		Scheduler s = f.getScheduler();
		assertEquals(0,s.getNbTasks());
		assertTrue(s.getHighestPriorityAssignableTask()==null);
		assertFalse(s.getAllTasksIterator().hasNext());	
	}
	
	/**
	 * Test the method to add a task
	 */
	@Test
	public void testTaskAdder(){
		
		assertFalse(scheduler1.hasAsTask(null));
		assertFalse(task1.hasAsScheduler(null));
		
        assertFalse(scheduler1.hasAsTask(task1));
        assertFalse(task1.hasAsScheduler(scheduler1));
        
        scheduler1.addAsTask(task1);
        assertTrue(scheduler1.hasAsTask(task1));
        assertTrue(task1.hasAsScheduler(scheduler1));
        
        Collection<Task> tasks = Arrays.asList(task1, task2);
        assertFalse(scheduler2.hasAsTasks(tasks));
        scheduler2.addAsTask(task1);
        scheduler2.addAsTask(task2);
        assertTrue(scheduler2.hasAsTasks(tasks));
        assertTrue(task1.hasAsScheduler(scheduler1));
        assertTrue(task1.hasAsScheduler(scheduler2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSameTaskAdder() throws Exception {
		scheduler1.addAsTask(task1);
		scheduler1.addAsTask(task1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullTaskAdder() throws Exception {
		scheduler1.addAsTask(null);
	}
	
	/**
	 * Method to test if a task is succesfully removed
	 */
	@Test
	public void testTaskRemover(){
		Collection<Task> tasks = Arrays.asList(task1, task2);
        scheduler2.addAsTask(task1);
        scheduler2.addAsTask(task2);
        assertTrue(scheduler2.hasAsTasks(tasks));
        assertTrue(task1.hasAsScheduler(scheduler2));
        assertTrue(task2.hasAsScheduler(scheduler2));
        scheduler2.removeAsTask(task1);
        assertFalse(scheduler2.hasAsTasks(tasks));
        assertFalse(task1.hasAsScheduler(scheduler2));
        assertTrue(task2.hasAsScheduler(scheduler2));
	}
	
	/**
	 * Method to test if a task is succesfully replaced
	 */
	@Test
	public void testTaskReplacer(){
        scheduler2.addAsTask(task1);
        assertTrue(scheduler2.hasAsTask(task1));
        Task task3 = new Task("task3", 300, new Print(new True()));
        scheduler2.replaceTask(task1, task3);
        assertFalse(scheduler2.hasAsTask(task1));
        assertTrue(scheduler2.hasAsTask(task3));
        scheduler2.removeAsTask(task3);
        assertFalse(scheduler2.hasAsTask(task3));

	}
	

	@Test
    public void testCanHaveAsTask() throws Exception {
        assertTrue(scheduler1.canHaveAsTask(task1));
        assertTrue(scheduler1.canHaveAsTask(task2));
        assertFalse(scheduler1.canHaveAsTask(null));
    }
	
	 @Test
	 public void testGetNbTasks() throws Exception{
	        assertEquals(0, scheduler2.getNbTasks());
	        scheduler1.addAsTask(task1);
	        scheduler1.addAsTask(task2);
	        assertEquals(2, scheduler1.getNbTasks());
	        scheduler1.removeAsTask(task1);
	        assertEquals(1, scheduler1.getNbTasks());
	  }
	 
	 @Test
	 public void testGetHighestPriority() throws Exception{
		 scheduler1.addAsTask(task1);
		 scheduler1.addAsTask(task2);
		 assertEquals(task2,scheduler1.getHighestPriorityAssignableTask());
	 }
	 
	 /**
	  * test if the iterator returns everything in descending order
	  */
	 @Test
	 public void testGetAllTasksIterator() throws Exception{
		 Task task3 = new Task("task1",300,new Print(new LiteralPosition(0,0,0)));
	     Task task4 = new Task("task2",400,new Print(new LiteralPosition(0,0,0)));
		 scheduler1.addAsTask(task4);
		 scheduler1.addAsTask(task2);
		 scheduler1.addAsTask(task3);
		 scheduler1.addAsTask(task1);
		 Iterator<Task> i = scheduler1.getAllTasksIterator();
		 int priority = 1000;
		 while(i.hasNext()){
			 
			 Task t = i.next();
			 assertTrue(t.getPriority()<priority);
			 priority = t.getPriority();
		 }
		 //DO NOT FORGET REMOVAL!
		 scheduler1.removeAsTask(task3);
		 scheduler1.removeAsTask(task4);
	 }
	 
	 
	 //CONDITIONS TO TEST
	 	public final Predicate<Task> priorityIsHigherThan200() {
	        return t-> t.getPriority() > 200 ;
	    }
	     
	    public final  Predicate<Task> priorityIsLowerThan200() {
	        return t-> t.getPriority() < 200 ;
	    }
	     
	    public final Predicate<Task> priorityIs200() {
	        return t-> t.getPriority()==200;
	    }
	 	
	 /**
	  * Test the conditions given by the predicates
	  */
	 @Test
	 public void testGetAllTasksSatisfying() throws Exception{

		 
		 Task task3 = new Task("task3",300,new Print(new LiteralPosition(0,0,0)));
	     Task task4 = new Task("task4",400,new Print(new LiteralPosition(0,0,0)));
		 scheduler1.addAsTask(task4);
		 scheduler1.addAsTask(task2);
		 scheduler1.addAsTask(task3);
		 scheduler1.addAsTask(task1);
		 
		 Collection<Task> c = scheduler1.getAllTasksSatisfying(priorityIsHigherThan200());
		 assertTrue(c.contains(task3));
		 assertTrue(c.contains(task4));
		 assertEquals(2, c.size());
		 
		 Collection<Task> c2 = scheduler1.getAllTasksSatisfying(priorityIsLowerThan200());
		 assertTrue(c2.contains(task1));
		 assertEquals(1, c2.size());
		 
		 Collection<Task> c3 = scheduler1.getAllTasksSatisfying(priorityIs200());
		 assertTrue(c3.contains(task2));
		 assertEquals(1, c3.size());
		 
		 //DO NOT FORGET REMOVAL!
		 scheduler1.removeAsTask(task3);
		 scheduler1.removeAsTask(task4);
	 }
	
	
	
	
	
	
	

}
