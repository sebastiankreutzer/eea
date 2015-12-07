package de.tu_darmstadt.gdi1.tanks.tests.tutors.suites;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TanksTestsuiteAll {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("All tutor tests for Tanks");
		
		suite.addTest(de.tu_darmstadt.gdi1.tanks.tests.tutors.suites.TanksTestsuiteMinimal.suite());
		suite.addTest(de.tu_darmstadt.gdi1.tanks.tests.tutors.suites.TanksTestsuiteExtended1.suite());
		suite.addTest(de.tu_darmstadt.gdi1.tanks.tests.tutors.suites.TanksTestsuiteExtended2.suite());
		suite.addTest(de.tu_darmstadt.gdi1.tanks.tests.tutors.suites.TanksTestsuiteExtended3.suite());
		
		return suite;
	}
}
