package hillbillies.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

/**
 * @author Holger
 * @version 1.0
 */

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
 * @Invar Each world must have proper units.
 * 		| this.hasProperUnits()
 * @Invar Each world must have proper factions.
 * 		| this.hasProperFactions()
 * @Invar Each world must have proper logs.
 * 		| this.hasProperLogs()
 * @Invar Each world must have proper boulders.
 * 		| this.hasProperBoulders()
 *       
 *       
 */

public class World {
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	//PUBLIC
	public static final int TYPE_AIR = 0;
	public static final int TYPE_ROCK = 1;
	public static final int TYPE_TREE = 2;
	public static final int TYPE_WORKSHOP = 3;
	public static final int[] VALID_CUBE_TYPES = {TYPE_AIR,TYPE_ROCK,TYPE_TREE,TYPE_WORKSHOP};
	
	//PRIVATE
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
 *       
 * @post No factions belong to this new world
 * @post No units belong to this new world
 * 
 * @post No items belong to this world, unless there are some spawned by
 * 		| this.makeAllSolidsConnected();
 * @effect Let all solids be connected to the border of the game world
 * 		| this.makeAllSolidsConnected();
 */
public World(int[][][] terrainTypes, TerrainChangeListener modelListener) throws IllegalArgumentException {
	
	//Connection with the GUI
		if(modelListener==null)
			throw new IllegalArgumentException();
		this.modelListener = modelListener;

	this.setTerrainTypes(terrainTypes);
	
	//Initialize immutable attributes
	this.nbCubesX = terrainTypes.length;
	this.nbCubesY = terrainTypes[0].length;
	this.nbCubesZ = terrainTypes[0][0].length;
	
	//Initialize associations
	this.units = new HashSet<Unit>(MAX_UNITS);
	this.factions = new HashSet<Faction>(MAX_FACTIONS);
	this.items = new HashSet<Item>();
	
	//Connection to ConnectedToBorder
	this.border = new ConnectedToBorder(this.getNbCubesX(),this.getNbCubesY(), this.getNbCubesZ());
	this.makeAllSolidsConnected();
	
	

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
	public int getCubeType(int[] position) throws IllegalArgumentException {
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
		return 0<=X&&X<this.getNbCubesX()&&
				0<=Y&&Y<this.getNbCubesY()&&
				0<=Z&&Z<this.getNbCubesZ();	
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
		int type = this.getCubeType(position);
		return (type == TYPE_ROCK) || (type == TYPE_TREE);	
	}
	
	/**
	 * Gives back all the adjacent cubes
	 * @param position
	 * 			The position of the cube
	 * @return
	 * 		a List<int[]> with all the locations of the surrounding cubes
	 */
	public List<int[]> getAdjacentCubes(int[] position){
		
		List<int[]> adjacentCubes = new ArrayList<>();
		
		int x = position[0];
		int y = position[1];
		int z = position[2];
		
			for (int dx=-1; dx<=1;dx++){
				for (int dy=-1; dy<=1;dy++){
					for (int dz=-1; dz<=1;dz++){
						int[] adjacentCube = new int[]{x+dx,y+dy,z+dz};
						if(isValidPosition(adjacentCube) && !(dx==0&&dy==0&&dz==0)){
							adjacentCubes.add(adjacentCube);
						}	
					}
				}	
			}
			return adjacentCubes;
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
		List<int[]> adjacentCubes = getAdjacentCubes(position);
		
		for(int[] adjacentCube: adjacentCubes){
			if(isSolidCube(adjacentCube))
				return true;
		}
		return false;
	}
	
	/**
	 * Checks whether the cube under the give position is solid
	 * 
	 * @param position
	 * 		the given position
	 * @return
	 * 		whether the cube under the give position is solid
	 * 			 with the level under z=0 considerd as 0
	 */
	public boolean isSolidUnder(int[] position){
		return position[2] == 0 || 
				isSolidCube(Utils.getPositionUnder(position));
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
	public void caveIn(int[] position) throws IllegalArgumentException{
		
		if(!isSolidCube(position) && !isValidPosition(position))
			throw new IllegalArgumentException("The cube is not solid or is on an invalid location");
		
		List<int[]> caveInList = this.getConnectedToBorder().changeSolidToPassable(
				position[0], position[1], position[2]);
		caveInList.add(position);
		
		for(int[] caveInPosition: caveInList){
			
			if(isSolidCube(caveInPosition)){
				
				//REPLACE CUBE BY AIR
				int type = this.getCubeType(caveInPosition);
				this.setcubeType(TYPE_AIR, caveInPosition);	
				
				//SPAWN RAWMATERIAL
				double probability = 0.25;
				Random rand = new Random();
				
				if (rand.nextDouble() <= probability){
					double[] spawnPosition = Utils.getCubeCenter(caveInPosition);
					
					if (type == TYPE_ROCK){
						this.createBoulder(spawnPosition);
						System.out.println("spawn boulder");
						
					}else if(type == TYPE_TREE){
						this.createLog(spawnPosition);
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
	
	
	/**
	 * Invokes the advanceTime method on all objects present in this World.
	 * This are alle units and all items
	 * 
	 * @param dt
	 * 		The time by which to advance, expressed in seconds.
	 * 
	 * @effect The time will advance for all units
	 * @effect The time will advance for all items
	 */
	public void advanceTime(double dt) throws IllegalArgumentException, IllegalStateException{
		
		if (!(0.0<=dt&&dt<=0.2))
			throw new IllegalArgumentException();
		
		//UNITS
		for(Unit unit: this.units){
			unit.advanceTime(dt);	
		}
		//ITEMS
		for(Item item: this.items){
			item.advanceTime(dt);
		}
		
	}
	
	
	
	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------FACTIONS-----------------------------------
	 *___________________________________________________________________

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
	public Set<Faction> factions;
	
	
	//------------------------GETTERS
	
	/**
	 * Gives back the active factions of this world
	 */
	@Basic @Raw
	public Set<Faction> getAllFactions(){
		Set<Faction> allFactions = new HashSet<>(factions);
		return allFactions;
	}
	
	/**
	 * @return the amount of factions in the world
	 */
	public int getNbFactions(){
		return this.factions.size();
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
		for (Faction faction : this.getAllFactions()) {
			if (faction.getNbUnits() < unitsInSmallest){
				smallestFaction = faction;
				unitsInSmallest = faction.getNbUnits();
			}
		}
		return smallestFaction;
	}
	
	/**
	 * TODO
	 * 
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
		if (this.getAllFactions().size()<MAX_FACTIONS){
			//take the faction for the unit
			faction = unit.getFaction();
			this.addAsFaction(faction);
		}else{
			//give unit a place in the smallest faction
			faction = this.getSmallestFaction();
		}
		return faction;
		
	}
	
	
	//------------------------SETTERS
	

	/**
	 * 
	 * Add the given faction to the set of factions of this world.
	 * 
	 * @param  faction
	 *         The faction to be added.
	 * 
	 * @post   This world has the given faction as one of its factions.
	 * 
	 * @throws IllegalArgumentException
	 * 		|!canHaveAsFaction(faction) || this.factions.size()>=MAX_FACTIONS
	 * 		
	 *       
	 */
	@Raw @Model
	private void addAsFaction(@Raw Faction faction) throws IllegalArgumentException {
		if(!canHaveAsFaction(faction) || this.factions.size()>=MAX_FACTIONS)
			throw new IllegalArgumentException();
		this.factions.add(faction);
	}

	/**
	 * Remove the given faction from the set of factions of this world.
	 * 
	 * @param  faction
	 *         The faction to be removed.
	 *       
	 * @post   This world no longer has the given faction as
	 *         one of its factions.
	 *         
	 * @throws IllegalArgumentException
	 * 		|!this.hasAsFaction(faction)
	 * 
	 *       
	 */
	@Raw
	public void removeAsFaction(@Raw Faction faction) throws IllegalArgumentException{
		if(!this.hasAsFaction(faction))
			throw new IllegalArgumentException();
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
	public Set<Unit> units;
	//------------------------GETTERS
	
	/**
	 * Gives back the units of the world
	 */
	@Basic @Raw
	public Set<Unit> getAllUnits() {
		Set<Unit> AllUnits = new HashSet<>(units);
		return AllUnits;
	}
	
	/**
	 * @return the amount of units in the world
	 */
	public int getNbUnits(){
		return this.units.size();
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
	 * @effect The unit will leave his old faction
	 * @effect The unit will have a new faction assigned
	 * 
	 * @throws IllegalArgumentException
	 * 			if the unit is dead or already belongs to a world
	 */
	public void addUnit(Unit unit) throws IllegalArgumentException{
		
		if(!this.canHaveAsUnit(unit) || unit.getWorld() == this)
			throw new IllegalArgumentException();
		
		//INAPROPRIATE POSITION
		if(!isValidPosition(Utils.getCubePosition(unit.getPosition())) 
				|| isSolidCube(Utils.getCubePosition(unit.getPosition())))
				unit.setPosition(Utils.getCubeCenter(getRandomPositionForUnit()));
		
		// SILENTLY REJECT UNIT
		if(this.units.size() < MAX_UNITS){
			
			//ADDING UNIT TO WORLD
			this.units.add(unit);
			unit.setWorld(this);
			
			//ADDING UNIT TO FACTION
			Faction faction = this.getFactionForUnit(unit);	
			unit.getFaction().removeUnit(unit);
			faction.addUnit(unit);
			
			System.out.println("Succesfully added unit: " + unit.getName());
		}
	}

	/**
	 * Handles the bidirectional association given Unit from the set of Units of this World.
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
	 * @effect The unit has no longer this world as world
	 */
	@Raw
	public void removeUnit(Unit unit) {
		assert this.hasAsUnit(unit) && unit.getWorld() == this;
		units.remove(unit);
		unit.setWorld(null);

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
	/**
	 * Check whether this world can have the given unit
	 * as one of its units.
	 * 
	 * @param  Unit
	 *         The unit to check.
	 * @return True if and only if the given unit is effective
	 *         and if the unit is alive
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit){
		return unit != null && unit.isAlive();
	}
	/**
	 * Check whether this world has proper units attached to it.
	 * 
	 * @return True if and only if this world can have each of the
	 *         units attached to it as one of its units,
	 *         and if each of these units references this faction as
	 *         the faction to which they are attached.
	 *         And if the amount of units does not exceed the max amount
	 */
	public boolean hasProperUnits() {
		
		if (this.getNbUnits()>MAX_UNITS)
			return false;
	
		for (Unit unit : this.units) {
			if (!canHaveAsUnit(unit) || unit.getWorld() != this)
				return false;
		}
		return true;
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
		while( Z != 0 && !isSolidCube(new int[]{X,Y,Z-1})){
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
		
		
		String name = "HillBilly"+(char)(this.getNbUnits()%26+'a');
		
		int[] position = this.getRandomPositionForUnit();
		
		int weight = rand.nextInt((MAX - MIN) + 1) + MIN;
		int agility = rand.nextInt((MAX - MIN) + 1) + MIN;
		int strength = rand.nextInt((MAX - MIN) + 1) + MIN;
		int toughness = rand.nextInt((MAX - MIN) + 1) + MIN;
		
		Unit unit = new Unit(name,position,weight,agility,strength,toughness, enableDefaultBehavior);
		return unit;
	}
	
	/*___________________________________________________________________
	 *___________________________________________________________________
	 * -----------------------ITEMS--------------------------------------
	 * --------------------CONTROLLING CLASS-----------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	/**
	 * Variable referencing a set collecting all the Items
	 * of this World.
	 * 
	 * @Invar  The referenced set is effective.
	 *       | items != null
	 * @Invar  Each Item registered in the referenced list is
	 *         effective and not yet terminated.
	 *       | for each item in items:
	 *       |   ( (item != null) &&
	 *       |     (! item.isTerminated()) )
	 */
	private Set<Item> items;
	
	/**
	 * Check whether this World has the given Item as one of its
	 * Items.
	 * 
	 * @param  item
	 *         The Item to check.
	 */
	@Basic
	@Raw
	public boolean hasAsItem(@Raw Item item) {
		return items.contains(item);
	}
	
	/**
	 * Check whether this World can have the given Item
	 * as one of its Items.
	 * 
	 * @param  item
	 *         The Item to check.
	 * @return True if and only if the given Item is effective
	 *         and that Item is a valid Item for this World.
	 *       | result == (item != null) && (item.canHaveAsWorld(this))
	 */
	@Raw
	public boolean canHaveAsItem(Item item) {
		return (item != null) && (item.canHaveAsWorld(this));
	}
	

	/**
	 * Check whether this World has proper Items attached to it.
	 * 
	 * @return True if and only if this World can have each of the
	 *         items attached to it as one of its Items,
	 *         and if each of these Items references this World as
	 *         the World to which they are attached.
	 *       | for each item in items:
	 *       |   if (hasAsItem(item))
	 *       |     then canHaveAsItem(item) &&
	 *       |          (item.getWorld() == this)
	 */
	public boolean hasProperItems() {
		for (Item item : this.items) {
			if (!canHaveAsItem(item) || item.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * Return the number of Items associated with this World.
	 *
	 * @return  The total number of Items collected in this World.
	 */
	public int getNbItems() {
		return items.size();
	}
	
	/**
	 * Add the given Item to the set of Items of this World.
	 * 
	 * @param  item
	 *         The Item to be added.
	 * @pre    The world can have this item as item 
	 * 				and the item does not yet reference any world
	 * @post   This World has the given Item as one of its items.
	 * @effect the item has this world as its world
	 */
	public void addItem(@Raw Item item) {
		assert this.canHaveAsItem(item) && item.getWorld() == this;
		items.add(item);
		item.setWorld(this);
	}
	
	/**
	 * Remove the given Item from the set of Items of this World.
	 * 
	 * @param  item
	 *         The Item to be removed.
	 * @pre    This World has the given Item as one of
	 *         its items, and the given Item does not
	 *         reference any World.
	 *       | this.hasAsItem(item) &&
	 *       | (item.getWorld() == null)
	 * @post   This World no longer has the given Item as
	 *         one of its items.
	 *       | ! new.hasAsItem(item)
	 */
	@Raw
	public void removeItem(Item item) {
		assert this.hasAsItem(item) && (item.getWorld() == this);
		items.remove(item);
		item.setWorld(null);
	}
	
	//---------------------ADDITIONAL INSPECTORS
	
	/**
	 * return  a clone of the set of Items that belong to this world
	 */
	@Basic
	public Set<Item> getAllItems(){
		Set<Item> allItems = new HashSet<>(items);
		return allItems;
	}
	
	/**
	 * Returns all items located on the cube, if any.
	 * 
	 * @param position
	 * 		The position of the cube
	 * 
	 * @return
	 * 		A new ArrayList of all items with positions located on the cube.
	 */
	@Raw
	public List<Item> getAllItemsOnPosition(int[] position) throws IllegalArgumentException {
		
		if (!isValidPosition(position)) {
			throw new IllegalArgumentException("Given position is invalid");
		}
		
		List<Item> AllItems = new ArrayList<Item>();
		
		for (Item item : this.items) {
			if (Utils.equals(position, Utils.getCubePosition(item.getPosition()))) {
				AllItems.add(item);
			}
		}
		
		return AllItems;
	}
	
	/**
	 * Returns a random item on the give position or null
	 * 
	 * @param position
	 * 		The position of the cube
	 * 
	 * @return
	 * 		A random item or null
	 * 
	 * @effect
	 * 		| getAllItemsOnPosition(position)
	 * 		
	 * 		
	 */
	@Raw 
	public Item getItemOnPosition(int[] position) throws IllegalArgumentException{
		List<Item> AllItems = getAllItemsOnPosition(position);
		
		if(AllItems.isEmpty())
			return null;
		else
			return AllItems.get(0);
	
	}

	
	
	//-------------------BOULDERS
	
	
	/**
	 * Creates a new Boulder in this World, on the position given.
	 * 
	 * @param position
	 * 		the position for the boulder
	 */
	public void createBoulder(double[] position) {
		new Boulder(position, this);
	}
	
	/**
	 * Gives back all Boulders in this World.
	 * 
	 * @return
	 * 		A Set of all Boulders in this World. 
	 */
	public Set<Boulder> getAllBoulders() {
		Set<Boulder> allBoulders = new HashSet<Boulder>();
		
		for (Item item : this.items) {
			if (item instanceof Boulder) {
				allBoulders.add((Boulder)item);
			}
		}
		return allBoulders;
	}


	
	//--------------------LOGS
	
	/**
	 * Gives back all Logs in this World.
	 * @return
	 * 		A new (Hash)Set of all Logs in this World. That makes this a
	 * 		shallow copy.
	 */
	public Set<Log> getAllLogs() {
		Set<Log> result = new HashSet<Log>();
		for (Item item : this.items) {
			if (item instanceof Log) {
				result.add((Log)item);
			}
		}
		return result;
	}

	/**
	 * Creates a new Log in this World, on the given position
	 * 
	 * @param position
	 * 		the position for the log
	 */
	public void createLog(double[] position) {
		new Log(position, this);	
	}


}
