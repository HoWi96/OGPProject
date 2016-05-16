package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

@Value
public enum TerrainType {
	
	AIR(0,true),
    ROCK(1,false),
    WOOD(2,false),
    WORKSHOP(3,true);

	private final int id;
	private final boolean passable;
	
	/**
	 * Creates a new terrainType
	 * 
	 * @param id
	 * @param passable
	 */
	TerrainType(int id, boolean passable){
		this.id = id;
		this.passable = passable;
    }

	/**
	 * @return true if passable
	 */
	@Basic @Raw @Immutable
	public boolean isPassable() {
		return passable;
	}

	/**
	 * @return the id
	 */
	@Basic @Raw @Immutable
	public int getId() {
		return id;
	}
	
	 /**
     * Static method to receive the terrain type, given its id.
     * 
     * @param id The id of the terrain type to retrieve
     * @return The corresponding terrain type
     */
    public static TerrainType fromId(int id){
        return TerrainType.values()[id];
    }
	
	
}
