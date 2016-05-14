package hillbillies.model;
import java.util.*;

import be.kuleuven.cs.som.annotate.*;


/**
 * @author Holger Willems |2e bach. ing.: OOP
 * @date 10/04/2016
 * @Version 2.0
 * 
 * ASSOCIATIONS
 * @Invar Each faction must have proper units.
 * 		| this.hasProperUnits()
 * @Invar  Each Faction can have its Scheduler as Scheduler.
 *      | canHaveAsScheduler(this.getScheduler())
 */
public class Faction {
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	public static final int MAX_UNITS_IN_FACTION = 50;
	
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
	private Set<Unit> units;
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
	 * @post   The faction is not yet terminated
	 * @post   The faction does not contain any units yet
	 * @post   The Scheduler of this new Faction is equal to a new
	 *         Scheduler.
	 */
	public Faction(){
		this.isTerminated = false;
		this.units = new HashSet<Unit>(MAX_UNITS_IN_FACTION);
		this.scheduler = new Scheduler();
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------DESTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Terminates the faction
	 * 
	 * @post this faction is now terminated
	 * @post this faction references no units anymore
	 * 
	 * @throws IllegalStateException
	 * 		if there are still units part of this faction
	 */
	public void terminate() throws IllegalStateException {
		if(this.getNbUnits()!=0)
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
	 * -----------------------UNITS--------------------------------------
	 * ------------------CONTROLLING CLASS-------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//-----------------------INSPECTORS
	/**
	 * Gives back the units of this faction
	 */
	@Basic
	public Set<Unit> getAllUnits() {
		return this.units;
	}
	
	/**
	 * @return the amount of units in the faction
	 */
	public int getNbUnits(){
		return this.units.size();
	}

	//-----------------------SETTERS
	/**
	 * Add the given unit to the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be added.
	 *         
	 * @pre    The given unit is alive and
	 * 			 the given unit does not reference any faction.
	 * 
	 * @post   This faction has the given unit as one of its units. 
	 * 
	 * @effect The unit will have this faction as faction
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException {
		if (!(this.canHaveAsUnit(unit) &&  unit.getFaction() == null && this.getNbUnits() < MAX_UNITS_IN_FACTION))
			throw new IllegalArgumentException();
		
		this.units.add(unit);
		unit.setFaction(this);
	}

	/**
	 * Remove the given unit from the set of units of this faction.
	 * 
	 * @param  unit
	 *         The unit to be removed.
	 *         
	 * @pre    This faction has the given unit as one of
	 *         its units, and the given unit does 
	 *         reference this faction.
	 *      
	 * @post   This faction no longer has the given unit as
	 *         one of its units.
	 *         
	 * @effect The unit will no longer have this faction as faction  
	 */
	@Raw
	public void removeUnit(Unit unit) throws IllegalArgumentException{
		if(!this.hasAsUnit(unit) || unit.getFaction() != this)
			throw new IllegalArgumentException();
		
		this.units.remove(unit);
		unit.setFaction(null);

	}
	//-----------------------EXTRA INSPECTORS
	
	/**
	 * Check whether this faction has the given unit as one of its units.
	 * 
	 * @param  Unit, The unit to check.
	 */
	@Basic @Raw
	public boolean hasAsUnit(Unit unit) {
		return this.units.contains(unit);
	}
	
	/**
	 * Check whether this faction can have the given unit
	 * as one of its units.
	 * 
	 * @param  Unit
	 *         The unit to check.
	 * @return True if and only if the given unit is effective
	 *         and if the unit is alive
	 */
	public boolean canHaveAsUnit(Unit unit) {
		return unit != null && unit.isAlive();
	}

	/**
	 * Check whether this faction has proper units attached to it.
	 * 
	 * @return True if and only if this faction can have each of the
	 *         units attached to it as one of its units,
	 *         and if each of these units references this faction as
	 *         the faction to which they are attached.
	 *         And if the amount of units does not exceed the max amount
	 */
	public boolean hasProperUnits() {
		
		if (this.getNbUnits()<=MAX_UNITS_IN_FACTION)
			return false;
		
		for (Unit unit : this.units) {
			if (!canHaveAsUnit(unit) || unit.getFaction() != this )
				return false;
		}
		return true;
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------SCHEDULER--------------------------------------
	 * ------------------CONTROLLING CLASS-------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Return the Scheduler of this Faction.
	 */
	@Basic @Raw @Immutable
	public Scheduler getScheduler() {
		return this.scheduler;
	}
	
	/**
	 * Check whether this Faction can have the given Scheduler as its Scheduler.
	 *  
	 * @param  scheduler
	 *         The Scheduler to check.
	 * @return 
	 *       | result == scheduler != null
	*/
	@Raw
	public boolean canHaveAsScheduler(Scheduler scheduler) {
		return scheduler != null;
	}
	
	/**
	 * Variable registering the Scheduler of this Faction.
	 */
	private final Scheduler scheduler;

}
