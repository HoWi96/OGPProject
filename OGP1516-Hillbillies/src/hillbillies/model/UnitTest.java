package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {

	String naam1 = "Baba 'O Reily";
	String naam2 = "B";
	String naam3 = "3azerty";
	String naam4 = "lol";
	
	
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
	public final void testCorrectName1() {
		assertTrue(Unit.isValidName(naam1));
	}
	
	@Test
	public final void testCorrectName2(){
		assertFalse(Unit.isValidName(naam2));
	}
	@Test
	public final void testCorrectName3(){
		assertFalse(Unit.isValidName(naam3));
	}
	@Test
	public final void testCorrectName4(){
		assertFalse(Unit.isValidName(naam4));
	}

	

}
