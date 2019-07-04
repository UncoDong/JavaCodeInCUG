package lab3.expression;

/**
 * Class to compute the division of two Expressions.
 * @author zhilinh
 *
 */
public final class DivisionExpression implements Expression {

	private Expression dividend, divisor;
	
	/**
	 * Public constructor that assigns two Expressions to instance Expressions.
	 * 
	 * @param dividend of the division
     * @param divisor of the division
	 */
	public DivisionExpression(Expression dividend, Expression divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}
	
	/**
	 * toString method that returns a string of assigned Expressions and a division
	 * operator with two parentheses.
	 */
	@Override
	public String toString() {
		return this.dividend.toString()+"/"+this.divisor.toString();
	}
	
	/**
	 * Method that returns the result of the computation.
	 * 
	 * @return returns the value of the Expression.
	 */
	public double eval() {
		return this.dividend.eval()/this.divisor.eval();
	}

}
