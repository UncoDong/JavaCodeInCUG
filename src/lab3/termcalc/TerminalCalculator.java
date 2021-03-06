package lab3.termcalc;


import junit.*;

import java.util.Objects;

import lab3.expression.Expression;

/**
 * TerminalCalculator - a command line calculator.
 */
public class TerminalCalculator {
    //CHECKSTYLE:OFF
    private final ExpressionParser parser;

    public TerminalCalculator(ExpressionMaker maker) {
        Objects.requireNonNull(maker);
        parser = new ExpressionParser(maker);
    }

    public Expression run(String expression) {
        return parser.eval(expression);
    }
}
