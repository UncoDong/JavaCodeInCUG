package lab3.expression;



/**
 * 
 * Class to implement sin value to an Expression.
 * @author UncleDong
 *
 */
public final class SinExpression implements Expression {

	private Expression value;
	
	/**
	 * Public constructor that assigns a value to the
	 * instance Expression.
	 * 
	 * @param value of an Expression which needs to get
	 * an sin value.
	 */
	public SinExpression(Expression value) {
		this.value = value;
	}
	
	/**
	 * toString method that returns the Expression with
	 * sin value.
	 */
	@Override
	public String toString() {
		return "sin("+ value.toString()+")";
	}
	
	/**
	 * Method that returns the value of the Expression.
	 * 
	 * @return returns the value of the Expression.
	 */
	public double eval() {
		
		return Math.sin( value.eval());
	}

}
