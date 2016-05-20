package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Item;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.helper.Utils;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class ItemTest {
	
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
	public void testItemConstructor(){
		Item i = new Log(world.getRandomPositionForUnit(),world);
		assertEquals(world, i.getWorld());
		assertTrue(Item.isValidWeight(i.getWeight()));
	}

	

	@Test
	public void testItemInWorld() {
		Log log = new Log(world.getRandomPositionForUnit(),world);
		
		assertTrue(world.canHaveAsItem(log));
		assertTrue(world.hasAsItem(log));
		assertTrue(world.hasProperItems());
		assertEquals(world.getNbItems(),1);
		
		int[] logPosition = Utils.getCubePosition(log.getPosition());
		assertEquals(world.getItemOnPosition(logPosition), log);
		
		world.removeItem(log);
		assertTrue(world.canHaveAsItem(log));
		assertTrue(world.hasProperItems());
		assertEquals(world.getNbItems(),0);

	}
	/**
	 * Let the unit add an item, and remove it again
	 */
	@Test
	public void testItemWithUnit(){
		
		Log log = new Log(new int[] { 0, 0, 0 },world);
		
		assertTrue(log.canHaveAsUnit(unit));
		assertTrue(unit.canHaveAsItem(log));
		
		unit.addItem(log);
		
		assertTrue(unit.hasItem());
		assertTrue(unit.canHaveAsItem(log));
		assertTrue(unit.getItem() == log);
		assertTrue(log.getUnit() == unit);
		
		unit.removeItem(log);
		
		assertFalse(unit.hasItem());
		assertTrue(unit.canHaveAsItem(log));
		assertFalse(unit.getItem() == log);
		assertFalse(log.getUnit() == unit);

	}
	
	/**
	 * Let unit pick up an item, and let it drop again
	 */
	@Test
	public void testItemWithUnitInWorld(){
		Log log = new Log(new int[] { 0, 0, 0 },world);
		assertTrue(world.hasAsItem(log));
		assertFalse(unit.hasItem());
		
		unit.pickUpItem(log);
		assertFalse(world.hasAsItem(log));
		assertTrue(unit.hasItem());
		
		unit.dropItem(log, new double[] { 1, 0, 0 });
		assertTrue(world.hasAsItem(log));
		assertFalse(unit.hasItem());
		assertEquals(log, world.getItemOnPosition(new int[] { 1, 0, 0 }));
		
	}
	
	@Test
	public void testIsValidWeight(){
		assertTrue(Log.isValidWeight(10));
		assertTrue(Log.isValidWeight(50));
		
		assertFalse(Log.isValidWeight(9));
		assertFalse(Log.isValidWeight(51));
	}
	
	@Test
	public void testFalling(){
		Log log = new Log(new int[] { 0, 0, 1 },world);
		log.advanceTime(0.4);
		assertTrue(Utils.equals(Utils.getCubePosition(log.getPosition()),new int[] { 0, 0, 0 }));
	}
	
	/**
	 * Let unit pick up an item, let him die. It has to be dropped
	 */
	@Test
	public void testUnitDiesDropsItem(){
		Log log = new Log(new int[] { 0, 0, 0 },world);
		unit.pickUpItem(log);
		unit.setPosition(Utils.getCubeCenter(new int[] { 0, 0, 1 }));
		unit.terminate();
		assertTrue(Utils.equals(unit.getPosition(),log.getPosition()));
		assertFalse(log.hasUnit());
	}
}
