package model;

import static org.junit.Assert.*;

import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class WorldTest {
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;

	private World world1;
	private Unit unit1;

	@Before
	public void setUp() throws Exception {
		int[][][] types = new int[3][4][5];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		world1 = new World(types, new DefaultTerrainChangeListener());
		unit1 = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCorrectDimensions() {
		assertEquals(3, world1.getNbCubesX());
		assertEquals(4, world1.getNbCubesY());
		assertEquals(5, world1.getNbCubesZ());	
	}
	@Test
	public void testCorrectTypes() {
		int[] position = new int[] {1,1,0};
		assertEquals(TYPE_ROCK, world1.getCubeType(position));
		assertEquals(TYPE_TREE, world1.getCubeType(new int[]{1,1,1}));
		assertEquals(TYPE_WORKSHOP, world1.getCubeType(new int[]{1,1,2}));
		assertEquals(TYPE_AIR, world1.getCubeType(new int[]{0,0,0}));
		
		assertTrue(world1.isSolidCube(position));
		world1.setcubeType(TYPE_AIR,position);
		assertEquals(TYPE_AIR, world1.getCubeType(position));
		assertTrue(!world1.isSolidCube(position));
	}
	
	@Test
	public void testInitialCaveIn(){
		int[][][] types = new int[4][4][4];
		types[1][1][1] = TYPE_ROCK;
		types[2][2][2] = TYPE_TREE;
		types[1][1][2] = TYPE_ROCK;
		
		World world1 = new World(types, new DefaultTerrainChangeListener());
		assertEquals(TYPE_AIR, world1.getCubeType(new int[]{1,1,1}));
		assertEquals(TYPE_AIR, world1.getCubeType(new int[]{1,1,2}));
		assertEquals(TYPE_AIR, world1.getCubeType(new int[]{2,2,2}));
	}

	@Test
	public void testAddUnitToWorld() {
		world1.addUnit(unit1);
		assertTrue(world1.hasAsUnit(unit1));
		assertTrue(unit1.getWorld() == world1);
		world1.removeUnit(unit1);
		assertFalse(world1.hasAsUnit(unit1));
		assertTrue(unit1.getWorld() == null);
		assertTrue(world1.getNbUnits() == 0);
	}

	@Test
	public void test2() {
	}


}
