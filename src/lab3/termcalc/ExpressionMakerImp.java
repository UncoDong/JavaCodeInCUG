package lab3.termcalc;

import lab3.expression.*;

/**
 * Class to implement ExpressionMaker and create a new Expression
 * @author zhilinh
 *
 */
public class ExpressionMakerImp implements ExpressionMaker{

	/**
	 * Method that returns a new Expression of the sum of addend1 and addend2.
	 */
	@Override
	public Expression sumExpression(Expression addend1, Expression addend2) {
		return new SumExpression(addend1, addend2);
	}

	/**
	 * Method that returns a new Expression of the difference of op1 and op2.
	 */
	@Override
	public Expression differenceExpression(Expression op1, Expression op2) {
		return new DifferenceExpression(op1, op2);
	}

	/**
	 * Method that returns a new Expression of the product of factor1 and factor2.
	 */
	@Override
	public Expression productExpression(Expression factor1, Expression factor2) {
		return new ProductExpression(factor1, factor2);
	}

	/**
	 * Method that returns a new Expression of the division of dividend and divisor.
	 */
	@Override
	public Expression divisionExpression(Expression dividend, Expression divisor) {
		return new DivisionExpression(dividend, divisor);
	}

	/**
	 * Method that returns a new Expression of the exponentiation of base and exponent.
	 */
	@Override
	public Expression exponentiationExpression(Expression base, Expression exponent) {
		return new ExponentiationExpression(base, exponent);
	}

	/**
	 * Method that returns a new Expression of the the negated operand.
	 */
	@Override
	public Expression negationExpression(Expression operand) {
		return new NegationExpression(operand);
	}

	/**
	 * Method that returns a new Expression of the absolute value Expression of value.
	 */
	@Override
	public Expression absoluteValueExpression(Expression value) {
		return new AbsoluteValueExpression(value);
	}

	/**
	 * Method that returns a new Expression of the value.
	 */
	@Override
	public Expression numberExpression(double value) {
		return new NumberExpression(value);
	}

}
