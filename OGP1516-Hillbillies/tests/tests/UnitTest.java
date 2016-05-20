
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
import ogp.framework.util.Util;

public class UnitTest {
	private Unit unit1;
	private Unit unit2;
	private Unit unit3;
	private Unit unit4;
	private Unit defender;
	private World world;

	static int[] position = {1, 2, 3};
	static int[] groundPosition = {0,0,0};
	static int[] groundPosition2 = {4,4,0};
	static int[] targetPosition = {2, 3, 4};
	static int[] targetPosition2 = {4,4,4};
	static double[] targetPositionDouble = {3, 4, 5};
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Set up a collection of mutable units in a mutable world,
	 *  which are added to the world
	 * 
	 * @post a unit 1 is created and added to the world
	 * @post a unit 2 is created and added to the world
	 * @post a unit 3 is created and added to the world
	 * @post a unit 4 is created
	 * @post a unit defender is created and added to the world
	 * @post a new world is created
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		unit1 = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, false);
		unit2 = new Unit("UnitStrong",position, 100,100,100,100, false);
		unit3 = new Unit("Baba 'O Reil", groundPosition, 50, 50, 50, 50, false);
		unit4 = new Unit("UnitWeak",position, 25, 25, 25 ,25, false);
		int[][][] types = new int[5][5][5];
		world = new World(types, new DefaultTerrainChangeListener());
		world.addUnit(unit1);
		world.addUnit(unit2);
		world.addUnit(unit3);
		
    	defender = new Unit("Baba 'O Reil", new int[]{50,50,50}, 50, 50, 50, 50, true);
    	world.addUnit(defender);
		
    }

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test to check if the static method isValidName
	 *  handles the given strings correctly
	 */
	@Test
	public void testIsValidName() {
		assertTrue(Unit.isValidName("Baba 'O Reily"));
		assertTrue(Unit.isValidName("A '\"\'"));
		
		assertFalse(Unit.isValidName("B.' 5"));
		assertFalse(Unit.isValidName("B"));
		assertFalse(Unit.isValidName("3azerty"));
		assertFalse(Unit.isValidName("lol"));
	}
	
	/**
	 * Test to check if the instance method isValidPosition handles the
	 * given positions correctly
	 */
	 @Test
	    public void testIsValidPosition(){
		 	assertTrue(unit1.isValidPosition(new double[]{0,0,0}));
	        assertTrue(unit1.isValidPosition(new double[]{4,4,4}));
	        
	        assertFalse(unit1.isValidPosition(new double[]{-1,0,0}));
	        assertFalse(unit1.isValidPosition(new double[]{5,5,5})); 
	        // unit4 does not belong to a world
	        assertTrue(unit4.isValidPosition(new double[]{-1,-10,-5}));
	    }
	 
	 /**
		 * Test to check if the static method isValidStrength
		 *  handles the given weights correctly
		 */
	 @Test
	    public void testIsValidStrength(){
		 	assertTrue(Unit.isValidStrength(1));
		 	assertTrue(Unit.isValidStrength(200));
		 	
	        
	        assertFalse(Unit.isValidStrength(0));
	        assertFalse(Unit.isValidStrength(201));    
	    }
	 
	 @Test
	    public void testIsValidAgility(){
		 	assertTrue(Unit.isValidAgility(1));
		 	assertTrue(Unit.isValidAgility(200));
	        
	        assertFalse(Unit.isValidAgility(0));
	        assertFalse(Unit.isValidAgility(201));
	 }
	
	 @Test
	    public void testIsValidToughness(){
		 	assertTrue(Unit.isValidToughness(200));
	        assertTrue(Unit.isValidToughness(1));
	        
	        assertFalse(Unit.isValidToughness(0));
	        assertFalse(Unit.isValidToughness(201));
	        
	    }
	 
	 @Test
	    public void testIsValidWeight(){
		 
		 	assertTrue(unit1.isValidWeight(50));
		 	assertTrue(unit1.isValidWeight(200));
		 	assertTrue(unit2.isValidWeight(200));
		 	assertTrue(unit4.isValidWeight(25));
		 	
	        assertFalse(unit1.isValidWeight(0));
	        assertFalse(unit1.isValidWeight(49));
	        assertFalse(unit2.isValidWeight(201)); 
	        assertFalse(unit4.isValidWeight(-5));   
	    }
	  @Test
	    public void testIsValidStamina(){
		  
		  	assertTrue(unit1.isValidStamina(50));
		  	assertTrue(unit1.isValidStamina(0));
		  	assertTrue(unit2.isValidStamina(200));
		  	assertTrue(unit4.isValidStamina(1));
		  	
	        assertFalse(unit1.isValidStamina(-1));
	        assertFalse(unit2.isValidStamina(201));
	        assertFalse(unit4.isValidStamina(26)); 
	    }
	  

	    @Test
	    public void testIsValidHitpoints(){
	    	assertTrue(unit1.isValidHitpoints(50));
		  	assertTrue(unit1.isValidHitpoints(0));
		  	assertTrue(unit2.isValidHitpoints(200));
		  	assertTrue(unit4.isValidHitpoints(1));
		  	
	        assertFalse(unit1.isValidHitpoints(-1));
	        assertFalse(unit2.isValidHitpoints(201));
	        assertFalse(unit4.isValidHitpoints(26)); 

	    }
	    
	    @Test
	    public void testIsValidOrientation(){
	    	assertTrue(Unit.isValidOrientation(0));

	    	assertFalse(Unit.isValidOrientation(7));
	    	assertFalse(Unit.isValidOrientation(-5));
	    	assertFalse(Unit.isValidOrientation(39));
	    	assertFalse(Unit.isValidOrientation(2*(float)Math.PI));
	    }
	    
	    @Test
	    public void testGetCubePosition(){
	    	double[] position = {1.4, 2.3, 3.2};
	    	int[] positionInt = {(int)position[0], (int)position[1], (int)position[2]};
	    	int[] position2 = Utils.getCubePosition(position);
	    	assertTrue(position2[0] == positionInt[0]);
	    	assertTrue(position2[1] == positionInt[1]);
	    	assertTrue(position2[2] == positionInt[2]);
	    }
	    
	    @Test
	    public void testGetMaxHitpoints(){
	    	assertTrue(unit1.getMaxHitpoints() == 50);
	    	assertTrue(unit2.getMaxHitpoints() == 200);
	    	assertTrue(unit4.getMaxHitpoints() == 13 );
	    }
	    
	    @Test
	    public void testGetMaxStamina(){
	    	assertTrue(unit1.getMaxStamina() == 50);
	    	assertTrue(unit2.getMaxStamina() == 200);
	    	assertTrue(unit4.getMaxStamina() == 13);
	    }
