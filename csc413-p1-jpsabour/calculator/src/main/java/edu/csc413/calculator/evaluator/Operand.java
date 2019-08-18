package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
  /**
  * construct operand from string token.
  */
  int operandVal;
  public Operand( String token ) {
    operandVal = Integer.parseInt(token);
  }
  /**
   * construct operand from integer
   */
  public Operand( int value ) {
    operandVal = value;
  }
  /**
   * return value of opernad
   */
  public int getValue() {
    //return 0;
    return operandVal;
  }
  /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token ) {
    //return false;
    try {
      Integer.parseInt(token);
      return true; // return true only if the token is an integer
    } catch(NumberFormatException e) {
      return false;
    }
  }
}
