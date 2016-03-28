package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class WorldTest {
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;

	private World world1;

	@Before
	public void setUp() throws Exception {
		int[][][] types = new int[3][4][5];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		world1 = new World(types, new DefaultTerrainChangeListener());
		
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
		assertEquals(TYPE_ROCK, world1.getcubeType(position));
		assertEquals(TYPE_TREE, world1.getcubeType(new int[]{1,1,1}));
		assertEquals(TYPE_WORKSHOP, world1.getcubeType(new int[]{1,1,2}));
		assertEquals(TYPE_AIR, world1.getcubeType(new int[]{0,0,0}));
		
		assertTrue(world1.isSolidCube(position));
		world1.setcubeType(TYPE_AIR,position);
		assertEquals(TYPE_AIR, world1.getcubeType(position));
		assertTrue(!world1.isSolidCube(position));
	}

	@Test
	public void test1() {	
	}

	@Test
	public void test2() {
	}


}
