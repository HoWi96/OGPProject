package hillbillies.model.position;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class CubePosition extends Position<Integer> {
	
	public CubePosition(int x, int y, int z) {
		super(x, y, z);
	}

	public CubePosition(int[] array) {
		super(array[0], array[1], array[2]);
	}
	
	/**
	 * Creates an array of the cube position
	 * 
	 * @return int[]
	 */
	public int[] toArray(){
		int[] array = new int[3];
		array[0] = getX();
		array[1] = getY();
		array[2] = getZ();
		return array;
	}
	
	public double getDistanceTo(CubePosition other){
		
		double distance = Math.sqrt(
			Math.pow(this.getX()-other.getX(),2) + 
			Math.pow(this.getY()-other.getY(),2) +
			Math.pow(this.getZ()-other.getZ(),2));
		
		return distance;	
	}
}
