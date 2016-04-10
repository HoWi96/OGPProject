package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class PathFindingTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testPathFound(){
		int[][][] types = new int[4][4][4];
		types[0][0][0] = 1;
		types[1][0][0] = 1;
		types[1][0][1] = 1;
		types[1][1][1] = 1;
		types[1][2][1] = 1;
		types[2][2][1] = 1;
		types[2][2][2] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 0, 0, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveTo(new int[] {3,3,3});
		advanceTimeFor(world, 10, 0.1);
		//TODO make toString for positions!
		System.out.println(" "+unit.getPosition()[0]+" "+unit.getPosition()[1]+" "+unit.getPosition()[2]);
		boolean reachedEnd = (
				unit.getPosition()[0]==3.5 && 
				unit.getPosition()[1]==3.5 &&
				unit.getPosition()[2]==3.5);
		assertTrue("end position reached", reachedEnd);
	}
	

	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(World world, double time, double step){
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			world.advanceTime(step);
		world.advanceTime(time - n * step);
	}

}
