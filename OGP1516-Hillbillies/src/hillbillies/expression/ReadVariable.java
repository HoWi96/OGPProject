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
		System.out.println(getVariableName());
		try{
			Expression<?> e = taskHandler.getValueOfVariable(getVariableName());
			
			if (e == null)
				throw new IllegalStateException();
			else
				return e.evaluate(taskHandler);
		
		} catch(IllegalStateException e){
			taskHandler.interruptTask();
			throw new Error("variable does not exist");
		}
	}

	/**
	 * @return the variableName
	 */
	public String getVariableName() {
		return variableName;
	}

}