//	    PRIVATE
//	    @Test
//	    public void testUpdateSpeed(){
//	    	
//	    	unit3.updateSpeed(0);
//	    	assertTrue(unit3.getSpeed() == 0);
//	    	//unit.setCurrentActivity(Activity.MOVING);
//	    	unit3.moveTo(groundPosition2);
//	    	unit3.updateSpeed(-1);
//	    	assertTrue(Util.fuzzyEquals(unit3.getSpeed(), 1.8));
//	    	unit3.updateSpeed(0);
//	    	assertTrue(Util.fuzzyEquals(unit3.getSpeed(), 1.5));
//	    	unit3.updateSpeed(1);
//	    	assertTrue(Util.fuzzyEquals(unit3.getSpeed(),0.75));
//	    }
//	    
	    
	    @Test
	    public void testGetNextPosition(){
	    	unit1.moveToAdjacent(1,1,1);
	    	double[] next = unit1.getNextPosition();
	    	assertTrue(Utils.equals(next, Utils.getCubeCenter(targetPosition)));
	    }
	    
	    @Test
	    public void testGetStep(){
	    	unit1.moveToAdjacent(1, 0, -1);
	    	int[] step2 = unit1.getStep();
	    	assertTrue(step2[0] == 1);
	    	assertTrue(step2[1] == 0);
	    	assertTrue(step2[2] == -1);
	    }
	    /*
	     * PRIVATE
	    @Test
	    public void testSetTargetPosition(){
	    	unit.setTargetPosition(targetPositionDouble);
	    	double[] target = unit.getTargetPosition();
	    	assertTrue(target[0] == 3);
	    	assertTrue(target[1] == 4);
	    	assertTrue(target[2] == 5);
	    }
	    */
	    /*
	     * PRIVATE
	    @Test
	    public void testSetNextPosition(){
	    	unit.setNextPosition(targetPositionDouble);
	    	double[] next = unit.getNextPosition();
	    	assertTrue(next[0] == 3);
	    	assertTrue(next[1] == 4);
	    	assertTrue(next[2] == 5);
	    }
	    */
	    @Test 
	    public void testMoveToTarget(){
	    	unit3.moveTo(groundPosition2);
	    	
	    	while(unit3.isMoving())
	    		unit3.advanceTime(0.2);
	    	int[] current = Utils.getCubePosition(unit3.getPosition());
	    	assertTrue(Utils.equals(current, groundPosition2));
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testIllegalArgumentExceptionMoveToTarget(){
	    	int[] position = {-3, 5, 70};
	    	unit1.moveTo(position);
	    }
	    
	    @Test
	    public void testMoveToAdjacent(){
	    	unit1.moveToAdjacent(-1, 0, 1);
	    	int[] step = unit1.getStep();
	    	assertTrue(step[0] == -1);
	    	assertTrue(step[1] == 0);
	    	assertTrue(step[2] == 1);
	    	assertTrue(unit1.getSpeed() == 0.75);
	    	assertTrue(Util.fuzzyEquals(unit1.getOrientation(), Math.PI));
	    	double[] next = unit1.getNextPosition();
	    	assertTrue(next[0] == 0.5);
	    	assertTrue(next[1] == 2.5);
	    	assertTrue(next[2] == 4.5);
	    }
	    
	    @Test
	    public void testGetIntermediatePosition(){
	    	unit1.setSpeed(1);
	    	double[] newPosition = unit1.getIntermediatePosition(-1, 0, 0, 0.5);
	    	assertTrue(Util.fuzzyEquals(newPosition[0],1));
	    	double[] newPosition2 = unit1.getIntermediatePosition(0, 1, 0, 0.5);
	    	assertTrue(Util.fuzzyEquals(newPosition2[1], 3));
	    	double[] newPosition3 = unit1.getIntermediatePosition(0, 0, 1, 0.5);
	    	assertTrue(Util.fuzzyEquals(newPosition3[2], 4));
	    }
	    
	    @Test
	    public void testIsMoving(){
	    	assertFalse(unit1.isMoving());
	    	unit1.moveToAdjacent(1, 0, -1);
	    	assertTrue(unit1.isMoving());
	    }
	    
	    @Test
	    public void testIsWorking(){
	    	assertFalse(unit1.isWorking());
	    	unit1.workAt(Utils.getCubePosition(unit1.getPosition()));
	    	assertTrue(unit1.isWorking());
	    }
	    
	    @Test
	    public void testIsResting(){

	    	assertFalse(unit3.isResting());
	    	unit3.moveTo(groundPosition2);
	    	unit3.startSprinting();
	    	unit3.advanceTime(0.2);
	    	unit3.rest();
	    	assertTrue(unit3.isResting());
	    }
	    
	    @Test
	    public void testIsAttacking(){
	    	assertFalse(unit1.isAttacking());
	    	Unit defender = new Unit("Baba 'O Reil", position, 50, 50, 50, 50, true);
	    	unit1.attack(defender);
	    	assertTrue(unit1.isAttacking());
	    }
	    
		@Test
	    public void testAttack(){
	    	unit2.attack(unit4);
	    	assertTrue(unit2.isAttacking());
	    	assertEquals(Math.atan2(0, 0),(double)unit4.getOrientation(),0.01);
	    	assertTrue(unit4.isDoingNothing());
	    }
	    
	    @Test
	    public void testDefend(){
	    	double[] prevPosition = unit4.getPosition();
	    	int prevHitpoints = unit4.getHitpoints();
	    	unit2.attack(unit4);
	    	double[] postPosition = unit4.getPosition();
	    	int postHitpoints = unit4.getHitpoints();
	    	assertTrue(Utils.inBetween(new double[] {-1,-1,-1},new double[] {1,1,1},Utils.addPositionsFactor(prevPosition, postPosition, -1))
	    			|| (prevHitpoints-postHitpoints == (int) unit2.getStrength()/10));
	    }
	    
	    @Test
	    public void testHasDefaultBehavior(){
	    	unit1.startDefaultBehavior();
	    	assertTrue(unit1.hasDefaultBehavior());
	    	unit1.stopDefaultBehaviour();
	    	assertFalse(unit1.hasDefaultBehavior());
	    }
	    
	    @Test
	    public void testIsSprinting(){
	    	assertFalse(unit1.isSprinting());
	    	unit1.moveToAdjacent(1, 1, 1);
	    	unit1.startSprinting();
	    	assertTrue(unit1.isSprinting());
	    }
	    
	    @Test
	    public void testIsAbleToMove(){
	    	assertTrue(unit1.isAbleToMove());
	    }
	    
	    @Test
	    public void testIsAbleToSprint(){
	    	
	    	assertFalse(unit3.isAbleToSprint());
	    	unit3.moveTo(groundPosition2);
	    	assertTrue(unit3.isAbleToSprint());
	    	unit3.startSprinting();
	    	unit3.terminate();
	    	assertFalse(unit3.isAbleToSprint());
	    	
	    }
	    
	    @Test
	    public void testIsAbleToAttack(){
	    	Unit defender1 = new Unit("Baba 'O Reil", new int[]{2,2,3}, 50, 50, 50, 50, true);
	    	//Unit defender2 = new Unit("Baba 'O Reil", new int[]{1,2,4}, 50, 50, 50, 50, true);
	    	Unit defender3 = new Unit("Baba 'O Reil", new int[]{2,3,3}, 50, 50, 50, 50, true);
	    	Unit defender4 = new Unit("Baba 'O Reil", new int[]{20,30,3}, 50, 50, 50, 50, true);
	    	assertTrue(unit1.isAbleToAttack(defender1));
	    	assertTrue(defender3.isAbleToAttack(defender1));
	    	assertTrue(unit1.isAbleToAttack(defender3));
	    	assertFalse(unit1.isAbleToAttack(defender4));
	    }
	    
	    /**
	     * Test to check if the right exception is thrown
	     *  when an invalid argument is given
	     */
	    @Test(expected = IllegalArgumentException.class)
	    public void testSetNameIllegalArgumentException(){
	    	unit1.setName("3azerty");
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testSetPositionException(){
	    	unit1.setPosition(new double[]{1,2,60});
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testAdvanceTimeException(){
	    	unit1.advanceTime(1);
	    }
	    
	    //PRIVATE METHODS
//	    @Test(expected = IllegalArgumentException.class)
//	    public void testSetTargetPosition(){
//	    	unit1.setTargetPosition(new double[]{1,3,-1});
//	    }
//	    
//	    @Test(expected = IllegalArgumentException.class)
//	    public void testSetNextPosition(){
//	    	unit1.setNextPosition(new double[]{-4,3,5});
//	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testMoveToTargetIllegalArgumentException(){
	    	unit1.moveTo(new int[]{1,60,3});
	    }
	    
	    @Test(expected = IllegalStateException.class)
	    public void testMoveToTargetIllegalStateException(){
	    	Unit defender = new Unit("Baba 'O Reil", position, 50, 50, 50, 50, true);
	    	world.addUnit(defender);
	    	unit1.attack(defender);
	    	unit1.moveTo(groundPosition);
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testMoveToAdjecentIllegalArgumentException1(){
	    	unit1.moveToAdjacent(2, 1, 1);
	    }
	    
	    @Test(expected = IllegalStateException.class)
	    public void testMoveToAdjecentIllegalStateException(){
	    	Unit defender = new Unit("Baba 'O Reil", position, 50, 50, 50, 50, true);
	    	unit1.attack(defender);
	    	unit1.moveToAdjacent(0, 1, 0);
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testMoveToAdjecentIllegalArgumentException2(){
	    	System.out.println("world of defender "+defender.getWorld());
	    	System.out.println("position "+defender.getPosition());
	    	defender.moveToAdjacent(-1, -1, -1);
	    }
	    
	    @Test(expected = IllegalStateException.class)
	    public void testWorkException(){
	    	Unit defender = new Unit("Baba 'O Reil", position, 50, 50, 50, 50, true);
	    	unit1.attack(defender);
	    	unit1.workAt(Utils.getCubePosition(unit1.getPosition()));
	    }
	    
	    @Test(expected = IllegalStateException.class)
	    public void testRestException(){
	    	Unit defender = new Unit("Baba 'O Reil", position, 50, 50, 50, 50, true);
	    	unit1.attack(defender);
	    	unit1.rest();
	    }
	    
	    @Test(expected = IllegalStateException.class)
	    public void testAttackException(){
	    	Unit defender = new Unit("Baba 'O Reil", new int[]{1,2,5}, 50, 50, 50, 50, true);
	    	unit1.attack(defender);
	    }
	    
	    @Test(expected = IllegalStateException.class)
	    public void testStartSprinting(){
	    	unit1.startSprinting();
	    }
	    
	    /**
	     * Let the unit pick up an item and afterwards, let it drop
	     */
		@Test
		public void testWorkingPickingUpDroppingDown(){
			int[][][] types = new int[2][2][2];
			types[1][1][0] = World.TYPE_ROCK;
			types[0][1][0] = World.TYPE_TREE;
			types[1][0][0] = World.TYPE_WORKSHOP;

			world = new World(types, new DefaultTerrainChangeListener());
			Unit unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
			world.addUnit(unit);
			
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
		
		/**
		 * Let the unit work on a workshop with an item an let him upgrade his armour
		 */
		@Test
		public void testWorkingUpgradeWorkshop(){
			int[][][] types = new int[2][2][2];
			types[1][1][0] = World.TYPE_ROCK;
			types[0][1][0] = World.TYPE_TREE;
			types[1][0][0] = World.TYPE_WORKSHOP;

			world = new World(types, new DefaultTerrainChangeListener());
			Unit unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
			world.addUnit(unit);
			
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
		
		/**
		 * Let the unit search for a path and let him go this place
		 */
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
			System.out.println(unit.getCubePosition().toString());
			boolean reachedEnd = (
					Util.fuzzyEquals(unit.getPosition()[0],3.5) && 
					Util.fuzzyEquals(unit.getPosition()[1],3.5) && 
					Util.fuzzyEquals(unit.getPosition()[2],3.5));
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
		private static void advanceTimeFor(World world, double time, double step) throws IllegalArgumentException{
			int n = (int) (time / step);
			for (int i = 0; i < n; i++)
				world.advanceTime(step);
			world.advanceTime(time - n * step);
		}
		
		


}
