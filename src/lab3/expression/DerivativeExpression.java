package lab3.expression;

/**
 * Class to compute the derivative of an Expression. 
 * @author zhilinh
 *
 */
public class DerivativeExpression implements Expression {

	private final double deltaX = 1e-9;
	private Expression fn;
	private Variable independentVar;
	private double fnVal;
	
	/**
	* Creates an expression representing the derivative of the specified
	* function with respect to the specified variable.
	*
	* @param fn the function whose derivative this expression represents
	* @param independent the variable with respect to which we're
	* differentiating
	*/
	public DerivativeExpression(Expression fn, Variable independent) {
		this.fn = fn;
		this.independentVar = independent;
	}
	
	/**
	 * Method that returns the derivative of the Expression.
	 * 
	 * @return returns the value of the Expression.
	 */
	@Override
	public double eval() {
		double b = this.fn.eval();
		this.independentVar.store(independentVar.eval()+this.deltaX);
		double a = this.fn.eval();
		this.fnVal = (a-b)/this.deltaX;
		//System.out.println(a+" "+b);
		return this.fnVal;
	}

}
