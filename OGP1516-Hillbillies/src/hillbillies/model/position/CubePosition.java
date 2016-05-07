package hillbillies.model.position;

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
}
