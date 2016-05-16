package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.Utils;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;


public class WorkingTest {
	
	World world;
	Unit unit;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int[][][] types = new int[2][2][2];
		types[1][1][0] = World.TYPE_ROCK;
		types[0][1][0] = World.TYPE_TREE;
		types[1][0][0] = World.TYPE_WORKSHOP;

		world = new World(types, new DefaultTerrainChangeListener());
		unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testWorkingPickingUpDroppingDown(){
		Log log = new Log(new int[] { 0, 0, 0 },world);
		unit.workAt(new int[] { 0, 0, 0 });
		double timeToWork = 600/unit.getStrength();
		advanceTimeFor(world, timeToWork, 0.2);
		
		assertTrue(unit.hasItem());
		assertTrue(unit.getItem() == log);
		
		unit.workAt(new int[] { 0, 0, 0 });
		advanceTimeFor(world, timeToWork, 0.2);
		
		assertFalse(unit.hasItem());
		assertTrue(unit.getItem() == null);
		assertTrue(Utils.equals(unit.getPosition(),log.getPosition()));

	}
	
	@Test
	public void testWorkingUpgradeWorkshop(){
		Log log = new Log(new int[] { 1, 0, 0 },world);
		
		double timeToWork = 550/unit.getStrength();
		
		unit.workAt(new int[] { 1, 0, 0 });
		int weight = unit.getWeight();
		int toughness = unit.getToughness();
		
		advanceTimeFor(world, timeToWork, 0.2);
		
		assertTrue(log.isTerminated());
		assertFalse(world.hasAsItem(log));
		
		assertTrue(weight+1 == unit.getWeight());
		System.out.println("toughness: "+unit.getToughness());
		assertTrue(toughness+1 == unit.getToughness() || 
				toughness+2 == unit.getToughness() ||
				toughness+3 == unit.getToughness());
	}
	
	@Test
	public void testCaveIn(){
		
	}
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(World world, double time, double step) throws IllegalArgumentException{
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			world.advanceTime(step);
		world.advanceTime(time - n * step);
	}


}
