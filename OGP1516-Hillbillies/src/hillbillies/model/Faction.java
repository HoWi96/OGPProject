package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

public class Faction {
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	private static final int MAX_UNITS_IN_FACTION = 50;
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------VARIABLES---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	/**
	 * Variable referencing a set collecting all the units
	 * of this faction.
	 * 
	 * @invar  The referenced set is effective.
	 *       | Units != null
	 * @invar  Each unit registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each Unit in Units:
	 *       |   ( (Unit != null) &&
	 *       |     (! Unit.isTerminated()) )
	 */
	private Set<Unit> units = new HashSet<Unit>(MAX_UNITS_IN_FACTION);
	/**
	 * variable registering the world of the faction
	 */
	private final World world;
	/**
	 * variable registering whether the faction is terminated
	 */
	private boolean isTerminated;
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTRUCTOR--------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Creates a new faction
	 * 
	 * @param unit
	 * 			the first unit of the faction
	 * @param world
	 * 		the world the faction belongs to
	 * 
	 * @post the unit is now part of this faction
	 * @post the faction belongs to the world world
	 * @post the faction is not yet terminated
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public Faction(World world) throws IllegalArgumentException, IllegalStateException{
		//ADD WORLD
		this.world = world;
		this.isTerminated = false;
	}
	/**
	 * Terminates the faction
	 * 
	 * @post this faction is now terminated
	 * @throws IllegalStateException
	 * 		if there are still units part of this faction
	 */
	public void terminate() throws IllegalStateException {
		if(!this.getUnits().isEmpty())
			throw new IllegalStateException();
		this.isTerminated = true;
		this.units = null;
	}
	/**
	 * Check whether this faction is terminated
	 */
	@Basic @Raw
	public boolean isTerminated(){
		return isTerminated;
	}
	

	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------METHODS------------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	/**
	 * Check whether this faction has the given unit as one of its units.
	 * 
	 * @param  Unit, The unit to check.
	 */
	@Basic @Raw
	public boolean hasAsUnit(@Raw Unit unit) {
		return units.contains(unit);
	}
	
	/**
	 * Add the given unit to the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be added.
	 * @pre    The given unit is effective and already references
	 *         this faction.
	 * 
	 * @post   This faction has the given unit as one of its units. 
	 */
	public void addUnit(@Raw Unit unit) {
		assert (unit != null) && (unit.getFaction() == this);
		units.add(unit);
	}

	/**
	 * Remove the given unit from the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be removed.
	 * @pre    This faction has the given unit as one of
	 *         its units, and the given unit does not
	 *         reference any faction.
	 *      
	 * @post   This faction no longer has the given unit as
	 *         one of its units.
	 */
	@Raw
	public void removeUnit(Unit unit) {
		assert this.hasAsUnit(unit) && (unit.getFaction() == null);
		units.remove(unit);
	}
	
	/**
	 * Gives back the units of this faction
	 */
	@Basic
	public Set<Unit> getUnits() {
		return this.units;
	}
	
	/**
	 * @return the amount of units in the faction
	 */
	public int getNbUnits(){
		return this.units.size();
	}
	
	/**
	 * Check whether this faction can have the given unit
	 * as one of its units.
	 * 
	 * @param  Unit
	 *         The unit to check.
	 * @return True if and only if the given unit is effective
	 *         and if the faction can still get more units
	 */
	public boolean canHaveAsUnit(Unit unit) {
		return (unit != null) && (this.getNbUnits() < MAX_UNITS_IN_FACTION);
	}
	
	/**
	 * returns the world of the faction
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	
	

	
	
	
	
	


}
