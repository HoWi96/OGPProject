package hillbillies.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;
import hillbillies.model.helper.Utils;

/**
 * A class used to find paths for units in the game world
 * 
 * @author Holger Willems |2e bach. ing.: OOP
 * @date 10/04/2016
 * @Version 2.0
 *
 */

public class PathFinding {
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------VARIABLES---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//---------Connection with the world
	/*
	 * The world for this pathFinding class
	 */
	private final World world;
	
	//---------A* algorithm
	/*
	 * linked list: ideal to remove/add last element in constant time
	 */
	private LinkedList<int[]> path = new LinkedList<>();
	/*
	 * HashSet for the closedSet: ideal to access information in constant time
	 */
	private Set<int[]> closedSet = new HashSet<>();
	/*
	 * TreeSet for the openSet: ideal for making yourself a natural ordering (which is recommended for the A*)
	 */
	private TreeSet<Node> openSet = new TreeSet<Node>();
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTRUCTOR---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Initialize the pathFinding with a given world, a given start position and a given target position
	 * 
	 * @param world
	 * 		the world for the pathFinding algorithm
	 * @param start
	 * 		the start position
	 * @param target
	 * 		the target position
	 * 
	 * @effect calculateFastestPath(start, target)

	 * @throws IllegalArgumentException
	 *  		| world == null
	 */
	public PathFinding(World world, int[] start, int[] target) throws IllegalArgumentException{
		if(world == null)
			throw new IllegalArgumentException();
		this.world = world;
		this.calculateFastestPath(start, target);
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------WORLD--------------------------------------
	 * -----------------UNI DIRECTIONAL----------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/*
	 * Returns the world of the pathfinding algorithm
	 */
	@Basic @Raw @Immutable
	public World getWorld(){
		return this.world;
	}
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------A* ALGORITHM-------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	/**
	 * Calculates the fastest path from start to target
	 * 
	 * @param start
	 * 		the starting position
	 * @param target
	 * 		the target position
	 * @throws IllegalArgumentException
	 * 		!world.isValidPosition(start) || !world.isValidPosition(target)
	 * @post
	 * 		the path will contain the fastest path or no path at all
	 */
	public void calculateFastestPath(int[] start, int[] target) throws IllegalArgumentException{
		
		if(!this.getWorld().isValidPosition(start) || !this.getWorld().isValidPosition(target))
			throw new IllegalArgumentException();
		
		openSet.add(new Node(start,0,getHeuristicCostEstimate(start,target),null));
		
		//We set a maximum on the items of the closed set so we don't have to calculate exhaustively long
		while (!openSet.isEmpty() && closedSet.size()<800){
			
			Node current = openSet.pollFirst();
			closedSet.add(current.getPosition());
			
			//TERMINATE PATHFINDING
			if (Utils.equals(current.getPosition(), target)){
				reconstructPathTo(current);
				break;
			}
			
			//CONTINUE SEARCHING
			for (int[] pos : this.getWorld().quickFindReachableAdjacents((current.getPosition()))){
				// time consuming factor!!!!!
				
				//Already a shorter way to this position
				if (closedSet.contains(pos)){
					continue;
				}
				
				double tentativeGscore = current.getGScore()+getDistanceBetween(current.getPosition(),pos);
				Node neighbor = getCorrespondingNodeFrom(pos);
				
				if(neighbor !=null){
					if(tentativeGscore >= neighbor.getGScore())
						continue;
					else
						openSet.remove(neighbor);
				} 
				
				neighbor = new Node(pos, tentativeGscore, tentativeGscore+getHeuristicCostEstimate(pos,target), current);
				openSet.add(neighbor);
			}
		}
	}
	
	//---------------------------------------HELPERS
	
	/**
	 * Calculates the fastest path, storing it in the path variable
	 * 
	 * @param target
	 * 		The target node
	 * 
	 * @post
	 * 		the path is stored in the path variable
	 * 		while the first node is not reached, 
	 * 		the position of the current node will be added to the path
	 */
	private void reconstructPathTo(Node target){
		Node currentNode = target;
		while (currentNode.getParent()!=null){
			path.addLast(currentNode.getPosition());
			currentNode = currentNode.getParent();
		}
	}
	
	/**
	 * Returns the exact time to move to an adjacent
	 * 
	 * @param start
	 * 		the starting position
	 * @param target
	 * 		the target position
	 * @return
	 * 		getEstimatedTimeTo(start,target)
	 */
	private double getDistanceBetween(int[] start, int[] target) {
		return getHeuristicCostEstimate(start,target);
	}


	/**
	 * Returns an estimated time to move from the the starting position to the target
	 * 
	 * @param start
	 * 		the start position
	 * @param target
	 * 		the target position
	 * @return
	 * 		the maximal change in direction, taking in mind that moves in z direction change the time slightly
	 */
	private double getHeuristicCostEstimate(int[] start, int[] target) {
		double dx = Math.abs(target[0]-start[0]);
		double dy = Math.abs(target[0]-start[0]);
		double dz = target[0]-start[0];
		
			if(dz>0)
				dz = Math.abs(dz)/0.5;
			else
				dz = Math.abs(dz)/1.2;
			
		double maxSameLevel = Math.max(dx, dy);
		double maxTotal = Math.max(maxSameLevel, dz);
		
		return maxTotal;
	}
	/**
	 * Returns the corresponding node of the open set for the given position
	 * 
	 * @param position
	 * 		the position to correspond with
	 * @return
	 * 		the matching node or null
	 */
	private Node getCorrespondingNodeFrom(int[] position){
		for (Node node : openSet){
			if (Utils.equals(node.getPosition(),position)){
				return node;
			}
		}
		return null;
	}

	/**
	 * Returns the next position, and deleting it at the same time from the path
	 * 
	 * @return
	 * 		the next position in the path
	 * @post
	 * 		the next position if removed from the path
	 */
	public int[] getNextPosition(){
		if (hasPathCompleted()){
			return null;
		}
		return path.removeLast();
	}

	/**
	 * @return whether the path is completed
	 */
	public boolean hasPathCompleted() {
		return path.isEmpty();
	}
	
	/**
	 * Returns the target position or if the path is completed null
	 * 
	 * @return the target position
	 * 			or null if the path is completed
	 */
	public int[] getTargetPosition(){
		if (hasPathCompleted()){
			return null;
		}
		int[] target = path.get(0);
		return target;
	}

	/*___________________________________________________________________
	 * __________________________________________________________________
	 * --------------------------NODE------------------------------------
	 * -----------------------NESTED CLASS-------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	
	/**
	 * 
	 * @author Holger
	 * 
	 * A class implementing the nodes used in the A* algorithm
	 * with a natural ordering for the nodes used in the treeSet
	 *
	 */
	@Value
	public class Node implements Comparable<Node>{
		
		
			//VARIABLES
			private final int[] position;
			private final Node parent;
			private final double gScore;
			private final double fScore;
			
			//CONSTRUCTOR
			/**
			 * Creates a node with immutable properties
			 * 
			 * @param position
			 * 			the position of the node
			 * @param gScore
			 * 		the g value of the node
			 * @param fScore
			 * 		the f value of the node
			 * @param parent
			 * 		the nodes parent
			 * @post
			 * 		the position will be set to the given position
			 * 		the parent will be set to the given parent
			 * 		the g value will be set to the given g
			 * 		the f value will be set to the given f
			 */
			public Node(int[] position, double gScore, double fScore, Node parent){
				this.position = position;
				this.parent = parent;	
				this.gScore = gScore;
				this.fScore = fScore;
				
			}
			
			//INSPECTORS
			/**
			 * returns the position
			 */
			@Basic @Immutable
			public int[] getPosition(){
				return this.position;
			}
			/**
			 * returns the gvalue
			 */
			@Basic @Immutable
			public double getGScore(){
				return this.gScore;
			}
			/**
			 * returns the parent
			 */
			@Basic @Immutable
			public Node getParent(){
				return this.parent;
			}
			/**
			 * returns the f value
			 */
			@Basic @Immutable
			public double getFScore(){
				return this.fScore;
			}
			
			/**
			 * Override functional method for natural ordering of nodes (used in TreeSet)
			 */
			@Override
			public int compareTo(Node otherNode) {
				if (this.equals(otherNode))
					return 0;
				if (this.getFScore()<otherNode.getFScore())
					return -1;
				else 
					return 1;
			}

			/* 
			 * Returns if two nodes are equal
			 */
			@Override
			public boolean equals(Object obj) {
				return super.equals(obj);
			}

			/* 
			 * Returns the hashcode for the node
			 */
			@Override
			public int hashCode() {
				return super.hashCode();
			}
			
			

		}
	
}
