package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int total = (int) Math.pow(operandOne.getValue(), operandTwo.getValue());
        //Operand operand = new Operand(total);
        return new Operand(total);
    }
}
