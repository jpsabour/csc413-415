package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.Operator;
import edu.csc413.calculator.operators.LeftParenthesis;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/ ";

  public Evaluator() {  //constructor
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Evaluator evaluator = (Evaluator) o;
    return Objects.equals(operandStack, evaluator.operandStack) &&
            Objects.equals(operatorStack, evaluator.operatorStack) &&
            Objects.equals(tokenizer, evaluator.tokenizer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operandStack, operatorStack, tokenizer);
  }

  public int eval(String expression ) {
    String token;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );


    while ( this.tokenizer.hasMoreTokens() ) {
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) { //take out the empty spaces
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
          if ( ! Operator.check( token )) {
            System.out.println( "*****invalid token******" );
            throw new RuntimeException("*****invalid token******");
          } else if (")".equals(token)) { // if token is ")"
            while (operatorStack.peek().priority() != 0) {  // look for left parenthesis then take them out
              Operator oldOpr = operatorStack.pop();
              Operand op2 = operandStack.pop();
              Operand op1 = operandStack.pop();
              operandStack.push(oldOpr.execute(op1, op2));
            }
            operatorStack.pop();

            // push left parenthesis to stack
          } else if ("(".equals(token)) {
            operatorStack.push(new LeftParenthesis());

          } else {
            //fix this line: Operator newOperator = new Operator();
            Operator newOperator = Operator.getOperator(token);
            //not empty
            if (!operatorStack.empty()) {
              //fix this line: while (operatorStack.peek().priority() >= newOperator.priority() ) {
              while ((operatorStack.peek().priority() >= newOperator.priority()) && (!operatorStack.isEmpty()) && (operandStack.size()>=2)) {
                Operator oldOpr = operatorStack.pop();
                Operand op2 = operandStack.pop();
                Operand op1 = operandStack.pop();
                operandStack.push(oldOpr.execute(op1, op2));
              }
            }
            operatorStack.push(newOperator);
          }
        }
      }
    }
    //empty
    while (!operatorStack.empty()) {
      Operator oldOpr = operatorStack.pop();
      Operand op2 = operandStack.pop();
      Operand op1 = operandStack.pop();
      operandStack.push(oldOpr.execute(op1, op2));
    }
    return (operandStack.pop().getValue());
  }
}

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks, 
    // that is, we should keep evaluating the operator stack until it is empty;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop.
