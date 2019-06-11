package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadFileTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testNotKeyWord() {
		assertEquals(false,ReadFile.NotKeyWord("for"));
		assertEquals(false,ReadFile.NotKeyWord("String"));
		assertEquals(false,ReadFile.NotKeyWord("123"));
		assertEquals(true,ReadFile.NotKeyWord("MyName"));
	}

	@Test
	void testCompare() {
		Map<String, Integer> dic1 = new HashMap<String, Integer>();
		Map<String, Integer> dic2 = new HashMap<String, Integer>();
		dic1.put("be", 1);
		dic1.put("do", 1);
		dic1.put("if", 1);
		dic1.put("is", 2);
		dic1.put("it", 3);
		dic1.put("me", 1);
		dic1.put("to", 3);
		dic1.put("up", 1);
		
		dic2.put("be", 1);
		dic2.put("it", 1);
		dic2.put("let", 1);
		assertEquals(0.44,ReadFile.compare(dic1, dic2),0.01);
	}

	
	@Test
	void testGetWord() {
		Set<String> ANS = new HashSet<String>();
	
		ANS.add("ABC");
		ANS.add("BCD");
		ANS.add("CDE");
		assertEquals(ANS,ReadFile.GetWord("ABC BCD,CDE",1));
		ANS.add("i");
		assertEquals(ANS,ReadFile.GetWord("for(int i = 0;i<9;i++)",1));
		assertEquals(ANS,ReadFile.GetWord("1,2,3,4",1));
	}



}
