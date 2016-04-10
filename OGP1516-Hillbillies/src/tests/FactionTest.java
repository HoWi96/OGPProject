package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class FactionTest {
	
	Faction faction1;
	Unit unit1;
	World world1;

	@Before
	public void setUp() throws Exception {
		int[][][] types = new int[3][4][5];
		world1 = new World(types, new DefaultTerrainChangeListener());
		faction1 = new Faction();
		unit1 = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testInitialFactionOfUnit(){
		Faction faction = unit1.getFaction();
		
		assertTrue(faction.hasAsUnit(unit1));
		assertTrue(unit1.getFaction()==faction);	
	}
	
	@Test
	public void testFactionsOfWorld(){
		assertTrue(world1.getAllFactions().isEmpty());
		assertEquals(world1.getNbFactions(), 0);

		for (int i = 0; i<10; i++){
			Unit unit = world1.createRandomUnit(false);
			world1.addUnit(unit);
		}
		assertTrue(world1.getNbFactions() == 5);
		
		//And Deleting all units
		Set<Unit> units = world1.getAllUnits();
		assertFalse(units.isEmpty());
		
		for(Unit unit: units){
			System.out.println(""+unit);
			unit.terminate();
		}
		assertTrue(world1.getAllFactions().isEmpty());
	}
	
	

	@Test
	public void testUnitInFaction(){
		
		unit1.getFaction().removeUnit(unit1);

		faction1.addUnit(unit1);
		
		assertEquals(unit1.getFaction(), faction1);
		assertEquals(faction1.getNbUnits(),1);
		assertTrue(faction1.getAllUnits().contains(unit1));
		assertTrue(faction1.hasAsUnit(unit1));
		
		faction1.removeUnit(unit1);
		
		assertTrue(unit1.getFaction() == null);
		assertEquals(faction1.getNbUnits(),0);
		assertFalse(faction1.getAllUnits().contains(unit1));
	}
	
	@Test
	public void testTerminateFaction(){
		Faction faction2 = new Faction();
		assertFalse(faction2.isTerminated());
		
		faction2.terminate();
		assertTrue(faction2.isTerminated());
		assertTrue(faction2.getAllUnits() == null);
	}
	

	
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalUnitAdded(){
		assertFalse(faction1.canHaveAsUnit(null));
		faction1.addUnit(null);
	}
	
	
	@Test
	public void testremoveFaction(){
		world1.addUnit(unit1);
		assertTrue(world1.hasAsFaction(unit1.getFaction()));

		world1.removeFaction(unit1.getFaction());
		assertFalse(world1.hasAsFaction(unit1.getFaction()));
	}
	
	
	



}
