package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class AddOperator extends Operator {
    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int total = operandOne.getValue()+ operandTwo.getValue();
       // Operand operand = new Operand(total);
        return new Operand(total);
    }
}
