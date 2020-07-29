package com.FlipkartDemo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.FlipkartDemo.custom.CustomFunction;

public class GenerateTestNGXML {

	public static String rootDir = CustomFunction.getRootDir();

	static XmlTest test = null;
	static XmlSuite suite = null;

	/**
	 * Method:generateTestNGXMLfile Description: This method generates testNg.xml by
	 * reading the data mentioned in "Automation_DS"Excel
	 * 
	 * @throws Exception
	 */
	public static void generateXMLfile() throws Exception {
		System.out.println("rootDir=======================" + rootDir);
		List<String> featureNameList = ExcelReader
				.getGlobalFeatureSheets(rootDir + "\\src\\test\\resources\\testData\\Automation_DS.xls");

		for (int i = 0; i < featureNameList.size(); i++) {
			System.out.println("Module name" + featureNameList.get(i).toLowerCase());
			generateModuleXMLFile(featureNameList.get(i).toLowerCase());

		}

	}

	/**
	 * Method:generateModuleTestNGXMLFile Description: This method generates
	 * testNg.xml by reading the data mentioned in "Automation_DS"Excel module wise
	 * 
	 * @throws Exception
	 */
	public static void generateModuleXMLFile(String module) throws Exception {
		try {

			FileWriter writer = null;
			// Fetching TC details from Excel
			List<TCExecutor> tcExecutorDetailsList = ExcelReader.getTCExecutorDetails(
					rootDir + "\\src\\test\\resources\\testData\\Automation_DS.xls", module.toUpperCase());
			System.out.println("List Size : " + tcExecutorDetailsList.size());
			System.out.println("Feature name: " + module);

			if (tcExecutorDetailsList.size() != 0) {
				// Create an instance of XmlSuite and assign a name for it.
				suite = new XmlSuite();

				suite.setName(" Flipkart Automation :" + module.toUpperCase());

				suite.setGroupByInstances(true);

				// For adding listners to suite
				List<String> listnerClasses = new ArrayList<String>();

				// suite.setListeners(listnerClasses);

				for (int i = 0; i < tcExecutorDetailsList.size(); i++) {

					//
					// Create a instance of XmlTest and assign a name for
					// it.
					test = new XmlTest(suite);

					test.setName(tcExecutorDetailsList.get(i).getModule() + "_"

							+ tcExecutorDetailsList.get(i).getTc_Name());

					// This Map can hold your testng Parameters.
					Map<String, String> testngParams = new HashMap<String, String>();

					// Assigning the DRIVER.
					testngParams.put("DRIVER", tcExecutorDetailsList.get(i).getDriver());

					// Assigning the Home Page URL.
					testngParams.put("HOMEPAGE_URL", tcExecutorDetailsList.get(i).getHomepage_url());

					// Assigning the HMC_USERNAME
					testngParams.put("USERNAME", tcExecutorDetailsList.get(i).getUserName());

					// Assigning the HMC_PASSWORD
					testngParams.put("PASSWORD", tcExecutorDetailsList.get(i).getPassword());
					// Assigning the TC_NAME.
					testngParams.put("TC_NAME", tcExecutorDetailsList.get(i).getTc_Name());

					// Add any parameters that you want to set to the Test.
					test.setParameters(testngParams);

					// test.setPreserveOrder("true");
					XmlClass testClass = null;
					// Create an array list of XmlClass
					ArrayList<XmlClass> classes = new ArrayList<XmlClass>();

					testClass = new XmlClass();
					// =====================================

					testClass.setName("com.FlipkartDemo.test" + "." + tcExecutorDetailsList.get(i).getTc_Name());

					classes.add(testClass);
					test.setXmlClasses(classes);

					// Creating TestNG.xml file

					File file = new File(System.getProperty("user.dir") + "/testSuites/TestSuite_" + module + ".xml");

					writer = new FileWriter(file);
					writer.write(suite.toXml());

					// }
				}
				writer.close();
				tcExecutorDetailsList.clear();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// For testing purpose
	public static void main(String args[]) throws Exception {

		generateXMLfile();
	}
}