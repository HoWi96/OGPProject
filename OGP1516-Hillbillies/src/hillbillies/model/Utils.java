package hillbillies.model;

import java.util.Random;

/**
 * @author Holger
 * @version 1.0
 */

public class Utils {
	
	private static final double CUBE_LENGTH = 1;
	
	/**
	 * Checks whether two coordinates are identical.
	 * 
	 * @return	Returns true if the given positions are the same, false if they are not.
	 * 			| result == (position[0] == position2[0]) && (position1[1] == position2[1])
	 * 						&& (position1[2] == position2[2])
	 */
	public static boolean equals(double[] position1, double[] position2) {
		return (position1[0] == position2[0])&&
				(position1[1] == position2[1])&&
				(position1[2] == position2[2]);
	}
	
	public static boolean equals(int[] position1, int[] position2) {
		return (position1[0] == position2[0])&&
				(position1[1] == position2[1])&&
				(position1[2] == position2[2]);
	}

	/**
	 * Checks whether a position is in between two positions.
	 * 
	 * @return	Returns true if the position is in between the other positions.
	 * 			| result == (position2[0] <= positionInBetween[0] && positionInBetween[0] <= position1[0]) ||
	 * 						(position2[0] >= positionInBetween[0] && positionInBetween[0] >= position1[0]) &&
	 * 						(position2[1] <= positionInBetween[1] && positionInBetween[1] <= position1[1]) ||
	 * 						(position2[1] >= positionInBetween[1] && positionInBetween[1] >= position1[1]) &&
	 * 						
	 */
	public static boolean inBetween(double[] position1, double[] position2, double[] positionInBetween) {
		return (((position2[0]<=positionInBetween[0]&&positionInBetween[0]<=position1[0])||
				(position2[0]>=positionInBetween[0]&&positionInBetween[0]>=position1[0]))&&
				(position2[1]<=positionInBetween[1]&&positionInBetween[1]<=position1[1]||
				position2[1]>=positionInBetween[1]&&positionInBetween[1]>=position1[1])&&
				(position2[2]<=positionInBetween[2]&&positionInBetween[2]<=position1[2]||
				position2[2]>=positionInBetween[2]&&positionInBetween[2]>=position1[2]));
	}

	/**
	 * Gives back the position of the cube
	 * 
	 * @param position
	 *			The position of this Unit
	 *
	 * @return The position of the cube where the Unit is located
	 * 			| cubePosition[0] = (int) Math.floor(position[0]);
	 *			| cubePosition[1] = (int) Math.floor(position[1]);
	 *			| cubePosition[2] = (int) Math.floor(position[2]);
	 */
	public static int[] getCubePosition(double[] position){
		int[] cubePosition = new int[3];
		cubePosition[0] = (int) Math.floor(position[0]);
		cubePosition[1] = (int) Math.floor(position[1]);
		cubePosition[2] = (int) Math.floor(position[2]);
		return cubePosition;
		}

	/**
	 * Gives back the position of the center of the cube with the given position
	 * 
	 * @param cubePosition
	 * 			the position of the cube
	 * 
	 * @return
	 * 		The position of the center of the cube with given coordinates
	 * 		| result == new double[] {
	 * 		| 	(double)coordinates[0]+0.5,
	 * 		|	(double)coordinates[1]+0.5,
	 * 		| 	(double)coordinates[2]+0.5
	 * 		| }
	 */
	public static double[] getCubeCenter(int[] cubePosition) {
		return new double[] {(double)(cubePosition[0]+CUBE_LENGTH/2),
							 (double)(cubePosition[1]+CUBE_LENGTH/2),
							 (double)(cubePosition[2]+CUBE_LENGTH/2)};
	}

	/**
	 * 
	 * @param position1
	 * 		first position
	 * @param position2
	 * 		position to be added to position1
	 * @param factor
	 *  	factor to multiply position2 with
	 * @return
	 * 		result == {position1[0] + position2[0]*factor,
	 *		position1[1] + position2[1]*factor,
	 *		position1[2] + position2[2]*factor}
	 */
	public static double[] addPositionsFactor(double[] position1, double[] position2, double factor){
		double[] finalPosition = {position1[0] + position2[0]*factor,
								  position1[1] + position2[1]*factor,
								  position1[2] + position2[2]*factor
		};
		return finalPosition;
	}
	
	public static int[] addPositionsFactor(int[] position1, int[] position2, int factor){
		int[] finalPosition = {position1[0] + position2[0]*factor,
								  position1[1] + position2[1]*factor,
								  position1[2] + position2[2]*factor
		};
		return finalPosition;
	}
	
	

	/**
	 *  A method to generate random integers between 2 values 
	 *  
	 * @param max  
	 * 		the upper limit
	 * @param min  
	 * 		the  lower limit 
	 * 			
	 * @return 
	 * 		a random number between min and max (inclusive)
	 */
    public static int randInt(int min,int max) {
    	
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randInt = rand.nextInt((max - min) + 1) + min;
        
        return randInt;
    }
    
    /**
     * Returns exactly the position under the given position
     * @param position
     * 			the given position
     * @return
     * 			the position under the given position
     */
    public static int[] getPositionUnder(int[] position) {
    	return addPositionsFactor(position, new int[] {0,0,-1}, 1);
    }
    /**
     * returns whether two positions are adjacent
     * 
     * @param position1
     * 			the first position
     * @param position2
     * 			the second position
     * @return
     * 			whether they are adjacent
     */
    public static boolean areAdjacent(int[] position1, int[] position2){
    	int[] difference = addPositionsFactor(position1, position2, -1);
    	int dx = difference[0];
    	int dy = difference[1];
    	int dz = difference[2];
    	return !(Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1);
    }
    




}
