package lab3.operator;

/**
 * Enum that contains implements of UnaryOperator
 * @author zhilinh
 *
 */
public enum UnaryOperatorImp implements UnaryOperator {
	ABS{
		
		/**
		 * toString method that returns the abs operator "abs".
		 */
		@Override
		public String toString() {
			return "abs";
		}
		
		/**
		 * Method that returns the absolute value of arg.
		 * 
		 * @param arg as the value.
		 * @return the absolute value.
		 */
		public double apply(double arg){
			return arg<0? -1*arg:arg;
		}
	},
	NEGATION{
		
		/**
		 * toString method that returns the negation operator "锟斤拷".
		 */
		@Override
		public String toString() {
			return "neg";
		}
		
		/**
		 * Method that returns the value of arg with a contrary sign.
		 * 
		 * @param arg as the value.
		 * @return the negated value.
		 */
		public double apply(double arg){
			return -1*arg;
		}
	},
	SIN{
		
		/**
		 * toString method that returns the negation operator "锟斤拷".
		 */
		@Override
		public String toString() {
			return "sin";
		}
		
		/**
		 * Method that returns the value of sin with a contrary sign.
		 * 
		 * @param arg as the value.
		 * @return the negated value.
		 */
		public double apply(double arg){
			return Math.sin(arg);
		}
	},
	COS{
		
		/**
		 * toString method that returns the negation operator "锟斤拷".
		 */
		@Override
		public String toString() {
			return "cos";
		}
		
		/**
		 * Method that returns the value of arg with a contrary sign.
		 * 
		 * @param cos as the value.
		 * @return the negated value.
		 */
		public double apply(double arg){
			return  Math.cos(arg);
		}
	},
	TAN{
		
		/**
		 * toString method that returns the negation operator "锟斤拷".
		 */
		@Override
		public String toString() {
			return "tan";
		}
		
		/**
		 * Method that returns the value of arg with a contrary sign.
		 * 
		 * @param tan as the value.
		 * @return the negated value.
		 */
		public double apply(double arg){
			return  Math.tan(arg);
		}
	},	
	LN{
		
		/**
		 * toString method that returns the negation operator "锟斤拷".
		 */
		@Override
		public String toString() {
			return "lg10";
		}
		
		/**
		 * Method that returns the value of arg with a contrary sign.
		 * 
		 * @param ln as the value.
		 * @return the negated value.
		 */
		public double apply(double arg){
			return Math.log10(arg);
		}
	};
}