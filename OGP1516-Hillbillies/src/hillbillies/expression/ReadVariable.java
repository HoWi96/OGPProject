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
			Object object =  taskHandler.getValueOfVariable(getVariableName()).evaluate(taskHandler);
			if(object == null){
				taskHandler.interruptTask();
				throw new Error(getVariableName()+" is not present");
			}else
				return object;
				

	}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

}
