package com.FlipkartDemo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

	/**
	 * Properties.
	 */
	public static Properties ObjRepoProperty;
	public static Properties TextProperty;
	public static String rootDir = com.FlipkartDemo.custom.CustomFunction.getRootDir();
	/**
	 * Load Property File.
	 * 
	 * @param hOMEPAGE_URL
	 * 
	 **/
	public static void loadProprtyFile() {

		ObjRepoProperty = new Properties();
		TextProperty = new Properties();

		try {

			// Reading/loading the ObjectRepository property file.
			InputStream is = PropertyFileReader.class.getClassLoader()
					.getResourceAsStream("ObjectRepository.properties");

			System.out.println("rootDir from Property File Reader (Object Repository)= " + rootDir);

			// System.out.println(rootDir+"\\src\\main\\resources\\testData\\ObjectRepository.properties");
			ObjRepoProperty.load(
					new FileInputStream(rootDir + "\\src\\test\\resources\\testData\\ObjectRepository.properties"));

			// Reading/loading the Text property file
			System.out.println("rootDir from Property File Reader (Text)= " + rootDir);
			TextProperty.load(new FileInputStream(rootDir + "\\src\\test\\resources\\testData\\Text.properties"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
