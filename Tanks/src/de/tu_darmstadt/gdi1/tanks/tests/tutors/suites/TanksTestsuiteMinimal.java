package de.tu_darmstadt.gdi1.tanks.tests.tutors.suites;

import de.tu_darmstadt.gdi1.tanks.tests.students.testcases.KeyboardInputTest;
import de.tu_darmstadt.gdi1.tanks.tests.students.testcases.ParseMapTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TanksTestsuiteMinimal {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Tutor tests for Tanks - Minimal");
		suite.addTest(new JUnit4TestAdapter(ParseMapTest.class));
		suite.addTest(new JUnit4TestAdapter(KeyboardInputTest.class));
		return suite;
	}
}
