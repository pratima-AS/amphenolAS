/**
 * @author manoj.ghadei
 *
 */

package com.vrt.testcases;

import java.io.FileNotFoundException;


import org.testng.TestNG;

import com.vrt.Listners.ExtentReporterNG;

public class testRunner {
	
	static TestNG testng;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException {		

	        
		ExtentReporterNG er = new ExtentReporterNG();

		testng = new TestNG();
		testng.setTestClasses(new Class[] {HitNTrialTests.class});
		//testng.setTestClasses(new Class[] {QualificationProcessTest.class});

		testng.run();			
	}

}
