package tests;

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class SchedulerTest {

	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;

	private Unit unit;
	private World world;
	private Task task1;
	private Task task2;
	private Scheduler scheduler;

	@Before
	public void setUp() throws Exception {
		int[][][] types = new int[3][4][5];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		world = new World(types, new DefaultTerrainChangeListener());
		unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		task1 = new Task("Task1", 1, new List<Statement>());
		task2 = new Task("Task2", 2, new List<Statement>());

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
