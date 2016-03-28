package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;

/**
 * @invar  The TerrainTypes of each world must be a valid TerrainTypes for any
 *         world.
 *       | isValidTerrainTypes(getTerrainTypes())
 * @invar  Each world can have its nbCubesX as dimension.
 *       | canHaveAsDimension(this.getNbCubesX())     
 * @invar  Each world can have its nbCubesY as dimension.
 *       | canHaveAsDimension(this.getNbCubesY())
 * @invar  Each world can have its nbCubesX as dimension.
 *       | canHaveAsDimension(this.getNbCubesZ())
 * @invar  The cubeType of each world must be a valid cubeType for any
 *         world.
 *       | isValidcubeType(getcubeType())      
 *       
 *       
 */

public class World {
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[] VALID_CUBE_TYPES = {TYPE_AIR,TYPE_ROCK,TYPE_TREE,TYPE_WORKSHOP};
	
	private static final int MAX_UNITS = 100;
	private static final int MAX_FACTIONS = 5;
	
	
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------VARIABLES---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	/**
	 * Variable registering the TerrainTypes of this world.
	 */
	private int[][][] terrainTypes;
	/**
	 * Variable registering the nbCubesX of this world.
	 */
	private final int nbCubesX;
	/**
	 * Variable registering the nbCubesY of this world.
	 */
	private final int nbCubesY;
	/**
	 * Variable registering the nbCubesZ of this world.
	 */
	private final int nbCubesZ;
	/**
	 * Variable registering the TerrainChangeListener of this world.
	 */
	private final TerrainChangeListener modelListener;
	/**
	 * Variable referencing a set collecting all the units
	 * of this world.
	 * 
	 * @invar  The referenced set is effective.
	 *       | units != null
	 * @invar  Each unit registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each unit in units:
	 *       |   ( (unit != null) &&
	 *       |     (! unit.isTerminated()) )
	 */
	public Set<Unit> units = new HashSet<Unit>(MAX_UNITS);
	/**
	 * Variable referencing a set collecting all the factions
	 * of this world.
	 * 
	 * @invar  The referenced set is effective.
	 *       | factions != null
	 * @invar  Each faction registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each faction in factions:
	 *       |   ( (faction != null) &&
	 *       |     (! faction.isTerminated()) )
	 */
	public Set<Faction> factions = new HashSet<Faction>(MAX_FACTIONS);
	
		
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTRUCTOR--------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
/**
 * @param  terraintypes
 *         The TerrainTypes for this new world.        
 * @param  modelListener
 *         The TerrainChangeListener for this new world.  
 * ______________________________________________________  
 *         
 * @effect The TerrainTypes of this new world is set to
 *         the given TerrainTypes.
 *       | this.setTerrainTypes(terrainTypes)
 *       
 * @post   The nbCubesX of this new world is equal to terrainTypes.length
 *       
 * @post   The nbCubesY of this new world is equal to terrainTypes[0].length
 *       
 * @post   The nbCubesX of this new world is equal to terrainTypes[0][0].length
 * 
 * @post   The TerrainChangeListener of this new world is equal to the given
 *         TerrainChangeListener.
 *       | new.getTerrainChangeListener() == modelListener        
 */
public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {
	
	this.setTerrainTypes(terrainTypes);
	
	//Immutable Variables
	this.nbCubesX = terrainTypes.length;
	this.nbCubesY = terrainTypes[0].length;
	this.nbCubesZ = terrainTypes[0][0].length;
	this.modelListener = modelListener;
}
	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------METHODS--------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//------------------------GETTERS
	
	/**
	 * Return the nbCubesX of this world.
	 */
	@Basic @Raw @Immutable
	public int getNbCubesX() {
		return this.nbCubesX;
	}
	
	/**
	 * Return the nbCubesY of this world.
	 */
	@Basic @Raw @Immutable
	public int getNbCubesY() {
		return this.nbCubesY;
	}
	
	/**
	 * Return the nbCubesZ of this world.
	 */
	@Basic @Raw @Immutable
	public int getNbCubesZ() {
		return this.nbCubesZ;
	}
	
	/**
	 * Return the TerrainTypes of this world.
	 */
	@Basic @Raw
	public int[][][] getTerrainTypes() {
		return this.terrainTypes.clone();
	}
	
