package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class MultiplyOperator extends Operator {
    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int total = operandOne.getValue() * operandTwo.getValue();
      //  Operand operand = new Operand(total);
        return new Operand (total);
    }
}
