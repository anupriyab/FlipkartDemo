package com.FlipkartDemo.util;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.FlipkartDemo.custom.CustomFunction;

public class ExcelReader {
	static List<TCExecutor> tcExecutorDetailsList;

	public static String rootDir = CustomFunction.getRootDir();

	/**
	 * Method:getTCExecutorDetails Description:This method fetches the test case
	 * details from Excel whose Execute Status is "YES"
	 * 
	 * @param fileName :Excel File name from where data to be fetched
	 * @return :tcExecutorDetailsList
	 */
	public static List<TCExecutor> getTCExecutorDetails(String fileName, String sheetame) {
		tcExecutorDetailsList = new ArrayList<TCExecutor>();

		/**
		 * Create a new instance for cellDataList
		 */
		List<List<HSSFCell>> cellDataList = new ArrayList<List<HSSFCell>>();
		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheetame);
			/**
			 * Iterate the rows and cells of the spreadsheet * to get all the data
			 */
			Iterator<Row> rowIterator = hssfSheet.rowIterator();

			while (rowIterator.hasNext()) {
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> iterator = hssfRow.cellIterator();

				List<HSSFCell> cellTempList = new ArrayList<HSSFCell>();
				while (iterator.hasNext()) {

					HSSFCell hssfCell = (HSSFCell) iterator.next();

					cellTempList.add(hssfCell);
					// cellTempList is a list obj that contains all columns value for perticular row
				}
				cellDataList.add(cellTempList);
				// cellDataList is a list obj that contains column values for all available rows
				// in excel
			}

