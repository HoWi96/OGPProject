package hillbillies.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class about the schedulers of the game
 * 
 * @author Holger Willems | 2e bach. ing. OOP
 * @date 16/05/2016
 * @Version 3.0
 * 
 */

/**
 * ASSOCIATIONS
 * @Invar The tasks of this scheduler must be 
 * 		  proper tasks for this scheduler.
 * 		| hasProperTasks()
 */
public class Scheduler{

	/*_____________________________________________________________
	 * ____________________________________________________________
	 *---------------------CONSTRUCTOR-----------------------------
	 * ____________________________________________________________
	 *_____________________________________________________________
	 */
	
	/**
	 * Creates a new scheduler without any tasks
	 * 
	 * @post A new treeSet of tasks is created, 
	 * 		sorted according to priority
	 * 		| tasks = new TreeSet<>(
	 * 					(Task t1, Task t2) -> t2.getPriority()-t1.getPriority())
	 * @post the scheduler is not yet terminated
	 * 		|	this.isTerminated() == false;
	 */
	public Scheduler(){
		tasks = new TreeSet<>( new Comparator<Task>() {

			@Override
			public int compare(Task t1, Task t2) {
				// reverse order
				return t2.getPriority()-t1.getPriority();
			}
		}
		);
		
		this.isTerminated = false;
	}
	
	/**
	 * Terminates the scheduler
	 * 
	 * @effect All the tasks this scheduler has, will be removed
	 * 		| removeAsTasks(getAllTasks());
	 * @post the scheduler will be terminated
	 * 		|this.isTerminated() == true
	 */
	public void terminate(){
		removeAsTasks(getAllTasks());
		this.isTerminated = true;
	}
	/**
	 * Returns whether the scheduler is terminated
	 */
	@Basic @Raw
	public boolean isTerminated(){
		return isTerminated;
	}
	
	/**
	 * Boolean indicating whether the scheduler is terminated
	 */
	private boolean isTerminated;
	
	/*_____________________________________________________________
	 * ____________________________________________________________
	 *-------------------------TASKS-------------------------------
	 *---------------------CONTROLLING CLASS-----------------------
	 * ____________________________________________________________
	 *_____________________________________________________________
	 */
	
	/**
     * Variable referencing a sorted set collecting all
     * the tasks of this scheduler.
     *
     * @Invar The referenced set is effective.
     * 			| tasks != null
     * @Invar Each task registered in the referenced list is
     * 			effective and not yet terminated.
     * 			| for each task in tasks:
     * 			  	( (task != null) &&
     * 			 	(! task.isTerminated()) )
     */
    private final SortedSet<Task> tasks;
    
    
    /**
     * Gives back all the tasks in no particular order
     * 
     * @return
     * 		All the tasks scheduled by the scheduler
     * 		| result == tasks
     */
    @Basic @Raw
    public Set<Task> getAllTasks(){
    	return new HashSet<>(this.tasks);
    }
    
    /**
     * Check whether this scheduler has the given task as one of its
     * tasks.
     *
     * @param task
     * 			The task to check.
     * @return True when the given task is part of this scheduler
     *          | result == tasks.contains(task)
     */
    @Raw
    public boolean hasAsTask(Task task) {
    	return tasks.contains(task);
    }
    
    /**
     * Check whether this scheduler has all the tasks in the given
     * collection as one of its tasks.
     * 
     * @param tasks 
     * 			The collection of tasks to check
     * @return True if all tasks are part of this scheduler.
     *         | result == tasks.containsAll(tasks);
     */
    @Raw
    public boolean hasAsTasks(Collection<Task> tasks){
        return this.tasks.containsAll(tasks);
    }
    
    /**
     * Check whether this scheduler can have the given task
     * as one of its tasks.
     *
     * @param task
     * 		The task to check.
     * @return True if and only if the given task is effective
     * 				and that task is a valid task for a scheduler.
     * 			| result == (task != null) && (task.canHaveAsScheduler(this))
     */
    @Raw
    public boolean canHaveAsTask(Task task) {
    	return (task != null) && (task.canHaveAsScheduler(this));
    }
    
    /**
     * Checks whether this scheduler has proper tasks attached to it.
     *
     * @return True if and only if this scheduler can have each of the
     * 		tasks attached to it as one of its tasks,
     * 		and if each of these tasks references this scheduler as
     * 		the scheduler to which they are attached.
     * 		| for each task in tasks:	
     * 		| canHaveAsTask(task) &&
     * 		| (task.getScheduler() == this)
     */
    public boolean hasProperTasks() {
    	for (Task task: tasks) {
    		if (!canHaveAsTask(task))
    		    return false;
    		if (!task.hasAsScheduler(this))
    		    return false;
    	}
    	return true;
    }
    
