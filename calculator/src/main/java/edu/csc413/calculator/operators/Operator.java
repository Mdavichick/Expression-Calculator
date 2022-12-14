package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;
import java.util.Map;

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

    // no one outside of operator knows we have hashmap (encapsulation)
    // private to ensure encapsulation, static to ensure we only have 1
    private static Map<String, Operator> operators;

    // how we set our HashMap
    static {
        operators = new HashMap<>();

        // do this for all operators
        operators.put("+",new AddOperator());
        operators.put("/", new DivideOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("^", new PowerOperator());
        operators.put("-", new SubtractOperator());
        operators.put("(", new ParenthesisOperator());
        operators.put(")", new closedParenthesisOperator());
    }

    /**
     * retrieve the priority of an Operator
     * @return priority of an Operator as an int
     */
    public abstract int priority();

    /**
     * Abstract method to execute an operator given two operands.
     * @param operandOne first operand of operator
     * @param operandTwo second operand of operator
     * @return an operand of the result of the operation.
     */
    public abstract Operand execute(Operand operandOne, Operand operandTwo);

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */

    // return correct object given a string token
    // leave it up to us to determine which function to retrieve objects from hashmap
    public static Operator getOperator(String token) {
        //map.get(key).getClass() // get type from map using key
       // operators.get(token).getClass(); // get type from map using key token
        return operators.get(token);    // or return operator of key token
       // return null;

    }

    
     /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {

        if (operators.containsKey(token))
        {
            return true;
        }

        return false;
    }


}
