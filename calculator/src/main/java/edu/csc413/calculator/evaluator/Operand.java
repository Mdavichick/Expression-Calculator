package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    /**
     * construct operand from string token.
     */

    // Operand has 1 variable that stores integer values
    private int value;

    // simple for loop to convert ASCII characters to numbers
    // ASCII Char 48=0 -> 57 = 9;
    public Operand(String token) {
//    int sum = 0;
//    for (int i =0; i < token.length(); i++) // for length of token
//    {
//        if (token.charAt(i) >= 48 && token.charAt(i) <= 57) // if ASCII value of token char is valid
//        {
//            sum += token.charAt(i)-48; // convert ASCII to Dec and add to sum
//        }
//    }
//        this.value = sum;

        // was having issues with the above code and 2 digit integers.
        // parseInt taken from Start Code video implemented as fix
        this.value=Integer.parseInt(token);
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        this.value = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        try{
            Integer.parseInt(token);                // parseInt takes a string and converts char to int
                                                    // based on ASCII value
        }catch(Exception ex){
            return false;
        }
        return true;
    }
}