    /**
     * Return the number of tasks associated with this scheduler.
     *
     * @return The total number of tasks collected in this scheduler.
     * 		| result == tasks.size();
     */
    public int getNbTasks() {
    	return tasks.size();
    }
    
    
    /**
     * Add a collection of tasks
     * 
     * @param tasks
     * 		the tasks to add
     * @effect set up a bidirectional association for each task
     * 		| for each task in tasks
     * 		| 	addAsTask(task)
     * 		
     */
    public void addAsTasks(Collection<Task> tasks) throws IllegalArgumentException{
    	for(Task task: tasks)
    		addAsTask(task);
    }
    
    /**
     * Remove a collection of tasks
     * 
     * @param tasks
     * 		the tasks to remove
     * @effect break down a bidirectional association for each task
     * 		| for each task in tasks
     * 		| 	removeAsTask(task)
     * 		
     */
    public void removeAsTasks(Collection<Task> tasks) throws IllegalArgumentException{
    	for(Task task: tasks)
    		removeAsTask(task);
    }
    
    /**
     * Set up a bidirectional association between
     *  this scheduler and the given task
     * 
     * @param task
     * 			The task to add
     * 
     * @effect the scheduler has this task as task
     * 		|this.tasks.add(task);
     * @effect the task has this scheduler as scheduler
     * 		|task.addAsScheduler(this);
     * 		
     * 
     * @throws IllegalArgumentException
     * 		| !canHaveAsTask(task)
     * @throws IllegalArgumentException
     * 		| hasAsTask(task)
     */
    public void addAsTask(Task task) throws IllegalArgumentException{
    	
       	if(!canHaveAsTask(task))
    		throw new IllegalArgumentException("invalid task");
    	if(hasAsTask(task))
    		throw new IllegalArgumentException("Task already scheduled");
    	
    	this.tasks.add(task);
    	task.addAsScheduler(this);
    	
    }
    
    /**
     * Breaks down the bidirectional association between
     *  this scheduler and the given task
     *  
     * @param task
     * 		the task to remove
     * 
     * @effect The scheduler is removed from the given task
     * 		|task.removeAsScheduler(this);
     * @effect The task is removed from this scheduler
     * 		|this.tasks.remove(task);
     * 
     * @throws IllegalArgumentException
     * 		| !hasAsTask(task)
     * 
     */
    public void removeAsTask(Task task) throws IllegalArgumentException{
    	
    	if(!hasAsTask(task))
    		throw new IllegalStateException("Task not scheduled");
    	
    	task.removeAsScheduler(this);
    	this.tasks.remove(task);	
    }
    
    /**
     * Replace task by another task
     * 
     * @param original
     * 		the original task
     * @param replacement
     * 		the task to replace with
     * 
     * @effect the original task will be removed
     * 		|removeAsTask(original);
     * @effect The execution of the original task will stop
     * 		| original.getUnit().getTaskHandler().interruptTask();
     * @effect the replacement task will be added
     * 		|removeAsTask(original);
     * 
     */
    public void replaceTask(Task original, Task replacement) throws IllegalArgumentException{
    	
    	removeAsTask(original);
    	original.getUnit().getTaskHandler().interruptTask();
    	removeAsTask(original);
    }
    
    /**
     * Returns an iterator of all the tasks
     * 
     * @return an iterator of all the tasks of this scheduler
     *  	ordered with descending priority
     *  	| tasks.iterator();
     */
    public Iterator<Task> getAllTasksIterator(){
    	return tasks.iterator();
    }
    
    /**
     * Returns the highest priority task that has no unit
     * 
     * @return the highest priority task without unit
     * 		| for each task in tasks
     * 		| 	if (!task.hasUnit)
     * 		|  	then return task
     * 		
     */
    public Task getHighestPriorityAssignableTask(){
    	for(Task task : tasks)
    		if (!task.hasUnit())
    			return task;
    	return null;
    }
    
    /**
     * Get all the tasks that satisfy some predicate
     * 
     * @param condition
     * 		The predicate to filter this tasks upon
     * @return
     * 		the tasks satisfying this condition
     * 		| tasks.filter(condition)
     */
    public Collection<Task> getAllTasksSatisfying(Predicate<Task> condition){
    	return  tasks
    			.stream()
    			.filter(condition)
    			.collect(Collectors.toSet());
    };
    
    /**
     * Connect the given task to the given unit
     * 
     * @param task
     * 		the task to connect
     * @param unit
     * 		the unit to connect with
     * @effect a bidirectional association will be set
     * 		| task.addUnit(unit);
     */
    public void markTaskByUnit(Task task, Unit unit) throws IllegalArgumentException {
    	task.addUnit(unit);
    };
    
    /**
     * Disconnect the given task to the given unit
     * 
     * @param task
     * 		the task to disconnect
     * @param unit
     * 		the unit to disconnect with
     * @effect a bidirectional association will be broken down
     * 		| task.removeUnit(unit);
     */
    public void resetMarkTaskByUnit(Task task, Unit unit) throws IllegalArgumentException {
    	task.removeUnit(unit);
    };
}
