package hillbillies.model.position;
import be.kuleuven.cs.som.annotate.*;

@Value
public class Position<T extends Number> {

	private final T x, y, z;

	/**
	 * constructs a position out three parameters
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Position(T x, T y, T z) {
		this.x = x;
		this.y = y;
		this.z = z;	
	}
	
	/**
	 * constructs a position out of an array
	 * 
	 * @param array
	 */
	public Position(T[] array) {
		this.x = array[0];
		this.y = array[1];
		this.z = array[2];	
	}
	
	/**
	 * @return the x
	 */
	@Basic @Immutable
	public final T getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	@Basic @Immutable
	public final T getY() {
		return y;
	}
	
	/**
	 * @return the z
	 */
	@Basic @Immutable
	public final T getZ() {
		return z;
	}

	/**
	 * A symbolic representation of the position
	 * 
	 * @return
	 * 		A concatenation of x, y and z
	 */
	@Override
	public String toString() {
		return "Position(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")";
	}
	/**
	 * Two object equal each other when they have the same class
	 */
	@Override
	public boolean equals(Object other){
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		return true;
	}


}

