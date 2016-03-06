
package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ogp.framework.util.Util;

public class UnitTest {
	static Unit unit;

	static int[] position = {1, 2, 3};
	static int[] targetPosition = {2, 3, 4};
	static double[] targetPositionDouble = {3, 4, 5};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		unit = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, true);
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testisValidName() {
		assertTrue(Unit.isValidName("Baba 'O Reily"));
		assertTrue(Unit.isValidName("A '\"\'"));
		
		assertFalse(Unit.isValidName("B.' 5"));
		assertFalse(Unit.isValidName("B"));
		assertFalse(Unit.isValidName("3azerty"));
		assertFalse(Unit.isValidName("lol"));
	}
	
	 @Test
	    public void testIsValidPosition(){
		 	assertTrue(Unit.isValidPosition(new double[]{0,0,0}));
	        assertTrue(Unit.isValidPosition(new double[]{50,50,50}));
	        
	        assertFalse(Unit.isValidPosition(new double[]{-1,-10,-5}));
	        assertFalse(Unit.isValidPosition(new double[]{51,51,51}));   
	    }
	 
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
		 
		 	assertTrue(Unit.isValidWeight(200,200,200));
		 	assertTrue(Unit.isValidWeight(1,1,1));
		 	
	        assertFalse(Unit.isValidWeight(0,0,0));
	        assertFalse(Unit.isValidWeight(201,1,1));
	        assertFalse(Unit.isValidWeight(10,15,15));   
	    }
	  @Test
	    public void testIsValidStamina(){
		  
		  	assertTrue(Unit.isValidStamina(800,200,200));
		  	assertTrue(Unit.isValidStamina(0,200,200));
		  	assertTrue(Unit.isValidStamina(62,34,90));
		  	assertTrue(Unit.isValidStamina(13,25,25));
		  	assertTrue(Unit.isValidStamina(12,25,25));
		  	
	        assertFalse(Unit.isValidStamina(-1,200,200));
	        assertFalse(Unit.isValidStamina(801,200,200));
	        assertFalse(Unit.isValidStamina(14,25,25)); 
	    }
	  

	    @Test
	    public void testIsValidHitpoints(){
	    	assertTrue(Unit.isValidHitpoints(800,200,200));
		  	assertTrue(Unit.isValidHitpoints(0,200,200));
		  	assertTrue(Unit.isValidHitpoints(62,34,90));
		  	assertTrue(Unit.isValidHitpoints(13,25,25));
		  	assertTrue(Unit.isValidHitpoints(12,25,25));
		  	
	        assertFalse(Unit.isValidHitpoints(-1,200,200));
	        assertFalse(Unit.isValidHitpoints(801,200,200));
	        assertFalse(Unit.isValidHitpoints(14,25,25)); 

	    }
	    
	    @Test
	    public void testIsValidOrientation(){
	    	assertTrue(Unit.isValidOrientation(0));
	    	assertTrue(Unit.isValidOrientation(6));
	    	assertTrue(Unit.isValidOrientation(4));
	    	assertTrue(Unit.isValidOrientation(2));

	    	assertFalse(Unit.isValidOrientation(7));
	    	assertFalse(Unit.isValidOrientation(98));
	    	assertFalse(Unit.isValidOrientation(39));
	    	assertFalse(Unit.isValidOrientation(2*(float)Math.PI));
	    }
	    
	    @Test
	    public void testGetCubePosition(){
	    	double[] position = {1.4, 2.3, 3.2};
	    	int[] positionInt = {(int)position[0], (int)position[1], (int)position[2]};
	    	int[] position2 = Unit.getCubePosition(position);
	    	assertTrue(position2[0] == positionInt[0]);
	    	assertTrue(position2[1] == positionInt[1]);
	    	assertTrue(position2[2] == positionInt[2]);
	    }
	    
	    @Test
	    public void testGetMaxHitpoints(){
	    	assertTrue(Unit.getMaxHitpoints(50,  80) == 80);
	    	assertTrue(Unit.getMaxHitpoints(54,  34) == 37);
	    	assertTrue(Unit.getMaxHitpoints(0,  100) == 0);
	    	
	    	assertFalse(Unit.getMaxHitpoints(50,  80) == 40);
	    	assertFalse(Unit.getMaxHitpoints(54,  34) == 38);
	    }
	    
	    @Test
	    public void testGetMaxStamina(){
	    	assertTrue(Unit.getMaxStamina(50,  80) == 80);
	    	assertTrue(Unit.getMaxStamina(54,  34) == 37);
	    	assertTrue(Unit.getMaxStamina(0,  100) == 0);
	    	
	    	assertFalse(Unit.getMaxStamina(50,  80) == 40);
	    	assertFalse(Unit.getMaxStamina(54,  34) == 38);
	    }
	    
	    @Test
	    public void testSetSpeed(){
	    	Unit unit = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, true);
	    	unit.setSpeed(30);
	    	assertTrue(unit.getSpeed() == 30);
	    }
	    
	    @Test
	    public void testUpdateSpeed(){
	    	Unit unit = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, true);
	    	unit.updateSpeed(0);
	    	assertTrue(unit.getSpeed() == 0);
	    	//unit.setCurrentActivity(Activity.MOVING);
	    	unit.moveToTarget(targetPosition);
	    	unit.updateSpeed(-1);
	    	assertTrue(Util.fuzzyEquals(unit.getSpeed(), 1.8));
	    	unit.updateSpeed(0);
	    	assertTrue(unit.getSpeed() == 1.5);
	    	unit.updateSpeed(1);
	    	assertTrue(unit.getSpeed() == 0.75);
	    }
	    
	    @Test
	    public void testGetTargetPosition(){
	    	Unit unit = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, true);
	    	unit.moveToTarget(targetPosition);
	    	double[] target = unit.getTargetPosition();
	    	assertTrue(target[0] == targetPosition[0]+0.5);
	    	assertTrue(target[1] == targetPosition[1]+0.5);
	    	assertTrue(target[2] == targetPosition[2]+0.5);
	    }
	    
	    @Test
	    public void testGetNextPosition(){
	    	Unit unit = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, true);
	    	unit.moveToTarget(targetPosition);
	    	double[] next = unit.getNextPosition();
	    	assertTrue(next[0] == position[0]+0.5);
	    	assertTrue(next[1] == position[1]+0.5);
	    	assertTrue(next[2] == position[2]+0.5);
	    }
	    
	    @Test
	    public void testGetStep(){
	    	Unit unit = new Unit("Baba 'O Reily", position, 50, 50, 50, 50, true);
	    	int[] step = {1, 0, -1};
	    	unit.setStep(step);
	    	int[] step2 = unit.getStep();
	    	assertTrue(step2[0] == 1);
	    	assertTrue(step2[1] == 0);
	    	assertTrue(step2[2] == -1);
	    }
	    
	    @Test
	    public void testSetTargetPosition(){
	    	unit.setTargetPosition(targetPositionDouble);
	    	double[] target = unit.getTargetPosition();
	    	assertTrue(target[0] == 3);
	    	assertTrue(target[1] == 4);
	    	assertTrue(target[2] == 5);
	    }
	    
	    @Test
	    public void testSetNextPosition(){
	    	unit.setNextPosition(targetPositionDouble);
	    	double[] next = unit.getNextPosition();
	    	assertTrue(next[0] == 3);
	    	assertTrue(next[1] == 4);
	    	assertTrue(next[2] == 5);
	    }
	    
	    @Test
	    public void testMoveToTarget(){
	    	unit.moveToTarget(targetPosition);
	    	double[] next = unit.getNextPosition();
	    	double[] target = unit.getTargetPosition();
	    	assertTrue(next[0] == 1.5);
	    	assertTrue(next[1] == 2.5);
	    	assertTrue(next[2] == 3.5);
	    	assertTrue(target[0] == 2.5);
	    	assertTrue(target[1] == 3.5);
	    	assertTrue(target[2] == 4.5);
	    	
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testIllegalArgumentExceptionMoveToTarget(){
	    	int[] position = {-3, 5, 70};
	    	unit.moveToTarget(position);
	    }
	    
	    @Test
	    public void testMoveToAdjacent(){
	    	unit.moveToAdjacent(-1, 0, 1);
	    	int[] step = unit.getStep();
	    	assertTrue(step[0] == -1);
	    	assertTrue(step[1] == 0);
	    	assertTrue(step[2] == 1);
	    	assertTrue(unit.getSpeed() == 0.75);
	    	assertTrue(Util.fuzzyEquals(unit.getOrientation(), Math.PI));
	    	double[] next = unit.getNextPosition();
	    	assertTrue(next[0] == 0.5);
	    	assertTrue(next[1] == 2.5);
	    	assertTrue(next[2] == 4.5);
	    }
	    
	    @Test
	    public void testGetIntermediatePosition(){
	    	unit.setSpeed(1);
	    	double[] newPosition = unit.getIntermediatePosition(-1, 0, 0, 0.5);
	    	assertTrue(Util.fuzzyEquals(newPosition[0],1));
	    	double[] newPosition2 = unit.getIntermediatePosition(0, 1, 0, 0.5);
	    	assertTrue(Util.fuzzyEquals(newPosition2[1], 3));
	    	double[] newPosition3 = unit.getIntermediatePosition(0, 0, 1, 0.5);
	    	assertTrue(Util.fuzzyEquals(newPosition3[2], 4));
	    }
	    
	    @Test
	    public void testIsMoving(){
	    	assertFalse(unit.isMoving());
	    	unit.moveToAdjacent(1, 0, -1);
	    	assertTrue(unit.isMoving());
	    }
	    
	    @Test
	    public void testIsWorking(){
	    	assertFalse(unit.isWorking());
	    	unit.work();
	    }
}
