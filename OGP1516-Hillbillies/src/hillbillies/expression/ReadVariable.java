package hillbillies.expression;

import hillbillies.model.TaskHandler;

public class ReadVariable extends Expression<Object> {

	private final String variableName;

	public ReadVariable(String variableName) {
		super();
		this.variableName = variableName;
	}

	@Override
	public Object evaluate(TaskHandler taskHandler) {
		try{
			return taskHandler.getValueOfVariable(getVariableName()).evaluate(taskHandler);
		}catch(Exception e){
			taskHandler.interruptTask();
			throw new Error(getVariableName()+" is not present");
			}
		
		}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

}
