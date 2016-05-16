package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

@Value
public enum TerrainType {
	
	AIR(0,false),
    ROCK(1,true),
    WOOD(2,true),
    WORKSHOP(3,false);

	private final int id;
	private final boolean solid;
	
	/**
	 * Creates a new terrainType
	 * 
	 * @param id
	 * @param passable
	 */
	TerrainType(int id, boolean passable){
		this.id = id;
		this.solid = passable;
    }

	/**
	 * @return true if solid
	 */
	@Basic @Raw @Immutable
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @return the id
	 */
	@Basic @Raw @Immutable
	public int getId() {
		return id;
	}
}
