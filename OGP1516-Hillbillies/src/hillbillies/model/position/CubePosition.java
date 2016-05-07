package hillbillies.model.position;

public class CubePosition extends Position<Integer> {

	public CubePosition(Integer x, Integer y, Integer z) {
		super(x, y, z);
	}

	public CubePosition(Integer[] array) {
		super(array);
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
