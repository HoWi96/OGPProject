package hillbillies.statement;

import hillbillies.model.TaskHandler;

public interface IStatement {

	/**
	 * A method to execute the statement
	 * 
	 * @param taskHandler
	 * 		the handler of the task
	 */
	void execute(TaskHandler taskHandler);

	/**
	 * @return the previous
	 */
	IStatement getPrevious();

	/**
	 * @return the next
	 */
	IStatement getNext();

}