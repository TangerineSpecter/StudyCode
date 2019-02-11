package strategy.service.impl;

import strategy.service.Operation;

public class OperationAdd implements Operation {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 + num2;
	}

}