			for (int i = 0; i < cellDataList.size(); i++) {

				List cellTempList = (List) cellDataList.get(i);

				if (cellTempList.get(0).toString().equalsIgnoreCase("YES")) {

					TCExecutor tcExecutorDetailsObj = new TCExecutor();

					tcExecutorDetailsObj
					.setExecute(cellTempList.get(getColumnIndex(fileName, sheetame, "EXECUTE")).toString());
					// Set test case module
					tcExecutorDetailsObj
					.setModule(cellTempList.get(getColumnIndex(fileName, sheetame, "MODULE")).toString());
					// Set test case name
					tcExecutorDetailsObj
					.setTc_Name(cellTempList.get(getColumnIndex(fileName, sheetame, "TC_NAME")).toString());
					// Set test case DRIVER value
					tcExecutorDetailsObj
					.setDriver(cellTempList.get(getColumnIndex(fileName, sheetame, "DRIVER")).toString());
					// Set testcase URL value
					tcExecutorDetailsObj.setHomepage_url(
							cellTempList.get(getColumnIndex(fileName, sheetame, "HOMEPAGE_URL")).toString());
					System.out.println(" App url displayed"
							+ cellTempList.get(getColumnIndex(fileName, sheetame, "HOMEPAGE_URL")).toString());

					// Set test case HMC_USERNAME value
					tcExecutorDetailsObj
					.setUserName(cellTempList.get(getColumnIndex(fileName, sheetame, "USERNAME")).toString());

					// Set test case HMC_PASSWORD value
					tcExecutorDetailsObj
					.setPassword(cellTempList.get(getColumnIndex(fileName, sheetame, "PASSWORD")).toString());

					// Add GlobalExecutorDetails object to
					// globalExecutorDetailsList
					tcExecutorDetailsList.add(tcExecutorDetailsObj);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tcExecutorDetailsList;
	}

	/**
	 * Method:getTCExecuter sheet details Description:This method fetches the TC
	 * Executer Sheets
	 * 
	 * @param fileName :Excel File name from where data to be fetched
	 * @return :
	 */
	public static List<String> getGlobalFeatureSheets(String fileName) {
		// System.out.println("fileName=========="+fileName);

		List<String> sheetList = new ArrayList<String>();

		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);

			// Get Sheet Count
			int sheetCount = workBook.getNumberOfSheets();

			// Add Feature GLOBALEXECUTER sheets to list
			for (int i = 0; i < sheetCount; i++) {

				if (workBook.getSheetName(i).contains("TCEXECUTER")) {
					System.out.println("inside");
					// sheetList.add(workBook.getSheetName(i).replace("GLOBALEXECUTER_",""));
					sheetList.add(workBook.getSheetName(i));

				}
			}

			for (int i = 0; i < sheetList.size(); i++) {
				// System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
				// System.out.println("Sheet name :" + sheetList.get(i));

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Outside");
		return sheetList;
	}

	/**
	 * Method:getColumnValue Description:This method fetches the column value for
	 * the specified version from the excel sheet.
	 * 
	 * @param fileName    :Excel File name from where data to be fetched
	 * @param sheet       : Sheet name of the excel
	 * @param dataVersion : DataVesion for which column value to be retrieved
	 * @param colName     : Name of the column for which column value to be
	 *                    retrieved
	 * 
	 * @return :colValue
	 */
	public static String getColumnValue(String fileName, String sheet, String dataVersion, String colName) {

		int columnIndex = 0;
		String expString = null;
		/**
		 * Create a new instance for cellDataList
		 */
		List<List<HSSFCell>> cellDataList = new ArrayList<List<HSSFCell>>();
		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheet);
			/**
			 * Iterate the rows and cells of the spreadsheet * to get all the datas
			 */
			Iterator<Row> rowIterator = hssfSheet.rowIterator();

			while (rowIterator.hasNext()) {
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> iterator = hssfRow.cellIterator();

				List<HSSFCell> cellTempList = new ArrayList<HSSFCell>();
				while (iterator.hasNext()) {

					HSSFCell hssfCell = (HSSFCell) iterator.next();

					if (hssfRow.getRowNum() == 0) {

						if (hssfCell.getStringCellValue().equalsIgnoreCase(colName)) {
							columnIndex = hssfCell.getColumnIndex();
						}
					}

					cellTempList.add(hssfCell);
				}
				cellDataList.add(cellTempList);
			}
			for (int i = 0; i < cellDataList.size(); i++) {

				List cellTempList = (List) cellDataList.get(i);

				for (int j = 0; j < cellTempList.size(); j++) {

					if (cellTempList.get(j).toString().equalsIgnoreCase(dataVersion)) {
						expString = cellTempList.get(columnIndex).toString();

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expString;
	}

	/**
	 * Method: getColumnIndex Description:This method return the specific column
	 * cell value from Excel sheet.
	 * 
	 * 
	 * @param fileName : Excel file absolute path
	 * @param sheet    : Name of the target sheet
	 * @param colName  : Name of the target column
	 * @return : return the cell value of the specific column.
	 */
	public static Integer getColumnIndex(String fileName, String sheet, String colName) {

		int columnIndex = 0;
		/**
		 * Create a new instance for cellDataList
		 */
		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheet);
			/**
			 * Iterate the rows and cells of the spreadsheet * to get all the data
			 */
			Iterator<Row> rowIterator = hssfSheet.rowIterator();

			while (rowIterator.hasNext()) {
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> iterator = hssfRow.cellIterator();

				while (iterator.hasNext()) {

					HSSFCell hssfCell = (HSSFCell) iterator.next();

					if (hssfRow.getRowNum() == 0) {
						if (hssfCell.getStringCellValue().equalsIgnoreCase(colName)) {
							columnIndex = hssfCell.getColumnIndex();

						}
					}

				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return columnIndex;
	}

	// Added for testing purpose
	public static void main(String args[]) throws Exception, SQLException, Exception {

		getGlobalFeatureSheets(rootDir + "\\src\\test\\resources\\testData\\Automation_DS.xls");
		getTCExecutorDetails(rootDir + "\\src\\test\\resources\\testData\\Automation_DS.xls", "TCEXECUTER");

	}
}