/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package rules;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * JUnit tests for RulesOf6005.
 */
public class RulesOf6005Test {
    
    /**
     * Tests the mayUseCodeInAssignment method.
     */
    @Test
    public void testMayUseCodeInAssignment() {
        assertFalse("´íÎó1£¡",
                RulesOf6005.mayUseCodeInAssignment(false,true, false, false, false));
        assertTrue("´íÎó2!",
                RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true));
    }
}
