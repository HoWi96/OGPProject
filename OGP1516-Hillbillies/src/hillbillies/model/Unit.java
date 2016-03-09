package hillbillies.model;


import be.kuleuven.cs.som.annotate.*;


/**
 * This class is all about the units of the game.
 * @author Holger Willems & Sebastiaan Van Overschee
 * @date 20/02/2016
 * @Version 0.0
 * 
 */

/** 
 * @invar  The Name of each Unit must be a valid Name for any
 *         Unit.
 *       | isValidName(getName())
 * @invar  The Position of each Unit must be a valid Position for any
 *         Unit.
 *       | isValidPosition(getPosition())
 *
 * @invar  The Strength of each Unit must be a valid Strength for any
 *         Unit.
 *       | isValidStrength(getStrength())
 *
 * @invar  The weight of each Unit must be a valid weight for any
 *         Unit.
 *       | isValidWeight(getWeight())
 *       
 * @invar  The Agility of each Unit must be a valid Agility for any
 *         Unit.
 *       | isValidAgility(getAgility())
 * @invar  The toughness of each Unit must be a valid toughness for any
 *         Unit.
 *       | isValidToughness(getToughness())
 * 
 * @invar  The hitpoints of each Unit must be a valid hitpoints for any
 *         Unit.
 *       | isValidHitpoints(getHitpoints())
 * 
 * @invar  The stamina of each Unit must be a valid stamina for any
 *         Unit.
 *       | isValidStamina(getStamina())
 * 
 * @invar  The orientation of each Unit must be a valid orientation for any
 *         Unit.
 *       | isValidOrientation(getOrientation())
 *       
 * @invar  The activity of each Unit must be a valid activity for any
 *         Unit.
 *       | isValidActivity(getActivity())
 *       
 * @invar  The nextPosition of each Unit must be a valid position for any
 *         Unit.
 *       | isValidPosition(getNextPosition())
 *       
 * @invar  The targetPosition of each Unit must be a valid position for any
 *         Unit.
 *       | isValidPosition(getTargetPosition())      
 *       
 */

public class Unit { 
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------CONSTANTS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/
	
	//TODO Place all constants here
	
	private static final String ALLOWED_NAME_PATTERN = "[a-zA-Z \"']+";
	
	private static final double CUBE_LENGTH = 1;
	private static final int MAXCOORDINATE = 50;
	private static final int MINCOORDINATE = 0;
	
	private static final float PI = (float) Math.PI;

	private static final double REST_INTERVAL = 60*3;
	private static final double NOTHING_INTERVAL = 10;
	
	/*___________________________________________________________________
	 * __________________________________________________________________
	 * -----------------------PARAMETERS---------------------------------
	 *___________________________________________________________________
	 *___________________________________________________________________*/

	/**
	* Variable registering the Name of this Unit.
	*/
	private String name;

	/**
	* Variable registering the Position of this Unit.
	* The length is set to 3 and will not change.
	*/
	private double[] position = new double[3];

	/**
	* Variable registering the weight of this Unit.
	*/
	private int weight;

	/**
	* Variable registering the Strength of this Unit.
	*/
	private int strength;

	/**
	* Variable registering the toughness of this Unit.
	*/
	private int toughness;

	/**
	* Variable registering the Agility of this Unit.
	*/
	private int agility;

	/**
	* Variable registering the hitpoints of this Unit.
	*/
	private int hitpoints;

	/**
	* Variable registering the stamina of this Unit.
	*/
	private double stamina;

	/**
	* Variable registering the orientation of this Unit.
	*/
	private float orientation;

	/**
	 * Variable registering the speed of this Unit.
	 */
	private double speed;

	/**
	 * The position where the unit is heading to
	 */
	private double[] targetPosition;

	/**
	 * The next position of the unit
	 */

	private double[] nextPosition;

	/**
	 * Variable registering if the unit is sprinting
	 */
	private boolean isSprinting;

	/**
	 * The time a unit needs to conduct an activity
	 */
	private float  progressTime;

