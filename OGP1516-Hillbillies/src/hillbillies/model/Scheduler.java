package hillbillies.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import be.kuleuven.cs.som.annotate.*;

/** 
 * @Invar  Each Scheduler can have its Faction as Faction.
 *       | canHaveAsFaction(this.getFaction())
 */

public class Scheduler{

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
    private final SortedSet<Task> tasks = new TreeSet<>(
    		(Task t1, Task t2) -> t2.getPriority()-t1.getPriority()
    );
    
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

    public void addAsTask(Task task)
    		throws IllegalArgumentException, IllegalStateException{
    	
       	if(!canHaveAsTask(task))
    		throw new IllegalArgumentException("invalid task");
    	if(hasAsTask(task))
    		throw new IllegalStateException("Task already scheduled");
    	
    	this.tasks.add(task);
    	task.addAsScheduler(this);
    	
    }
    
    public void removeAsTask(Task task) 
    		throws IllegalArgumentException{
    	
       	if(!canHaveAsTask(task))
    		throw new IllegalArgumentException("invalid task");
    	if(!hasAsTask(task))
    		throw new IllegalStateException("Task not scheduled");
    	
    	task.removeAsScheduler(this);
    	this.tasks.remove(task);	
    }
    
    public Iterator<Task> getAllTasksIterator(){
    	return tasks.iterator();
    }
    
    public Task getHighestPriorityAssignableTask(){
    	for(Task task : tasks)
    		if (task.getUnit() == null)
    			return task;
    	return null;
    }
    
    public Collection<Task> getAllTasksSatisfying(Predicate<Task> condition){
    	return  tasks
    			.stream()
    			.filter(condition)
    			.collect(Collectors.toSet());
    };
}
