package edu.csc413.calculator.evaluator;

//test

import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.*;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private Stack<Operand> operandStack; // hold operand objects
  private Stack<Operator> operatorStack; // hold operator objects
  private StringTokenizer expressionTokenizer; // tokenize a string
  private final String delimiters = " +/*-^()"; // this list is incomplete, find remaining "hint hint parenthesis"
  // we will include delimiters as tokens, will split based on delimiters


  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  // This execute will be identical to the one embedded within code minus the priority bounds
  // execute implemented to handle operations within parenthesis
  public void execute()
  {
    while (!operatorStack.empty()&&operatorStack.peek().priority()>0)
    {
      Operator operatorFromStack = operatorStack.pop();
      Operand operandTwo = operandStack.pop();
      Operand operandOne = operandStack.pop();
      operandStack.push(operatorFromStack.execute(operandOne, operandTwo));
    }
    operatorStack.pop();              // since bounds operate around priority > 0;
  }                                   // we pop 1 time to remove parenthesis


  public int evaluateExpression(String expression ) throws InvalidTokenException {
    String expressionToken;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.expressionTokenizer = new StringTokenizer( expression, this.delimiters, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators


    // Interweave parenthesis code into this while loop
    //as parsing tokens, see open one simple as pushing to operator stack, closed don't need to push
    // evaluate operators until you see an open one and pop it off the stack and continue as usual
    while ( this.expressionTokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( expressionToken = this.expressionTokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( expressionToken )) {                            // Check if Operand -> Push
          operandStack.push( new Operand( expressionToken ));
        } else {
          if ( ! Operator.check( expressionToken )) {                       // Check if Operator -> if not -> Error
            throw new InvalidTokenException(expressionToken);
          }

          // Below for Operator Algorithm

          // Add in code for parenthesis
          // Open "(" : push to operator stack - mentions pairing of parenthesis ( )
          if (expressionToken.equals(")"))                                  // If our token contains closed parenthesis
          {
            execute();                                                      // Call execute() to handle operations inside
            continue;
          }
          if ((expressionToken.equals("(")))                                // If our token contains open parenthesis
          {
            operatorStack.push(new ParenthesisOperator());                  // Push into stack
            continue;
          }

          //if we get past the above code, we know we have a correct operator

          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.


          //Operator Abstract, cannot do this. Need to fix 27:45 Starter Code Video
          //Operator newOperator = new Operator();

          Operator newOperator = Operator.getOperator(expressionToken);//fetch correct operator from hashmap using token
          // do not have direct access to hashmap from here

          // protect peek(), if empty peeking will throw errors
          // while new priority incoming is greater than current priority in the stack
          while (!(operatorStack.empty()) && operatorStack.peek().priority() >= newOperator.priority() ) {
            // note that when we eval the expression 1 - 2 we will
            // push the 1 then the 2 and then do the subtraction operation
            // This means that the first number to be popped is the
            // second operand, not the first operand - see the following code
            Operator operatorFromStack = operatorStack.pop();
            Operand operandTwo = operandStack.pop();
            Operand operandOne = operandStack.pop();
            Operand result = operatorFromStack.execute( operandOne, operandTwo );
            operandStack.push( result );
          }

          operatorStack.push( newOperator );
        }

      }


    } // end of while (Tokenizer.hasMoreTokens)

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty;
    // Suggestion: create a method that processes the operator stack until empty.

    // Here we must create a method that will process operator stack until empty
    // evaluate remaining operators 33:10 Starter Code


      while (!operatorStack.empty() && operatorStack.peek().priority() > -1)          // Executes all operators including brackets
      {
        Operator operatorFromStack = operatorStack.pop();
        Operand operandTwo = operandStack.pop();
        Operand operandOne = operandStack.pop();
        operandStack.push(operatorFromStack.execute(operandOne, operandTwo));
      }




      return operandStack.peek().getValue(); // return value stored at top of operandStack
  }
}