	/**
	 * Get the cube type on a given position
	 * 
	 * @param position
	 * 			the position where you want to know the cubeType of
	 * @return the cubeType
	 * @throws IllegalArgumentException
	 * 			if the position is out of bounds
	 */
	@Raw
	public int getcubeType(int[] position) throws IllegalArgumentException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		
		return this.getTerrainTypes()[X][Y][Z];
		
	}
	
	/**
	 * Return the TerrainChangeListener of this world.
	 */
	@Basic @Raw @Immutable
	public TerrainChangeListener getTerrainChangeListener() {
		return this.modelListener;
	}
	
	//------------------------SETTERS
	
	/**
	 * Set the TerrainTypes of this world to the given TerrainTypes.
	 * 
	 * @param  terraintypes
	 *         The new TerrainTypes for this world.
	 * @post   The TerrainTypes of this new world is equal to
	 *         the given TerrainTypes.
	 *       | new.getTerrainTypes() == terrainTypes
	 * @throws IllegalArgmunetException
	 *         The given TerrainTypes is not a valid TerrainTypes for any
	 *         world.
	 *       | ! isValidTerrainTypes(getTerrainTypes())
	 */
	@Raw
	public void setTerrainTypes(int[][][] terraintypes) throws IllegalArgumentException {
		if (! isValidTerrainTypes(terraintypes))
			throw new IllegalArgumentException();
		this.terrainTypes = terraintypes;
	}
	
	/**
	 * Set the cubeType of this world to the given cubeType.
	 * 
	 * @param  cubeType
	 *         The new cubeType for this world.
	 * @post   The cubeType of this new world is equal to
	 *         the given cubeType.
	 *       | new.getcubeType(position) == cubeType
	 * @throws IllegalArgumentException
	 *         The given cubeType is not a valid cubeType for any
	 *         world. Or give position is out of bounds
	 */
	@Raw
	public void setcubeType(int cubeType, int[] position) throws IllegalArgumentException {
		
		if (! isValidCubeType(cubeType) || !isValidPosition(position))
			throw new IllegalArgumentException();
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		//more efficient than changing the whole world
		this.terrainTypes[X][Y][Z] = cubeType;
		this.getTerrainChangeListener().notifyTerrainChanged(X,Y,Z);
		
	}
	
	//------------------------INSPECTORS
	
	/**
	 * Check whether the given TerrainTypes is a valid TerrainTypes for
	 * any world.
	 *  
	 * @param  TerrainTypes
	 *         The TerrainTypes to check.
	 * @return
	 * 		TerrainTypes may not be null.
	 *      The dimensions must be nonzero and larger than 0.
	 *      All elements part of TerrainType must be valid cube types.
	*/
	public static boolean isValidTerrainTypes(int[][][] terrainTypes) {
		
		//terraintypes may not be null
		if(terrainTypes == null)
			return false;
		//dimensions must be nonzero
		if(!(terrainTypes.length>0 && 
				terrainTypes[0].length>0 && terrainTypes[0][0].length>0))
			return false;
		
		// all elements need to be valid cube types;
		for(int[][] outerArray: terrainTypes){
			for( int[] innerArray: outerArray){
				for(int cubeType: innerArray){
					if(!isValidCubeType(cubeType))
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check whether the given cubeType is a valid cubeType for
	 * any world.
	 *  
	 * @param  cubeType
	 *         The cubeType to check.
	 * @return 
	 *       whether the type of the cube is a valid cubeType
	*/
	private static boolean isValidCubeType(int cubeType) {
		for(int validCubeType: VALID_CUBE_TYPES){
			if(cubeType == validCubeType)
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param position
	 * 			the position to check
	 * @return
	 * 		whether the position is inside the game world
	 */
	@Raw
	public boolean isValidPosition(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return 0<=X&&X<=this.getNbCubesX()&&
				0<=Y&&Y<=this.getNbCubesY()&&
				0<=Z&&Z<=this.getNbCubesZ();	
	}
	/**
	 * Returns whether the cube is solid or not
	 * 
	 * @param position
	 * 			the position of the cube
	 * @return
	 * 		if the cube at the given position is rock or tree
	 */
	public boolean isSolidCube(int[] position){
		int type = this.getcubeType(position);
		return (type == TYPE_ROCK) || (type == TYPE_TREE);	
	}
	
	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------FACTIONS-----------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//------------------------GETTERS
	
	/**
	 * Gives back the active factions of this world
	 */
	@Basic @Raw
	public Set<Faction> getFactions(){
		return this.factions;
	}
	
	//------------------------SETTERS
	

	/**
	 * Add the given faction to the set of factions of this world.
	 * 
	 * @param  faction
	 *         The faction to be added.
	 * @pre    The given faction is effective and already references
	 *         this world.
	 *       | (faction != null) && (faction.getWorld() == this)
	 * @post   This world has the given faction as one of its factions.
	 *       | new.hasAsFaction(faction)
	 */
	public void addFaction(@Raw Faction faction) {
		assert (faction != null) && (faction.getWorld() == this);
		factions.add(faction);
	}
	
	//------------------------DESTRUCTORS

	/**
	 * Remove the given faction from the set of factions of this world.
	 * 
	 * @param  faction
	 *         The faction to be removed.
	 * @pre    This world has the given faction as one of
	 *         its factions, and the given faction does not
	 *         reference any world.
	 *       | this.hasAsFaction(faction) &&
	 *       | (faction.getWorld() == null)
	 * @post   This world no longer has the given faction as
	 *         one of its factions.
	 *       | ! new.hasAsFaction(faction)
	 */
	@Raw
	public void removeFaction(Faction faction) throws IllegalArgumentException, IllegalStateException {
		if(faction.get)
		assert this.hasAsFaction(faction) && (faction.getWorld() == null);
		factions.remove(faction);
	}
	


	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------UNITS-----------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//------------------------GETTERS
	
	/**
	 * Gives back the units of the world
	 */
	@Basic @Raw
	public Set<Unit> getUnits() {
		return this.units;
	}
	//------------------------SETTERS
	
	public void addUnit(Unit unit) throws IllegalArgumentException, IllegalStateException{
		if(this.getUnits().size() == MAX_UNITS)
			throw new IllegalStateException();
		this.units.add(unit);
		// this function should return illegalArgument when the unit is on a solid position or out of bounds
		unit.setWorld(this);
	}
	//------------------------DESTRUCTORS
	

}
