package hillbillies.model;

import be.kuleuven.cs.som.annotate.Value;


/**
 * Interface representing the terrain types of the world
 * 
 * @author Holger
 */
@Value
public interface ITerrainType {
	
	static final int TYPE_AIR = 0;
	static final int TYPE_ROCK = 1;
	static final int TYPE_TREE = 2;
	static final int TYPE_WORKSHOP = 3;
	
}
