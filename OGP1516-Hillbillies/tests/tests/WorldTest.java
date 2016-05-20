package tests;

import static org.junit.Assert.*;

import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.ITerrainType;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.helper.CubePosition;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class WorldTest implements ITerrainType {

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
	public void testHasSolidAdjacents(){
		int[][][] types = new int[3][3][3];
		
		types[0][0][0] = TYPE_ROCK;
		World world = new World(types, new DefaultTerrainChangeListener());
		assertTrue(world.hasSolidAdjacents(new int[]{0,0,0}));
		assertTrue(world.hasSolidAdjacents(new int[]{1,0,0}));
		assertTrue(world.hasSolidAdjacents(new int[]{1,1,1}));
		
		assertFalse(world.hasSolidAdjacents(new int[]{0,0,2}));
		assertFalse(world.hasSolidAdjacents(new int[]{2,2,1}));
		
		world.caveIn(new int[]{0,0,0});
		assertFalse(world.hasSolidAdjacents(new int[]{1,1,1}));
		
	
	}

	@Test
	public void testquickFindReachableAdjacents() {
		int[][][] types = new int[3][3][3];
		types[0][0][0] = TYPE_ROCK;
		types[2][2][0] = TYPE_ROCK;
		World world = new World(types, new DefaultTerrainChangeListener());
		
		assertEquals(TYPE_ROCK, world.getCubeType(new int[]{0,0,0}));
		assertEquals(TYPE_ROCK, world.getCubeType(new int[]{2,2,0}));
		
		
		assertTrue(!world.isSolidCube(new int[]{2,1,1}));
		assertTrue(!world.isSolidCube(new int[]{1,2,1}));

		assertTrue(world.hasSolidAdjacents(new int[]{2,1,1}));
		assertTrue(world.hasSolidAdjacents(new int[]{1,2,1}));

		
		assertEquals(5,world.quickFindReachableAdjacents(new int[]{1,1,1}).size());
		
		world.caveIn(new int[]{0,0,0});
		
		assertEquals(3,world.quickFindReachableAdjacents(new int[]{1,1,1}).size());
		world.caveIn(new int[]{2,2,0});
		
		assertEquals(1,world.quickFindReachableAdjacents(new int[]{1,1,1}).size());
		
	}
	@Test
	public void testisSolidUnder(){
		int[][][] types = new int[2][2][2];
		types[0][0][0] = TYPE_ROCK;
		World world = new World(types, new DefaultTerrainChangeListener());
		
		assertTrue(world.isSolidUnder(new int[] {0,0,1}));
		assertTrue(world.isSolidUnder(new int[] {0,1,0}));
		assertFalse(world.isSolidUnder(new int[] {0,1,1}));
		
	}
	
	@Test
	public void testisSolidCube(){
		int[][][] types = new int[2][2][2];
		types[0][0][0] = TYPE_ROCK;
		World world = new World(types, new DefaultTerrainChangeListener());
		
		assertTrue(world.isSolidCube(new int[] {0,0,0}));
		assertFalse(world.isSolidCube(new int[] {1,0,0}));

	}
	
	@Test
	public void testcanMoveDirectly(){
		int[][][] types = new int[3][3][3];
		types[0][1][0] = TYPE_ROCK;
		types[1][0][0] = TYPE_ROCK;
		World world = new World(types, new DefaultTerrainChangeListener());
		
		assertFalse(world.canMoveDirectly(new int[] {0,0,0},1,1,0));
		assertTrue(world.canMoveDirectly(new int[] {1,1,0},1,1,0));
	}

	@Test
	public void caveInTest(){
		int[][][] types = new int[3][3][3];
		types[0][1][0] = TYPE_ROCK;
		types[1][0][0] = TYPE_ROCK;
		World world = new World(types, new DefaultTerrainChangeListener());
		world.caveIn(new int[] {0,1,0});
		world.caveIn(new int[] {1,0,0});
		assertEquals(TYPE_AIR,world.getCubeType(new int[] {0,1,0}));
		assertEquals(TYPE_AIR,world.getCubeType(new int[] {1,0,0}));
	}
	
	@Test
	public void getLogTest(){
		Log l = new Log(new int[]{0,0,0}, world1);
		assertEquals(1,world1.getAllItems().size());
		assertEquals(1,world1.getAllLogs().size());
		assertEquals(l, world1.getItemOnPosition(new int[]{0,0,0}));
	}
	
	@Test
	public void getBoulderTest(){
		Boulder b = new Boulder(new int[]{0,0,0}, world1);
		assertEquals(1,world1.getAllItems().size());
		assertEquals(1,world1.getAllBoulders().size());
		assertEquals(b, world1.getItemOnPosition(new int[]{0,0,0}));
	}
	
	@Test
	public void removeItemTest(){
		Log l = new Log(new int[]{0,0,0}, world1);
		assertTrue(world1.hasAsItem(l));
		world1.removeItem(l);
		assertFalse(world1.hasAsItem(l));
	}
	
	@Test
	public void UnitsOnPositionTest(){
		world1.addUnit(unit1);
		assertEquals(unit1,world1.getAllUnitsOnPosition(new int[]{0,0,0}).get(0));
	}
	
	@Test
	public void getAllWorkshopsTest(){
		assertTrue(world1.getAllWorkshops().contains(new CubePosition(1,1,2)));
	}

}
