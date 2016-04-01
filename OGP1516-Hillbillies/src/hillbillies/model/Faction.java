package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Invar Each faction must have proper units.
 * 		| this.hasProperUnits()
 */
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
	 * @param world
	 * 		the world the faction belongs to
	 * 
	 * @post the faction belongs to the world world
	 * @post the faction is not yet terminated
	 * 
	 */
	public Faction(World world){
		//ADD WORLD
		this.world = world;
		this.isTerminated = false;
	}
	
	/**
	 * Terminates the faction
	 * 
	 * @post this faction is now terminated
	 * 
	 * @throws IllegalStateException
	 * 		if there are still units part of this faction
	 */
	public void terminate() throws IllegalStateException {
		if(!this.getUnits().isEmpty())
			throw new IllegalStateException();
		this.isTerminated = true;
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
	
	//-----------------------GETTERS
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
	 * returns the world of the faction
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	//-----------------------SETTERS
	/**
	 * Add the given unit to the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be added.
	 * @pre    The given unit is alive and
	 * 				 is not yet connected to a faction
	 * @post   This faction has the given unit as one of its units. 
	 * @effect The unit will have this faction as faction
	 */
	public void addUnit(@Raw Unit unit) {
		assert (unit.isAlive() && unit.getFaction() == null);
		this.units.add(unit);	
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
		this.units.remove(unit);
	}
	//-----------------------INSPECTORS
	
	/**
	 * Check whether this faction has the given unit as one of its units.
	 * 
	 * @param  Unit, The unit to check.
	 */
	@Basic @Raw
	public boolean hasAsUnit(Unit unit) {
		return this.getUnits().contains(unit);
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
	 * Check whether this faction has proper units attached to it.
	 * 
	 * @return True if and only if this faction can have each of the
	 *         units attached to it as one of its units,
	 *         and if each of these units references this faction as
	 *         the faction to which they are attached.
	 */
	public boolean hasProperUnits() {
		for (Unit unit : this.getUnits()) {
			if (!canHaveAsUnit(unit))
				return false;
			if (unit.getFaction() != this)
				return false;
		}
		return true;
	}
	
	
	
	
	
	
	

	
	
	
	
	


}
