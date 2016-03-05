
package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {

	
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
}
