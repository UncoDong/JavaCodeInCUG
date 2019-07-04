package lab3.guicalc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lab3.operator.BinaryOperator;
import lab3.operator.BinaryOperatorImp;
import lab3.operator.UnaryOperator;
import lab3.operator.UnaryOperatorImp;

/**
 * Main program that runs the GUI Calculator
 */
public class Main {

    /**
     * Add BinaryOperators and UnaryOperators implements to the calculator.
     * 
     * @param args as input
     */
    public static void main(String[] args) {
        // Generating OperatorSet
        //throw new Exception("Need to be implemented锛�");
    	Set<UnaryOperator> unaryOperators =new HashSet<>();
    	unaryOperators.add(UnaryOperatorImp.ABS);
    	unaryOperators.add(UnaryOperatorImp.NEGATION);
    	unaryOperators.add(UnaryOperatorImp.TAN);
    	unaryOperators.add(UnaryOperatorImp.SIN);
    	unaryOperators.add(UnaryOperatorImp.COS);
    	unaryOperators.add(UnaryOperatorImp.LN);
    	
        Set<BinaryOperator> binaryOperators = new HashSet<BinaryOperator>();
        binaryOperators.add(BinaryOperatorImp.ADDITION);
        binaryOperators.add(BinaryOperatorImp.DIVISION);
        binaryOperators.add(BinaryOperatorImp.EXPONENTIATION);
        binaryOperators.add(BinaryOperatorImp.MULTIPLICATION);
        binaryOperators.add(BinaryOperatorImp.SUBTRACTION);

        new GuiCalculator(unaryOperators,binaryOperators);
        // Run the calculator!
        //throw new Exception("Need to be implemented锛�");

    }
}
