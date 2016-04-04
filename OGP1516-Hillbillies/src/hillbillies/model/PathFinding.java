package hillbillies.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import be.kuleuven.cs.som.annotate.Immutable;

public class PathFinding {
	
	private World world;
	private ArrayList<int[]> path = new ArrayList<>();
	private Set<int[]> closedset = new HashSet<>();
	private TreeSet<Node> openset = new TreeSet<Node>();
	
	public PathFinding(World world, int[] start, int[] target) throws IllegalArgumentException{
		this.world = world;
		this.calculateFastestPath(start, target);
	}
	
	public class Node implements Comparable<Node>{
		
		private int[] myPos;
		private double gvalue;
		private double fvalue;
		private Node parent;
		
		public Node(int[] pos, double g, double f, Node parent){
			this.myPos = pos;
			this.gvalue = g;
			this.fvalue = f;
			this.parent = parent;	
		}
		
		//------------------------GETTERS
		public int[] getPosition(){
			return this.myPos;
		}
		public double getGValue(){
			return this.gvalue;
		}
		public Node getParent(){
			return this.parent;
		}
		public double getFValue(){
			return this.fvalue;
		}
		
		//----------------------SETTERS
		public void setParent(Node parent){
			this.parent = parent;
		}
		public void setGValue(double gvalue){
			this.gvalue = gvalue;
		}
		public void setFValue(double fvalue){
			this.fvalue = fvalue;
		}

		//Implement comparable for a natural ordening of the treeset
		@Override
		public int compareTo(Node o) {
			if (this.equals(o)){
				return 0;
			}
			if (this.getFValue()<o.getFValue()){
				return -1;
			} else return 1;
		}
	}
	
	public void calculateFastestPath(int[] start, int[] target) throws IllegalArgumentException{
		
		openset.add(new Node(start,0,getEstimatedTimeTo(start,target),null));
		
		while (openset.size()>0){
			
			Node current = openset.pollFirst();
			closedset.add(current.getPosition());
			
			//TERMINATE PATHFINDING
			if (Utils.equals(current.getPosition(), target)){
				calculatePath(current);
				break;
			}
			//CONTINUE SEARCHING
			for (int[] pos : world.quickFindReachableAdjacents((current.getPosition()))){
				// time consuming factor!!!!!
				
				//Already a shorter way to this position
				if (closedset.contains(pos)){
					continue;
				}
				
				double tempGscore = current.getGValue()+getExactTimeToAdjacent(current.getPosition(),pos);
				Node matchingNode = getMatchingNodeFromOpenSet(pos);
				
				if (matchingNode!=null && tempGscore >= matchingNode.getGValue()){
					continue;
				} else if (matchingNode != null){
					openset.remove(matchingNode);
				}
				
				matchingNode = new Node(pos, tempGscore, tempGscore+getEstimatedTimeTo(pos,target), current);
				openset.add(matchingNode);
			}
		}
	}
	
	
	@Immutable
	private double getExactTimeToAdjacent(int[] position, int[] pos) {
		return 1.0;
	}



	private double getEstimatedTimeTo(int[] start, int[] target) {
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

	private Node getMatchingNodeFromOpenSet(int[] pos){
		for (Node node : openset){
			if (Utils.equals(node.getPosition(),pos)){
				return node;
			}
		}
		return null;
	}
	
	private void calculatePath(Node target){
		Node currentNode = target;
		while (currentNode.getParent()!=null){
			int[] pos = currentNode.getPosition();
			path.add(pos);
			currentNode = currentNode.getParent();
		}
	}
	
	public int[] moveToNextPos(){
		if (hasPathCompleted()){
			return null;
		}
		int[] nextPos = this.path.get(this.path.size()-1);
		this.path.remove(nextPos);
		return nextPos;
	}


	/**
	 * @return whether the path is completed
	 */
	public boolean hasPathCompleted() {
		return this.path.isEmpty();
	}
	
	public boolean setContains(Set<int[]> set, int[] pos){
		for (int[] position : set){
			if (Utils.equals(pos,position)){
				return true;
			}
		}
		return false;
	}
	
	
	
	public ArrayList<int[]> getPath(){
		return this.path;
	}
	
}
