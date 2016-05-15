package hillbillies.expression.booleanExpression;

import hillbillies.expression.Expression;
import hillbillies.model.TaskHandler;

public class True extends Expression<Boolean> {

	public True() {
		super();
	}

	@Override
	public Boolean evaluate(TaskHandler taskHandler) {
		return true;
	}

}