	/**
	 * Variable registering the Activity of this Unit.
	 */
	private Activity activity;
	/**
	 * Variable registering the time till mandatory rest
	 */
	private double counterTillRest = 0.0;
	/**
	 * the time till default behaviour is activated
	 */
	private double counterTillDefault = 0.0;
	/**
	 * the step the unit is currently making
	 */
	private int[] step;
	/**
	 * indicates if the unit is operating in default behavior
	 */
	private boolean hasDefaultBehaviorEnabled;
	
/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------CONSTRUCTOR--------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/	

/**
 * Create a new unit with the given attributes.
 * 
 * PARAMETERS
 * @param name
 *            The name of the unit.
 * @param initialPosition
 *            The initial position of the unit, as an array with 3 elements
 *            {x, y, z}.
 * @param weight
 *            The initial weight of the unit
 * @param agility
 *            The initial agility of the unit
 * @param strength
 *            The initial strength of the unit
 * @param toughness
 *            The initial toughness of the unit
 * @param enableDefaultBehavior
 *            Whether the default behavior of the unit is enabled
 * 
 * ____________________________________________________________
 * 
 * @effect The Name of this new Unit is set to
 *         the given Name.
 *       | this.setName(name)
 *       
 * @effect The Position of this new Unit is set to
 *         the given Position.
 *       | this.setPosition(position)
 *       
 * @effect The nextPosition of this new Unit is set to
 *         the given Position.
 *       | this.setNextPosition(position)
 *       
 * @effect the orientation of this unit will be set to PI/2
 * 		| this.setOrientation(PI/2)
 * 
 * @effect the hitpoints will be set to the maximal amount of hitpoints
 * 		| this.setHitpoints(this.getMaxHitpoints))
 * 
 * @effect the stamina will be set to the maximal amount of stamina
 * 		| this.setStamina(this.getStamina))
 *       
 * 
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength. Otherwise, the Strength of this new Unit is equal
 *         to 100.
 *       | if (isValidInitialStrength(strength))
 *       |   then new.getStrength() == strength
 *       |   else new.getStrength() == 100
 *
 * @post   If the given Agility is a valid Agility for any Unit,
 *         the Agility of this new Unit is equal to the given
 *         Agility. Otherwise, the Agility of this new Unit is equal
 *         to 100.
 *       | if (isValidInitialAgility(agility))
 *       |   then new.getAgility() == agility
 *       |   else new.getAgility() == 100
 *
 * @post   If the given toughness is a valid toughness for any Unit,
 *         the toughness of this new Unit is equal to the given
 *         toughness. Otherwise, the toughness of this new Unit is equal
 *         to getToughness.
 *       | if (isValidInitialToughness(toughness))
 *       |   then new.getToughness() == toughness
 *       |   else new.getToughness() == 100
 *
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight. Otherwise, the weight of this new Unit is equal
 *         to 100.
 *       | if (isValidInitialWeight(weight) && isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       |   else new.getWeight() == 100
 * @post if the given boolean enableDefaultBehavior is true,
 * 		 the unit will go into default behavior. 
 * 		 Else the activity of the unit is set to "none"
 * 		| if(enableDefaultBehavior)
 *	    |	new.hasDefaultBehavior() == true;
 *	    | else
 *		|	new.getCurrentActivity() == "none";
 *
 */
public Unit(String name, int[] initialPosition, int weight, int agility,
			int strength, int toughness, boolean enableDefaultBehavior)
			throws IllegalArgumentException {
	
	setName(name);
	
	setPosition(getCubeCenter(initialPosition));
	
	setNextPosition(getCubeCenter(initialPosition));
	
	if (!isValidInitialStrength(strength))
		strength = 100;
	setStrength(strength);
	
	if (!isValidInitialAgility(agility))
		agility = 100;
	setAgility(agility);
	
	if (!isValidInitialToughness(toughness))
		toughness = 100;
	setToughness(toughness);
	
	if (!isValidInitialWeight(weight) || !isValidWeight(weight))
		weight = 100;
	setWeight(weight);
	
	setHitpoints(getMaxHitpoints());
	
	setStamina(getMaxStamina());
	
	setOrientation(PI/2);
	
	
	if(enableDefaultBehavior)
		this.startDefaultBehavior();
	else
		this.stopDefaultBehaviour();
}

/*
 * ------------------INITIAL CHECKERS-----------------------------
 */

/**
 * Checks whether the given value is a valid initial value for toughness.
 * 
 * @param  toughness
 *         The initial toughness to check.
 *         
 * @return true if and only if the given toughness is not larger than 100 or smaller than 25
 * 		| result == (toughness <= 100 && toughness >= 25)
 */
private boolean isValidInitialToughness(int toughness ){
	return (toughness <= 100 && toughness >= 25);
}

/**
 * Checks whether the given value is a valid initial value for strength.
 * 
 * @param  strength
 *         The initial strength to check.
 *         
 * @return true if and only if the given strength is not larger then 100 or smaller then 25
 * 		| result (strength <= 100 && strength >= 25)
 */
private boolean isValidInitialStrength(int strength ){
	return (strength <= 100 && strength >= 25);
}

/**
 * 
 *  Checks whether the given value is a valid initial value for agility.
 *  
 *	@param  agility
 *         The initial agility to check.
 *         
 * @return true if and only if the given agility is not larger then 100 or smaller then 25
 * 		| result == (agility <= 100 && agility >= 25)
 */
private boolean isValidInitialAgility(int agility ){
	return (agility <= 100 && agility >= 25);
}

/**
 *
 * Checks whether the given value is a valid initial value for weight.
 * 
 *  @param weight
 *         The initial weight to check.
 *         
 * @return true if and only if the given weight is not larger then 100 or smaller then 25
 * 		| result == (weight <= 100 && weight >= 25)
 */
private boolean isValidInitialWeight(int weight){
	return (weight <= 100 && weight >= 25);
}

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------PROPERTIES---------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/

//------------------------GETTERS

/**
 * Return the Name of this Unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
}

/**
 * Return the Position of this Unit.
 */
@Basic @Raw
public double[] getPosition() {
	return this.position;
}

/**
 * Return the weight of this Unit.
 */
@Basic @Raw
public int getWeight() {
	return this.weight;
}

/**
 * Return the Strength of this Unit.
 */
@Basic @Raw
public int getStrength() {
	return this.strength;
}

/**
 * Return the Agility of this Unit.
 */
@Basic @Raw
public int getAgility() {
	return this.agility;
}

/**
 * Return the toughness of this Unit.
 */
@Basic @Raw
public int getToughness() {
	return this.toughness;
}

/**
 * Return the hitpoints of this Unit.
 */
@Basic @Raw
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Return the stamina of this Unit.
 */
@Basic @Raw
public double getStamina() {
	return this.stamina;
}

/**
 * Return the orientation of this Unit.
 */
@Basic @Raw
public float getOrientation() {
	return this.orientation;
}

/**
* Return the speed of this Unit.
*/
@Basic @Raw
public double getSpeed(){
	return this.speed;
}

/*---------------------SETTERS
/**
 * Set the Name of this Unit to the given Name.
 * 
 * @param  name
 *         The new Name for this Unit.
 * @post   The Name of this new Unit is equal to
 *         the given Name.
 *       | new.getName() == name
 * @throws IllegalArgumentException
 *         The given Name is not a valid Name for any
 *         Unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) throws IllegalArgumentException {
	if (! isValidName(name))
		throw new IllegalArgumentException();
	this.name = name;
}

/**
 * Set the Position of this Unit to the given Position.
 * 
 * @param  position
 *         The new Position for this Unit.
 * @post   The Position of this new Unit is equal to
 *         the given Position.
 *       | new.getPosition() == position
 * @throws illegalArgumentException
 *         The given Position is not a valid Position for any
 *         Unit.
 *       | ! isValidPosition(getPosition())
 */	
@Raw
public void setPosition(double[] position) throws IllegalArgumentException {
	
	if (! isValidPosition(position))
		throw new IllegalArgumentException();
	
	this.position = position;	
}

/**
 * Set the weight of this Unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this Unit.
 * @post   If the given weight is a valid weight for any Unit,
 *         the weight of this new Unit is equal to the given
 *         weight else we don't change anything.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 *       
 */
@Raw
public void setWeight(int weight) {
	if (isValidWeight(weight))
		this.weight = weight;
}


/**
 * Set the Strength of this Unit to the given Strength.
 * 
 * @param  strength
 *         The new Strength for this Unit.
 * @post   If the given Strength is a valid Strength for any Unit,
 *         the Strength of this new Unit is equal to the given
 *         Strength else we don't change anything.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 */
@Raw
public void setStrength(int strength) {
	if (isValidStrength(strength))
		this.strength = strength;
}

/**
 * Set the Agility of this Unit to the given Agility.
 * 
 * @param  agility
 *         The new Agility for this Unit.
 * @post   If the given Agility is a valid Agility for any Unit,
 *         the Agility of this new Unit is equal to the given
 *         Agility else we don't change anything.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 */
@Raw
public void setAgility(int agility) {
	if (isValidAgility(agility))
		this.agility = agility;
}

/**
 * Set the toughness of this Unit to the given toughness.
 * 
 * @param  toughness
 *         The new toughness for this Unit.
 * @post   If the given toughness is a valid toughness for any Unit,
 *         the toughness of this new Unit is equal to the given
 *         toughness.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 */
@Raw
public void setToughness(int toughness) {
	if (isValidToughness(toughness))
		this.toughness = toughness;
}

/**
 * Set the hitpoints of this Unit to the given hitpoints.
 * 
 * @param  hitpoints
 *         The new hitpoints for this Unit.
 * @pre    The given hitpoints must be a valid hitpoints for any
 *         Unit.
 *       | isValidHitpoints(hitpoints)
 * @post   The hitpoints of this Unit is equal to the given
 *         hitpoints.
 *       | new.getHitpoints() == hitpoints
 */
@Raw @Model
private void setHitpoints(int hitpoints) {
	assert isValidHitpoints(hitpoints);
	this.hitpoints = hitpoints;
}

/**
 * Set the stamina of this Unit to the given stamina.
 * 
 * @param  stamina
 *         The new stamina for this Unit.
 * @pre    The given stamina must be a valid stamina for any
 *         Unit.
 *       | isValidStamina(stamina)
 * @post   The stamina of this Unit is equal to the given
 *         stamina.
 *       | new.getStamina() == stamina
 */
@Raw @Model
private void setStamina(double stamina) {
	assert this.isValidStamina(stamina);
	this.stamina = stamina;
}

/**
 * Set the orientation of this Unit to the given orientation.
 * 
 * @param  orientation
 *         The new orientation for this Unit.
 * @post   If the given orientation is a valid orientation for any Unit,
 *         the orientation of this new Unit is equal to the given
 *         orientation.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 * @post If the  given orientation is not a valid orientation for any Unit,
 *         the orientation will be orientation modulo 2*PI
 *        | if (!isValidOrientation(orientation))
 *        |  	then new.getOrientation() == orientation%2*PI
 */
@Raw
public void setOrientation(float orientation) {
	if (isValidOrientation(orientation))
		this.orientation = orientation;
	else{
		this.orientation = orientation%(2*PI);
	}
}

/**
 * Sets the speedof this unit to the given speed.
 * @param	speed
 * 			The new speed of this unit.
 * @post	The speed of this unit is equal to the given speed.
 * 			| new.getSpeed() == speed
 */

@Raw
public void setSpeed(double speed){
	this.speed = speed;
}

/*------------------------CHECKERS
/**
 * Check whether the given Name is a valid Name for
 * any Unit.
 *  
 * @param  Name
 *         The Name to check.
 * @return true if and only if the name is longer then 2 and
 * 			the first character is an upper case letter and
 * 			the name exists of only letters, numbers, spaces and quotes.
 *       | result == (name.length()>2)&&
 *       (Character.isUpperCase(name.charAt(0))&&
 *       (name.matches("[A-Za-z\"' ]+")))
*/
public static boolean isValidName(String name) {
	return (name.length()>2)&&
			(Character.isUpperCase(name.charAt(0))&&
			(name.matches(ALLOWED_NAME_PATTERN)));
}


/**
 * Check whether the given Position is a valid Position for
 * any Unit.
 *  
 * @param  Position
 *         The Position to check.
 * @return The position has to be inside the game world
 *       | result ==
 *       | (MINCOORDINATE<=position[0]&&position[0]<=MAXCOORDINATE)&&
 *       | (MINCOORDINATE<=position[1]&&position[1]<=MAXCOORDINATE)&&
 *       | (MINCOORDINATE<=position[2]&&position[2]<=MAXCOORDINATE)
 *       
*/
public static boolean isValidPosition(double[] position) {
	return (MINCOORDINATE<=position[0]&&position[0]<=MAXCOORDINATE)&&
	       (MINCOORDINATE<=position[1]&&position[1]<=MAXCOORDINATE)&&
	       (MINCOORDINATE<=position[2]&&position[2]<=MAXCOORDINATE);
}

/**
 * Check whether the given weight is a valid weight for
 * any Unit.
 *  
 * @param  weight
 *         The weight to check.
 * @return true if and only if the weight is between 0 and 200 inclusively and
 * 			the weight must be at least the minimum weight.
 *       | result == (1<=weight&&weight<=200)&&(weight>getMinWeight(strength,agility))
*/
public boolean isValidWeight(int weight) {
	return 	(1<=weight)&&(weight<=200)&&
			(weight>=this.getMinWeight()) ;
}


/**
 * Check whether the given Strength is a valid Strength for
 * any Unit.
 *  
 * @param  strength
 *         The Strength to check.
 * @return 
 *       | result == (1<=strength&&strength<=200)
*/
public static boolean isValidStrength(int strength) {
	return (1<=strength&&strength<=200);
	
}

/**
 * Check whether the given Agility is a valid Agility for
 * any Unit.
 *  
 * @param  agility
 *         The Agility to check.
 * @return  true if and only if the agility lays between 1 and 200
 *       | result == (1<=agility && agility<=200)
*/
public static boolean isValidAgility(int agility) {
	return (1<=agility&&agility<=200);
}

/**
 * Check whether the given toughness is a valid toughness for
 * any Unit.
 *  
 * @param  toughness
 *         The toughness to check.
 * @return 
 *       | result == (1<=toughness && toughness <= 200)
*/
public static boolean isValidToughness(int toughness) {
	return (1<=toughness && toughness<=200);
}

/**
 * Check whether the given hitpoints is a valid hitpoints for
 * any Unit.
 *  
 * @param  hitpoints
 *         The hitpoints to check.
 * @return true if and only if the hitpoints are between 0 and getMaxHitpoints()
 *       | result == (0<=hitpoints && hitpoints <= this.getMaxHitpoints()
*/
public boolean isValidHitpoints(int hitpoints) {
	return (0<=hitpoints && hitpoints <= this.getMaxHitpoints());
}


/**
 * Check whether the given stamina is a valid stamina for
 * any Unit.
 *  
 * @param  stamina
 *         The stamina to check.
 * @return 
 *       | result == (0<=stamina && stamina<= this.getMaxStamina())
*/
public boolean isValidStamina(double stamina) {
	return (0<=stamina && stamina<= this.getMaxStamina());
}

/**
 * Check whether the given orientation is a valid orientation for
 * any Unit.
 *  
 * @param  orientation
 *         The orientation to check.
 * @return 
 *       | result == (0<=orientation && orientation<2*PI)
*/
public static boolean isValidOrientation(float orientation) {
	return (0<=orientation && orientation<2*PI);
}


/*--------------------HELPER METHODS
//WEIGTH

/**
 * The minimum weight
 * 
 * @return the minimum weight of this unit
 * 		|result ==  (strength+agility)/2
 */
@Model
private int getMinWeight( ){
	return (this.getStrength()+ this.getAgility())/2;
}

//HITPOINTS

/**
 * The maximum amount of hitpoints a unit can have
 * 
 * @return The maximum HP a unit can have is the closest integer
 * 			 to 200 * weight/100 * toughness/100
 * 		| result == Math.ceil(0.02*this.getWeight()*this.getToughness())
 */
public int getMaxHitpoints(){
	
	return (int) Math.ceil(0.02*this.getWeight()*this.getToughness());
}

/**
 * Checks if the unit is fully recovered
 * 
 * @return true if the unit is fully recovered
 * 			|this.getHitpoints() == getMaxHitpoints(this.getWeight(), this.getToughness())&&
			(this.getStamina()==getMaxStamina(this.getWeight(), this.getToughness()))
 */
private boolean isFullyHealed() {
	return this.getHitpoints() == getMaxHitpoints()&&
			(this.getStamina()==getMaxStamina());
}


//STAMINA

/**
 * The maximum amount of stamina a unit can have
 * 
 * @return The maximum Stamina a unit can have is the closest integer
 * 			 to 2 * weight * toughness / 100
 * 		| result == Math.ceil(2 * weight * toughness / 100)
 */
public int getMaxStamina(){
	return (int) Math.ceil(0.02*this.getWeight()*this.getToughness());
}

//ORIENTATION

/**
 * returns the moving orientation of the unit
 * 
 * @param velocityVector
 *			the vector indication the velocity in each direction
 * @return the moving orientation of the unit
 * 			| result == (float) Math.atan2(velocityVector[1], velocityVector[0])
 */
@Model
private static float getMovingOrientation(double[] velocityVector){
	float orientation = (float) Math.atan2(velocityVector[1], velocityVector[0]);
	return orientation;
}
/**
 * Update the orientations of the units who are 
 * @param attacker
 * 			the attacking unit
 * @param defender
 * 			the defending unit
 * @post The orientation of the attacking unit will be updated
 * 			| attacker.getOrientation() = (float) Math.atan2(dPosition[1]-aPosition[1], dPosition[0]-aPosition[1])
 * 			| defender.getOrientation() = (float) Math.atan2(aPosition[1]-dPosition[1], aPosition[0]-dPosition[1])
 */
@Model
private static void updateOrientation(Unit attacker, Unit defender){ 
	double[] aPosition = attacker.getPosition();
	double[] dPosition = defender.getPosition();
	float aOrientation = (float) Math.atan2(dPosition[1]-aPosition[1], dPosition[0]-aPosition[0]);
	float dOrientation = (float) Math.atan2(aPosition[1]-dPosition[1], aPosition[0]-dPosition[0]);
	attacker.setOrientation(aOrientation);
	defender.setOrientation(dOrientation);
}

//SPEED


/**
* Update the speed of the unit with the information we have about that unit
* 
* @param dz
* 		the difference in z coordinates
* @post the new speed of the unit is the speed of the unit
* 		 according the conditions of this unit
*/
public void updateSpeed(int dz){
	double realSpeed;
	if(this.isMoving()){
		double baseSpeed = (double) 0.75*(this.getStrength()+this.getAgility())/(this.getWeight());
		double walkingSpeed;
		
		if (dz == 1){
			walkingSpeed = baseSpeed*0.5;
			}
		else if (dz == -1){
			walkingSpeed = baseSpeed*1.2;
			}
		else{
			walkingSpeed = baseSpeed;
		}
		realSpeed = walkingSpeed;
		
		if (this.isSprinting())
			realSpeed = walkingSpeed*2;
	}else{
		realSpeed = 0.0;
	}
	
	this.speed = realSpeed;
}

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------ADVANCE TIME-------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/

//TODO further optimize advanceTime

public void advanceTime(double dt) throws IllegalArgumentException {
	if (!(0.0<=dt&&dt<=0.2))
		throw new IllegalArgumentException();
	
    counterTillRest += dt;
    
    //set counter to the moment the unit needs to rest
    if(counterTillRest >= REST_INTERVAL && this.isAbleToRest()){
    	rest();
    	counterTillRest = 0.0;
    }
	
	//if the unit stopped moving, we have to stop sprinting and stop the speed
	if(!this.isMoving()){
		if(!this.isSprinting())
			this.stopSprinting();
		if(this.getSpeed()>0)
			this.setSpeed(0);
	}
	
	//if the unit stopped 
	
	//if the unit is in default mode, it can randomly start to sprint while moving
	if(this.hasDefaultBehavior() && !this.isSprinting() && this.isMoving() && this.isAbleToSprint()){
		double randomSprint = Math.random();
		if(randomSprint<0.01)
			this.startSprinting();
	}
	
	// continue moving after you are again able to move
	if((this.getTargetPosition()!= null) && !equals(this.getPosition(),this.getTargetPosition())&&this.isAbleToMoveFurther()){
			this.moveToTarget(getCubePosition(this.getTargetPosition()));
	}
	
	if(this.hasDefaultBehavior() && this.getCurrentActivity()==Activity.NOTHING){
	
			int randomActivity = (int) (Math.random()*3);
			
			if (randomActivity == 0) {
				// een unit kan enkel naar het centrum van een cube bewegen!
				double[] randomPosition = new double[] {Math.random()*50, Math.random()*50, Math.random()*50};
				int[] targetPosition = getCubePosition(randomPosition);
				this.moveToTarget(targetPosition);
			}
			if (randomActivity == 1) {
				work();
			}
			if (randomActivity == 2) {
				rest();
			}
	}
	
	/*
	 * --------------------------HANDEL DIFFERENT ACTIVITIES-----------------------------
	 */
	
	Activity activity =this.getCurrentActivity();
			
	if (activity == Activity.MOVING) {
		
			if(this.isSprinting()){
				if(this.getStamina()>=10*dt){
					this.setStamina((this.getStamina()-10*dt));
				}else{
					this.setStamina(0);
					this.stopSprinting();
				}
				
			}
			
			
			double[] cPosition = this.getPosition();
			double[] nPosition = this.getNextPosition();
			double[] tPosition = this.getTargetPosition();
			
			
			if(equals(cPosition,nPosition)){
				if(tPosition == null || equals(cPosition, tPosition)){
					this.setCurrentActivity(Activity.NOTHING);
					counterTillDefault = 0;
					this.stopSprinting();
					this.setSpeed(0);
				}else{
					int[] step = new int[3];
					//pathfinding algorithm
					for(int i = 0; i<3; i++){
						if (cPosition[i] == tPosition[i]){
							step[i] = 0;
						}else if(cPosition[i] < tPosition[i]){
							step[i] = 1;
						}else{
							step[i] = -1;
						}
						this.moveToAdjacent(step[0],step[1],step[2]);
						nPosition = this.getNextPosition();
				}}}
			double[] iPosition = this.getIntermediatePosition(this.getStep()[0],this.getStep()[1],this.getStep()[2], dt);
			if(inBetween(cPosition, nPosition, iPosition))
				this.setPosition(iPosition);
			else
				this.setPosition(nPosition);
	}
	
	if (activity == Activity.WORKING) {
		
			this.setProgressTime((float)(this.getProgressTime()+dt));
			if (this.getProgressTime() >= this.getWorkingTime()) {
				this.setCurrentActivity(Activity.NOTHING);
				counterTillDefault =0;
			}
	}
	if (activity == Activity.ATTACKING){
		
			this.setProgressTime((float)(this.getProgressTime()+dt));
			if (this.getProgressTime() >= this.getFightingTime()) {
				this.setCurrentActivity(Activity.NOTHING);
				counterTillDefault = 0;
			}
	}
	if (activity == Activity.RESTING) {
		
			if (this.isFullyHealed()) {
				this.setCurrentActivity(Activity.NOTHING);
				counterTillDefault = 0;
			} else {
				this.setProgressTime((float)(this.getProgressTime()+dt));
				
					if(this.getProgressTime()>this.getMinimalHitpointTime() && 
							this.getHitpoints()<getMaxHitpoints()){
						
						this.setHitpoints(this.getHitpoints()+1);
						this.setProgressTime(this.getProgressTime()-this.getMinimalStaminaTime());

					}else if (this.getProgressTime()>this.getMinimalHitpointTime() && 
							this.getStamina()<getMaxStamina()){
						
						this.setStamina((int)this.getStamina()+1);
						this.setProgressTime(this.getProgressTime()-this.getMinimalStaminaTime());
					}
					
				}
				
			}
			
	if (activity == Activity.NOTHING) {
		
			counterTillDefault = counterTillDefault+dt;
			if(counterTillDefault >= NOTHING_INTERVAL){
				counterTillDefault = 0.0;
				this.startDefaultBehavior();
				this.setProgressTime(0);
				
			}
	}
}

/*___________________________________________________________________
 * __________________________________________________________________
 * -----------------------MOVING-------------------------------------
 *___________________________________________________________________
 *___________________________________________________________________*/

/*------------------GETTERS
 
/**
 * Get the targetPosition of this unit
 */
@Basic @Raw
public double[] getTargetPosition() {
	return this.targetPosition;
}

/**
 * Get the nextPosition of this unit
 */
@Basic @Raw
public double[] getNextPosition() {
	return this.nextPosition;
}
/**
 * 
 * Get the step to be taken by the unit
 */
@Basic @Raw
public int[] getStep(){
	return this.step;
}

/*-----------------SETTERS
/**
 * 
 * Set the target position of the unit
 * 
 * @param targetPosition
 * 			the position where the unit is heading to
 * @throws IllegalArgumentException
 * 			if the position is not valid
 * 			| (!isValidPosition(targetPosition))
 * @post the units target position is targetPosition
 * 			|new.getTargetPosition() == targetPosition
 */
@Raw @Model
private void setTargetPosition(double[] targetPosition) throws IllegalArgumentException  {
	if (!isValidPosition(targetPosition))
		throw new IllegalArgumentException();
	this.targetPosition = targetPosition;
}

/**
 * 
 * Set the target position of the unit
 * 
 * @param nextPosition
 * 			the position where the unit is heading to
 
 * @post the units next position is nextPosition
 * 			|new.getNextPosition() == nextPosition
 * 
 * @throws IllegalArgumentException
 * 			if the position is not valid
 * 			| (!isValidPosition(nextPosition))
 */
@Raw @Model
private void setNextPosition(double[] nextPosition) throws IllegalArgumentException  {
	if (!isValidPosition(nextPosition))
		throw new IllegalArgumentException();
	this.nextPosition = nextPosition;
}

/**
 * 
 * Set the step to be taken by the unit
 * 
 * @param step
 * 			the position where the unit is heading to
 * 
 * @post the units step is set to step
 * 			|new.getStep() == step
 * 
 * @throws IllegalArgumentException
 * 			if the position is not valid
 * 			| !isValidStep(step)
 * 
 */
@Raw @Model
private void setStep(int[] step) throws IllegalArgumentException {
	if(!isValidStep(step[0],step[1],step[2]))
		throw new IllegalArgumentException();
	this.step = step;
}


/*
 * -------------------MOVING-------------------
 */

/**
 * Let the move to a target cube
 * 
 * @param cube
 * 			the cube where the unit will move to
 * 
 * @post the current activity of the unit will change to "moving"
 * 			|new.getCurrentActivity == "moving"
 * @post the target position will be set to the center of cube
 * 			| new.getTargetPosition == getCubeCenter(cube)
 * @throws IllegalArgumentException
 * 			will be thrown if the cube is out of bounds
 * 				|!isValidPosition(getCubeCenter(cube)
 */
public void moveToTarget(int[] cube) throws IllegalArgumentException, IllegalStateException{
	
	if(!isValidPosition(getCubeCenter(cube)))
		throw new IllegalArgumentException();
	
	if(!this.isAbleToMove())
		throw new IllegalStateException();
	
	this.setCurrentActivity(Activity.MOVING);
	this.setNextPosition(this.getPosition());
	this.setTargetPosition(getCubeCenter(cube));			
}

/**
 * 
 * Let the unit move to an adjacent position
 * 
 * @param dx
 * 		The difference in cubes to go in x direction
 * @param dy
 * 		The difference in cubes to go in y direction 
 * @param dz
 * 		The difference in cubes to go in z direction
 * 
 * @effect the acivity of the unit will be set to moving
 * 			| this.setCurrentActivity("moving")
 * 
 * @effect The speed will be updated according to the movement in z direction
 * 			|this.updateSpeed(dz)
 * 
 * @post The unit will get to the new position,
 * 		this is the center of a neighboring cube
 * 			| 	double[] newPosition = {
 *			| cubeCenter[0]+dx,
 *			| cubeCenter[1]+dy,
 *			| cubeCenter[2]+dz};
 *			| new.position == setTargetPosition(newPosition)
 * @post the unit will update his targetposition when only advanceTime is pressed
 * 			|if(this.getTargetPosition() != null && equals(this.getPosition(),this.getTargetPosition()))
			|	new.getTargetPosition = nextPosition
 *
 * @throws IllegalArgumentException
 * 		If at least one of the parameters is not -1, 0, or 1
 * 		| (Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1)
 * @throws IllegalStateException
 * 		If the calculated destination is out of bounds
 * 		| !isValidPosition(newPosition)
 */
public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException, IllegalStateException{
		if (!isValidStep(dx, dy, dz)){
			throw new IllegalArgumentException();
		}
		if(!this.isAbleToMove())
			throw new IllegalStateException();

		double[] cubeCenter = getCubeCenter(getCubePosition(this.getPosition()));
		double[] nextPosition = new double[] {cubeCenter[0]+dx,cubeCenter[1]+dy,cubeCenter[2]+dz};
		
		if (!isValidPosition(nextPosition))
			throw new IllegalArgumentException();

		//when hitting only moveToAdjacent
		if(this.getTargetPosition() != null && equals(this.getPosition(),this.getTargetPosition()))
			this.setTargetPosition(nextPosition);
		
		this.setStep(new int[]{dx,dy,dz});
		this.setCurrentActivity(Activity.MOVING);
		this.updateSpeed(dz);
		this.setOrientation(getMovingOrientation(getVelocityVector(dx, dy, dz, this.getSpeed())));
		this.setNextPosition(nextPosition);
			
	}

/*
 * ----------------------HELPER METHODS--------------------------
 */

/**
 * 
 * get the intermediate position of the unit
 * 
 * @param dx
 * 		difference in x direction
 * @param dy
 * 		difference in y direction
 * @param dz
 * 		difference in z direction
 * @param dt
 * 		time
 * @return the intermediate position of unit who is walking
 * 			 from his original position to his new position
 * 			| result == {this.position[0]+this.getVelocityVector[0]*dt,
 * 						this.position[1]+this.getVelocityVector[1]*dt,
 * 						this.position[2]+this.getVelocityVector[2]*dt}
 * 						
 */
public double[] getIntermediatePosition(int dx, int dy, int dz, double dt){
	double[] position = this.getPosition();
	double[] velocityVector = getVelocityVector(dx, dy, dz, this.getSpeed());

	double[] newPosition = new double[] {
			position[0]+velocityVector[0]*dt,
			position[1]+velocityVector[1]*dt,
			position[2]+velocityVector[2]*dt	
	};
	return newPosition;
}

/**
 * Get the velocity in the different directions of the unit
 * 
 * @param dx
 * 			difference in x direction
 * @param dy
 * 			difference in y direction
 * @param dz
 * 			difference in z direction
 * @param speed
 * 		The speed of the given unit
 * @return the velocity in all directions of the unit
 * 			|result == {speed*dx/distance,speed*dy/distance,speed*dz/distance}
 * 				
 */
@Model
private static double[] getVelocityVector(int dx, int dy, int dz, double speed){
	double distance = Math.sqrt(dx*dx+dy*dy+dz*dz);
	double[] velocity = new double[] {
			speed*dx/distance,
			speed*dy/distance,
			speed*dz/distance
	};
	return velocity;
};

/**
 * Checks if the step is a valid step
 * 
 * @param dx
 * 			difference in x direction
 * @param dy
 * 			difference in y direction
 * @param dz
 * 			difference in z direction
 * @return If the step goes to a neighbouring cube
 * 		|result == Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1
 */
@Model
private static boolean isValidStep(int dx, int dy, int dz) {
	return !(Math.abs(dx)>1||Math.abs(dy)>1||Math.abs(dz)>1);
}


/**_____________________________________________________________
 * _____________________________________________________________
 * -------------------------ACTIVITIES--------------------------
 * _____________________________________________________________
 *_____________________________________________________________
 */

/*
 * -----------------GETTERS-----------------
 */

/**
 * Returns the activity this unit is doing.
 */
@Basic @Model @Raw
private Activity getCurrentActivity(){
	return this.activity;
}

/**
 * Returns the time this unit will continue its current activity.
 */
@Basic @Model @Raw
private float getProgressTime(){
	return this.progressTime;
}

/*
 * ----------------SETTERS----------------
 */

/**
 * Sets the current activity to the given activity.
 * 
 * @param	activity
 * 			The activity the unit will be performing.
 * @post	The activity of the unit will be the given activity.
 * 			| new.getCurrentActivity == activity
 * @throws	IllegalArgumentException
 * 			The given activity is not a valid activity.
 */
@Model
private void setCurrentActivity(Activity activity) throws IllegalArgumentException{
	this.activity = activity;
}

/**
 * Sets the current activity time to the given time.
 * 
 * @param	time
 * 			The time the unit will be performing its activity.
 * @post	The activity time of the unit will be the given time.
 * 			| new.getActivityTime == time
 */
@Model
private void setProgressTime(float time){
	this.progressTime = time;
}

/*
 * ----------------------ACTIVITY CHECKERS-----------------------
 */

/**
 * Tells whether the unit is currently moving.
 * @return
 * 		true if the unit is currently moving.
 * 		| result == (this.getCurrentActivity == "moving")
 */
public boolean isMoving(){
	return (this.getCurrentActivity()==Activity.MOVING);
}
/**
 * Tells whether the unit is currently working.
 * @return
 * 		true if the unit is currently working.
 * 		| result == (this.getCurrentActivity == "working")
 */
public  boolean isWorking(){
	return (this.getCurrentActivity()==Activity.WORKING);	
}
/**
 * Tells whether the unit is currently resting.
 * @return
 * 		true if the unit is currently resting.
 * 		| result == (this.getCurrentActivity == "resting")
 */
public boolean isResting(){
	return (this.getCurrentActivity()==Activity.RESTING);
}
/**
 * Tells whether the unit is currently attacking.
 * @return
 * 		true if the unit is currently attacking.
 * 		| result == (this.getCurrentActivity == "attacking")
 */
public boolean isAttacking(){
	return (this.getCurrentActivity()==Activity.ATTACKING);
}
/**
 * Tells whether the unit is currently in default behavior.
 * @return
 * 		true if the unit is currently in default behavior.
 * 		| result == (this.hasDefaultBehaviorEnabled)
 */
public boolean hasDefaultBehavior() {
	return this.hasDefaultBehaviorEnabled;
}

/**
* Returns true if the unit is sprinting
* @return
*  		true if the unit is currently sprinting.
* 		| result == this.isSprinting
*/
@Basic @Raw
public boolean isSprinting() {
	return this.isSprinting;
}

/**
* Returns true if the unit is doing nothing.
* @return
*  		true if the unit is currently doing nothing.
* 		| result == (this.getCurrentActivity() == Activity.NOTHING)
*/
public boolean isDoingNothing(){
	return (this.getCurrentActivity()==Activity.NOTHING);
}

/*
 * ---------------ACTIVITY INITIALISERS----------------------
 */

//WORKING

/**
 * 
 * The unit starts working
 * 
 * @post the progress time will be set to 0
 * 		| new.getProgressTime = 0
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.WORKING
 * 
 * @throws IllegalStateException
*         the given unit is not able to work or is in default behavior.
*       | ! isAbleToWork()&&!hasDefaultBehavior())
 */
public void work() throws IllegalStateException{
	if (!this.isAbleToWork())
			throw new IllegalStateException();
	
	this.setCurrentActivity(Activity.WORKING);
	this.setProgressTime(0);
}

//RESTING

/** 
* The unit starts resting
* 
* 
* @post the activity of the unit is switched off to nothing
* 		 | new.getCurrentAcivity() == Activity.RESTING
* 
* @post the progress time will be set to 0
 * 		| new.getProgressTime = 0
* 
* @throws IllegalStateException
* 			if the  unit is not able to rest
* 			| !this.isAbleToRest()&&!this.hasDefaultBehavior()
*/
public void rest() throws IllegalStateException{
	if(!this.isAbleToRest())
		throw new IllegalStateException();
	
	this.setCurrentActivity(Activity.RESTING);	
	this.setProgressTime(0);
}
//FIGHTING

/**
 * Let the unit attack another unit
 * 
 * @post the activity time will be set to the maximal resting time
 * 		| new.getProgressTime = 0
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.ATTACKING
 * 
 * @throws IllegalStateException
 * 			if the  unit is not able to attack or is in default behavior
 * 			| !this.isAbleToAttack()
 * 
 * @effect the orientation of both defender and attacker will be updated
 * 			| updateOrientation(this, defender)
 */
public void attack(Unit defender) throws IllegalStateException{
	if(!this.isAbleToAttack(defender))
		throw new IllegalStateException();
	
	defender.defend(this);
	
	updateOrientation(this, defender);
	this.setCurrentActivity(Activity.ATTACKING);
	this.setProgressTime(0);
}



/**
 * Makes the unit defend against a given attacker.
 * 
 * The defender has a 0.2*defender.agility/attacker.agility chance to dodge an attack.
 * If dodging is successful, the defending unit will move to a random adjacent square.
 * 
 * If dodging is unsuccessful, the defending unit will try to block the attack.
 * The chance of blocking is 0.25*(defender.strength*defender.agility)/(attacker.strength+attacker.agility)
 * If blocking is successful, the defending unit takes no damage.
 * 
 * If both dodging and blocking are unsuccessful, the defending unit takes damage.
 * The damage the unit takes equals attacker.strength/10
 * 
 * instantious response, no game time needed
 * 
 * @post or the unit changed his position
 * 			| new.getPosition() == getCubeCenter(getCubePosition(nextPosition);
 * 
 * @post or the unit blocked the attack and nothing happens
 * 			| /
 * 
 * @post or the defender loses some hitpoints
 * 			| new.getHitpoints() == this.getHitpoints() - attacker.getStrength()/10
 */

public void defend(Unit attacker){
	
	this.setCurrentActivity(Activity.NOTHING);
	
	//first Dodging
	double probDodging = 0.2*this.getAgility()/attacker.getAgility();
	
	if(Math.random()<probDodging){
		int step[] = new int[] {0,0,0};
		double[] currentPosition = this.getPosition();
		double[] nextPosition = currentPosition;
		while ((step[0]==0 && step[1]==0)||(!isValidPosition(nextPosition))){
			step[0] = -1 + (int)(Math.random()*3);
			step[1] = -1 + (int)(Math.random()*3);
			nextPosition[0] = currentPosition[0] + step[0];
			nextPosition[1] = currentPosition[1] + step[1];
		};
		//may move instantaneous
		this.setPosition(getCubeCenter(getCubePosition(nextPosition)));
		return;
	};
	
	//then blocking
	double probBlocking = 0.25*(this.getStrength()+this.getAgility())/
			(attacker.getStrength()+attacker.getAgility());
	
	if(Math.random()<probBlocking){
		return;
	};
	
	//then taking damage
	int damage = attacker.getStrength()/10;
	int newHitpoints = this.getHitpoints()- damage;
	if(newHitpoints<=0)
		newHitpoints =0;
	this.setHitpoints(newHitpoints);
	}

//DEFAULTBEHAVIOUR

/**
 * Start the default behavior of this unit
 * 
 * @post the activity of the unit is switched to default behavior
 * 		 | new.hasDefaultBehavior() == true
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.NOTHING
 * 
 */
public void startDefaultBehavior() {
		this.setProgressTime(0);
		this.setCurrentActivity(Activity.NOTHING);
		this.hasDefaultBehaviorEnabled = true; 
}

//SPRINTING

/**
* The Unit starts to sprint
* 
* @post  The unit will go in sprinting mode
*       | new.isSprinting() == true
*       
* @throws IllegalStateException
*         the given unit is not able to sprint
*       | ! this.isAbleToSprint()
*/
public void startSprinting() throws IllegalStateException{
	if (!this.isAbleToSprint())
		throw new IllegalStateException();
	this.isSprinting = true;
}



/*
 * --------------ACTIVITY TERMINATORS--------------------
 */

/**
 * Stop default behavior of Unit
 * 
 * @post the activity of the unit is switched off to nothing
 * 		 | new.getCurrentAcivity() == Activity.NOTHING
 * 
 * @post the default behavior is switched off
 * 		| new.hasDefaultBehaviour() == false
 * 
 */
public void stopDefaultBehaviour() {
	this.setCurrentActivity(Activity.NOTHING);
	this.hasDefaultBehaviorEnabled = false;
}


/**
* The unit will stop sprinting
* 
* @post  The unit will stop sprinting
*       | new.isSprinting() == false
*/
public void stopSprinting() {
	this.isSprinting = false;
}

/*
 * ---------------ACTIVITY TIME-------------------------
 */


/**
 * Gives the minimal time to recover one hitpoint.
 * The time it takes for a unit to restore one hitpoint
 * 
 * @return  the minimal 
 * 			|result == 40/getToughness()
 */ 
@Model
private float getMinimalHitpointTime(){
	return (float)40/this.getToughness();
}

/**
 * Gives the minimal time to recover one stamina.
 * The time it takes for a unit to restore one stamina
 * 
 * @return  the minimal time it takes to recover one stamina
 * 			|result == 20/getToughness()
 */ 
@Model
private float getMinimalStaminaTime(){
	return (float)20/this.getToughness();
}

/**
 * Gives the time it takes for a unit to carry out some work.
 * 
 * @return The time it takes for this unit to carry out some work, 
 * 			more specific 500/this.getStrength()
 * 		| result == 500/this.getStrength()
 */
@Model
private float getWorkingTime(){
	return (float)500/this.getStrength();
}

/**
 * Gives the time it takes for a unit to attack
 * 
 * @return The time it takes for a unit to attack
 * 		| result == 1
 */
@Immutable
private final float getFightingTime(){
	return (float)1;
}

/*
 * ---------------------------ABILITY CHECKERS-------------------------------
 */

/**
 * Checks if this unit can move.
 * A unit can move if it is not attacking and if it is not working.
 * @return	true if unit is not attacking and not working.
 * 			| result == this.getCurrentActivity()!=Activity.ATTACKING
 */
public boolean isAbleToMove(){
	return this.getCurrentActivity()!=Activity.ATTACKING ;
}

/**
 * Checks if this unit can move further.
 * A unit can move further if it has no activity
 * @return	true if unit is not attacking and not working.
 * 			| result == this.getCurrentActivity()!=Activity.NOTHING
 */
public boolean isAbleToMoveFurther(){
	return this.getCurrentActivity()==Activity.NOTHING;
}

/**
 * Checks if this unit can rest.
 * A unit can rest if it is not attacking and it needs to recover
 * @return	true if unit is not attacking.
 * 			| result == this.getCurrentActivity()!=Activity.ATTACKING && !this.isFullyHealed()
 */
public boolean isAbleToRest(){
	return this.getCurrentActivity()!=Activity.ATTACKING;
}

/**
 * Checks if this unit is currently able to sprint.
 * A unit can sprint if its stamina is above 0 and if it is already moving.
 * @return	true if the unit is moving and its stamina is higher than 0.
 * 			| result == this.isMoving() && getStamina()>0
 */
public boolean isAbleToSprint(){
	return this.isMoving() && getStamina()>0;
}

/**
 * Checks if this unit is currently able to work.
 * A unit can work if it is not attacking.
 * @return	true if this unit is not attacking.
 * 			| result == this.getCurrentActivity() != Activity.ATTACKING
 */
public boolean isAbleToWork(){
	return this.getCurrentActivity() != Activity.ATTACKING;
}
/**
 * Checks if this unit is currently able to attack.
 * A unit can work if it is not attacking.
 * @return	true if this unit is not attacking.
 * 			| result == (Math.abs(dx)<=1&&Math.abs(dy)<=1&&Math.abs(dz)==0);
 */
@Immutable
public boolean isAbleToAttack(Unit defender){
	int[] aPosition = getCubePosition(this.getPosition());
	int[] dPosition = getCubePosition(defender.getPosition());
	int dx = aPosition[0] - dPosition[0];
	int dy = aPosition[1] - dPosition[1];
	int dz = aPosition[2] - dPosition[2];

	return (Math.abs(dx)<=1&&Math.abs(dy)<=1&&Math.abs(dz)==0);
}

/*_____________________________________________________________
 * ____________________________________________________________
 *-------------------------HELPER METHODS----------------------
 * ____________________________________________________________
 *_____________________________________________________________
 */
//TODO Testen
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



}