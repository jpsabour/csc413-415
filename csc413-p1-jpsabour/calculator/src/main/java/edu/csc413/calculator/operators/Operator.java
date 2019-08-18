package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );
    private static HashMap<String, Operator> ops;   //has to be static access

    static {
        ops = new HashMap<>();
        ops.put("+", new AddOperator());
        ops.put("/", new DivideOperator());
        ops.put("(", new LeftParenthesis());
        ops.put("*", new MultiplyOperator());
        ops.put("^", new PowerOperator());
        ops.put(")", new RightParenthesis());
        ops.put("-", new SubtractOperator());
    }

    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 ); //declared as abstract so no body


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check( String token ) {
        //return false;
        return ops.containsKey(token);
    }


    public static Operator getOperator(String token){
        //return null;
        return ops.get(token);  //should return null if nothing linked to the key
    }
}
