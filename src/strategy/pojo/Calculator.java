package strategy.pojo;

import strategy.service.Operation;

public class Calculator {

	private Operation operation;

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public int doOperation(int num1, int num2) {
		return this.operation.doOperation(num1, num2);
	}
}
