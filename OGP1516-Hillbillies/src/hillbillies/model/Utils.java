package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Gives back all the adjacent cubes
	 * @param position
	 * 			The position of the cube
	 * @return
	 * 		a List<int[]> with all the locations of the surrounding cubes
	 */
	public static List<int[]> getAdjacentCubes(int[] position){
		
		List<int[]> adjacentCubes = new ArrayList<>(26);
		
		int x = position[0];
		int y = position[1];
		int z = position[2];
		
			for (int dx=-1; dx<=1;dx++){
				for (int dy=-1; dy<=1;dy++){
					for (int dz=-1; dz<=1;dz++){
						//the cube itself doesn't have to be part of the list
						if(!(dx==0&&dy==0&&dz==0)){
							adjacentCubes.add(new int[]{x+dx,y+dy,z+dz});
						}	
					}
				}	
			}
			return adjacentCubes;
	}




}
