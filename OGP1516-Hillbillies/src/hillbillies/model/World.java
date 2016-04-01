package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

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
	/**
	 * Variable registering the ConnectedToBorder class storing information about this world
	 */
	private final ConnectedToBorder border;
	
		
	
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
 *
 * @post   The connectedToBorder of this new world is equal to the given
 *         connectedToBorder.
 *       | new.getConnectedToBorder() == border
 */
public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {
	
	this.setTerrainTypes(terrainTypes);
	
	//Immutable Variables
	this.nbCubesX = terrainTypes.length;
	this.nbCubesY = terrainTypes[0].length;
	this.nbCubesZ = terrainTypes[0][0].length;
	//Connection to ConnectedToBorder
	this.border = new ConnectedToBorder(this.getNbCubesX(),this.getNbCubesY(), this.getNbCubesZ());
	this.makeAllSolidsConnected();
	//Connection with the GUI
	if(modelListener==null)
		throw new IllegalArgumentException();
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
	
	/**
	 * Return the connectedToBorder of this world.
	 */
	@Basic @Raw @Immutable
	public ConnectedToBorder getConnectedToBorder() {
		return this.border;
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
	
	/**
	 * Checks whether there are solid cubes surrounding the given cube
	 * 
	 * @param position
	 * 		The position
	 * @return
	 * 		whether there are solid cubes surrounding the given cube
	 * 		
	 */
	public boolean hasSolidAdjacents(int[] position){
		List<int[]> adjacentCubes = Utils.getAdjacentCubes(position);
		
		for(int[] adjacentCube: adjacentCubes){
			if(isSolidCube(adjacentCube))
				return true;
		}
		return false;
	}
	
	
	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------TERRAIN CHANGES----------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Cave in the world on the specific position and control if there
	 *  are, due to this change, cubes not connected anymore to the border
	 * 
	 * @param position
	 * 			The position where a cave in will happen
	 * 
	 * @post The type of the position will be changed to TYPE_AIR
	 * @effect the ConnectedToBorder will change his content due to the change
	 * @effect The TerrainChangeListener will be notified
	 * 
	 * @effect A boulder will be spawned
	 * @effect A log will be spawned
	 * 
	 */
	private void caveIn(int[] position){
		
		List<int[]> caveInList = this.getConnectedToBorder().changeSolidToPassable(
				position[0], position[1], position[2]);
		
		for(int[] caveInPosition: caveInList){
			
			if(isSolidCube(caveInPosition)){
				//REPLACE CUBE
				int type = this.getcubeType(caveInPosition);
				this.setcubeType(TYPE_AIR, caveInPosition);	
				this.getTerrainChangeListener().notifyTerrainChanged(
						caveInPosition[0], caveInPosition[1], caveInPosition[2]);
				//SPAWN RAWMATERIAL
				double probability = 0.25;
				Random rand = new Random();
				if (rand.nextDouble() <= probability){
					if (type == TYPE_ROCK){
						// spawn boulder
						System.out.println("spawn boulder");
					}else if(type == TYPE_TREE){
						// spawn log
						System.out.println("spawn log");
					}
				}
			}
		}
	}
	
	/**
	 * Makes all the solid cubes connected
	 * 
	 * @effect the solid cubes in the world which are not connected will cave in
	 * 
	 */
	private void makeAllSolidsConnected(){
		
		for (int x=0; x<getNbCubesX(); x++) {
			for (int y=0; y<getNbCubesY(); y++) {
				for (int z=0; z<getNbCubesZ(); z++) {
					// non solid cubes have to be notified if they are not already updated
					if(!isSolidCube(new int[] {x,y,z}) && this.getConnectedToBorder().isSolidConnectedToBorder(x, y, z)){
						this.caveIn(new int[] {x,y,z});
					}	
				}
			}
		}
	}
	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------ADVANCE TIME-------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	public void advanceTime(double dt) throws IllegalArgumentException{
		if (!(0.0<=dt&&dt<=0.2))
			throw new IllegalArgumentException();
		//UNITS
		for(Unit unit: this.getUnits()){
			unit.advanceTime(dt);	
		}
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
	

	/**
	 * Returns the faction with the least amount of units
	 * 
	 * @return the smallest faction
	 * 
	 */
	@Raw @Model
	private Faction getSmallestFaction(){
		Faction smallestFaction = null;
		int unitsInSmallest = 50;
		for (Faction faction : this.getFactions()) {
			if (faction.getNbUnits() < unitsInSmallest){
				smallestFaction = faction;
				unitsInSmallest = faction.getNbUnits();
			}
		}
		return smallestFaction;
	}
	
	/**
	 * Adds a unit to a faction
	 * 
	 * @param unit
	 * 			the unit who is needing a faction
	 * @post the world will have a new faction if the max number of factions is not yet reached
	 * 
	 * @return The new faction for the given unit
	 * @return The smallest faction is returned
	 * 
	 */
	@Raw @Model
	private Faction getFactionForUnit(Unit unit){
		Faction faction;
		if (this.getFactions().size()<MAX_FACTIONS){
			faction = new Faction(this);
			this.addAsFaction(faction);
		}else{
			faction = this.getSmallestFaction();
		}
		return faction;
		
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
	@Raw @Model
	private void addAsFaction(@Raw Faction faction) {
		assert (faction != null) && (faction.getWorld() == this);
		this.factions.add(faction);
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
	@Raw @Model
	private void removeAsFaction(@Raw Faction faction){
		assert this.hasAsFaction(faction) && (faction.getWorld() == null);
		factions.remove(faction);
	}
	
	//------------------------INSPECTORS
	
	/**
	 * Check whether this world has the given faction as one of its
	 * factions.
	 * 
	 * @param  faction
	 *         The faction to check.
	 */
	@Basic @Raw
	public boolean hasAsFaction(@Raw Faction faction) {
		return factions.contains(faction);
	}
	
	/**
	 * Check whether this world can have the given faction
	 * as one of its factions.
	 * 
	 * @param  faction
	 *         The faction to check.
	 * @return True if and only if the given faction is effective
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction) {
		return faction != null;
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
	/**
	 * Adds the unit to this world and gives the unit a faction
	 * 
	 * @param unit
	 * 			a unit for this world
	 * 
	 * @post the unit will belong now to this world
	 * @post the unit will belong now to his new faction
	 * 
	 * @effect The world will have this unit as member
	 * @effect The faction will have the unit as member7
	 * 
	 * @throws IllegalArgumentException
	 * 			if the unit is dead or already belongs to a world
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException{
		
		if(!unit.isAlive() || unit.getWorld() != null)
			throw new IllegalArgumentException();
		
		if(this.getUnits().size() < MAX_UNITS){
			
			
			//ADDING UNIT TO WORLD
			System.out.println("Adding unit to world");
			this.units.add(unit);
			unit.setWorld(this);
			
			//ADDING UNIT TO FACTION
			System.out.println("adding unit to faction");
			Faction faction = this.getFactionForUnit(unit);
			unit.setFaction(faction);
			faction.addUnit(unit);
			System.out.println("succesfully added unit");
		
		}
	}
	//------------------------DESTRUCTORS
	/**
	 * Remove the given Unit from the set of Units of this World.
	 * 
	 * @param  unit
	 *         The Unit to be removed.
	 * @pre    This World has the given Unit as one of
	 *         its Units, and the given Unit does not
	 *         reference any World.
	 *       | this.hasAsUnit(unit) &&
	 *       | (unit.getWorld() == null)
	 * @post   This World no longer has the given Unit as
	 *         one of its Units.
	 *       | ! new.hasAsUnit(unit)
	 */
	@Raw
	public void removeAsUnit(Unit unit) {
		assert this.hasAsUnit(unit) && (unit.getWorld() == null);
		units.remove(unit);
	}
	//------------------------INSPECTORS
	/**
	 * Check whether this World has the given Unit as one of its
	 * Units.
	 * 
	 * @param  unit
	 *         The Unit to check.
	 */
	@Raw
	public boolean hasAsUnit(@Raw Unit unit) {
		return units.contains(unit);
	}
	
	//------------------------HELPERS
	
	/**
	 * Gives back a random location for a unit
	 * 
	 * @return a random position for a unit inside the game world
	 * 			The type where the unit is located is non solid
	 * 			The type where the unit is non solid or is ground level
	 */
	@Raw
	public int[] getRandomPositionForUnit() {
		
		int nbX = this.getNbCubesX();
		int nbY = this.getNbCubesY();
		int nbZ = this.getNbCubesZ();
		
		int X = (int) (Math.random()*nbX);
		int Y = (int) (Math.random()*nbY);
		int Z = (int) (Math.random()*nbZ);
		
		//get a random non solid location
		while(isSolidCube(new int[]{X,Y,Z})){
			X = (int) (Math.random()*nbX);
			Y = (int) (Math.random()*nbY);
			Z = (int) (Math.random()*nbZ);	
		};
		// go down till you hit ground or level 0
		while(!isSolidCube(new int[]{X,Y,Z-1}) && Z ==0){
			Z= Z-1;	
		};
		
		return new int[] {X, Y, Z};
	}
	/**
	 * 
	 * @param enableDefaultBehavior
	 * 		whether the default behavior should be enabled or not
	 * @effect
	 * 		A random unit will be created with random properties
	 * @return
	 * 		The random unit
	 * 		
	 */
	public Unit getRandomUnit(boolean enableDefaultBehavior){
		
		Random rand = new Random();
		int MIN = 25;
		int MAX = 100;
		
		
		String name = "HillBilly"+(char)(this.getUnits().size()%26+'a');
		
		int[] position = this.getRandomPositionForUnit();
		
		int weight = rand.nextInt((MAX - MIN) + 1) + MIN;
		int agility = rand.nextInt((MAX - MIN) + 1) + MIN;
		int strength = rand.nextInt((MAX - MIN) + 1) + MIN;
		int toughness = rand.nextInt((MAX - MIN) + 1) + MIN;
		
		Unit unit = new Unit(name,position,weight,agility,strength,toughness, enableDefaultBehavior);
		return unit;
	}
	

}
