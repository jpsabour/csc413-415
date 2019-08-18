package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator{
    public int priority() {
        return 3;
    }
    public Operand execute(Operand op1, Operand op2 ) {

        int value1 = op1.getValue();
        int value2 = op2.getValue();
        int result = 1;
        for(int i = 0; i < value2; i++){
            result *= value1;
        }
        return new Operand( result);
    }
}
