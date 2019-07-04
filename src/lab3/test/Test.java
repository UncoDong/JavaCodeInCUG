package lab3.test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;

import com.sun.jdi.AbsentInformationException;

import lab3.expression.*;
import lab3.termcalc.ExpressionMakerImp;
import lab3.termcalc.TerminalCalculator;

class Test {
	static TerminalCalculator ter = new TerminalCalculator(new ExpressionMakerImp());
	static String str = null;
	@BeforeEach
	void setUp() throws Exception {
	}

	@org.junit.jupiter.api.Test
	void testSum() {
		//加法运算测试
		str = "3+4";
		assertEquals(7, ter.run(str).eval());
	}
	
	@org.junit.jupiter.api.Test
	void testAbs() {
		//绝对值运算测试
		str = "-4";
		assertEquals(4, new AbsoluteValueExpression(ter.run(str)).eval() );
		str = "-0";
		assertEquals(0, new AbsoluteValueExpression(ter.run(str)).eval() );
		str = "0";
		assertEquals(-0, new AbsoluteValueExpression(ter.run(str)).eval() );
		
	}
	
	@org.junit.jupiter.api.Test
	void testDef() {
		//减法运算测试
		str = "1-4";
		assertEquals(-3, ter.run(str).eval());
		str = "4-1";
		assertEquals(3, ter.run(str).eval());
		str = "0-3";
		assertEquals(-3, ter.run(str).eval());
	}
	
	@org.junit.jupiter.api.Test
	void testDiv() {
		//除法运算测试
		str = "1/(-4)";
		assertEquals(-0.25, ter.run(str).eval());
		str = "(-1)/(-4)";
		assertEquals(0.25, ter.run(str).eval());
		str = "1/3";
		assertEquals(0.3333, ter.run(str).eval(),0.0001);
		str = "2/0";
		assertEquals(Float.POSITIVE_INFINITY, ter.run(str).eval());
	}
	
	@org.junit.jupiter.api.Test
	void testExp() {
		//平方运算测试
		str = "1^4";
		assertEquals(1, ter.run(str).eval());
		str = "4^1";
		assertEquals(4, ter.run(str).eval());
		str = "4^(0.5)";
		assertEquals(2, ter.run(str).eval());
		str = "3^0";
		assertEquals(1, ter.run(str).eval());
	}
	
	@org.junit.jupiter.api.Test
	void testNeg() {
		//取反运算测试
		str = "-4";
		assertEquals(4, new NegationExpression(ter.run(str)).eval() );
		str = "4";
		assertEquals(-4, new NegationExpression(ter.run(str)).eval() );
		str = "0";
		assertEquals(-0, new NegationExpression(ter.run(str)).eval() );
	}

	
	@org.junit.jupiter.api.Test
	void testNum() {
		//单个表达测试
		str = "4";
		assertEquals(4, ter.run(str).eval());
		str = "-4";
		assertEquals(-4, ter.run(str).eval());
		str = "0";
		assertEquals(0, ter.run(str).eval());
	}
	
	@org.junit.jupiter.api.Test
	void testPro() {
		//乘法表达测试
		str = "(-1)*(-4)";
		assertEquals(4, ter.run(str).eval());
		str = "(-1)*4";
		assertEquals(-4, ter.run(str).eval());
	}
	
	@org.junit.jupiter.api.Test
	void testVar() {
		//参数表达测试
		//x*x-2x+1
		Variable  x = new Variable ("x");
		Expression xfang = new ProductExpression(x,x);
		Expression _2x = new ProductExpression(ter.run("2"),x);
		Expression plus =  new DifferenceExpression(xfang, _2x);
		Expression finall = new DifferenceExpression(plus, ter.run("-1"));
		x.store(1);
		assertEquals(0, finall.eval());
		x.store(2);
		assertEquals(1, finall.eval());
	}
	
	@org.junit.jupiter.api.Test
	void testDer() {
		//求导测试
		//2x,x*x-2
		Variable  x = new Variable ("x");
		for(int i = 0;i<100;i++)
		{
			x.store(i);
			assertEquals(new ProductExpression(ter.run("2"),x).eval(), new DerivativeExpression(new ProductExpression(x,x),x).eval(),0.01);
		}
		
		//cosx，sinx
		for(int i = 0;i<100;i++)
		{
			x.store(i);
			assertEquals(new CosExpression(x).eval(), new DerivativeExpression(new SinExpression(x),x).eval(),0.01);
		}
				
	}
	
	//@org.junit.jupiter.api.Test(timeout = 500)
	void testNew() {
		//牛顿运算测试
		Variable  x = new Variable ("x");
		NewtonsMethod niu = new NewtonsMethod();
		Expression fn = new DifferenceExpression(new ProductExpression(x,x), ter.run("2"));
		double ans = niu.zero(fn, x, 1.5, 0.00001);
		assertEquals(1, ans,0.01);
	}
}
