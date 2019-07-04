package lab3.termcalc;

import java.io.*;

import java.util.Scanner;

import lab3.expression.DerivativeExpression;
import lab3.expression.Expression;
import lab3.expression.ProductExpression;
import lab3.expression.Variable;

/**
 * Main entry point for the command line calculator
 */
public class Main {
    /**
     * @param args program arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //Create Instances(ExpressionMaker,TerminalCalculator)
    	TerminalCalculator exp = new TerminalCalculator(new ExpressionMakerImp());
    	
		Variable  x1 = new Variable ("x");
		x1.store(1);
		//System.out.println(new DerivativeExpression(new ProductExpression(x1,x1),x1).eval());
		
    	while(true) 
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    		String str = null; 
    		System.out.println("输入:"); 
    		str = br.readLine(); 
    		System.out.print("结果 :");
    		System.out.println(exp.run(str).eval());
    	}
    	
        //Use TerminalCalculator
        //throw new Exception("Need to be implemented锛�");
    }
}
