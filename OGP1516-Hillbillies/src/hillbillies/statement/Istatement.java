package hillbillies.statement;

import hillbillies.model.TaskHandler;

public interface Istatement {

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
	Statement getPrevious();

	/**
	 * @return the next
	 */
	Statement getNext();

}