package lab3.expression;



/**
 * 
 * Class to implement Cos value to an Expression.
 * @author UncleDong
 *
 */
public final class CosExpression implements Expression {

	private Expression value;
	
	/**
	 * Public constructor that assigns a value to the
	 * instance Expression.
	 * 
	 * @param value of an Expression which needs to get
	 * an Cos value.
	 */
	public CosExpression(Expression value) {
		this.value = value;
	}
	
	/**
	 * toString method that returns the Expression with
	 * Cos value.
	 */
	@Override
	public String toString() {
		return "cos("+ value.toString()+")";
	}
	
	/**
	 * Method that returns the value of the Expression.
	 * 
	 * @return returns the value of the Expression.
	 */
	public double eval() {
		
		return Math.cos(value.eval());
	}

}
