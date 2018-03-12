package com.selenium.commons;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Screenshot implements ITestListener {

	public final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	public WebDriver driver = Configuration.browser();
	public DataSheet ds;
	public CommonCode common;
	//protected String execution = EnvirnomentHandler.getInstance().getEnvirnoment().getexecutionType();
	public int i = 1;
	public static int index = 0;

	static Workbook wbook;
	static WritableWorkbook wwbCopy;
	static String ExecutedTestCasesSheet;
	WritableWorkbook workbook;

	public Screenshot() {
		System.out.println("in screen shot class");
		ds = new DataSheet();

	}

	String testName = null;
	String group;

	@Override
	public void onTestFailure(ITestResult tr) {

		Reporter.log(" Failed Test Method name is:- " + tr.getMethod().getMethodName(), true);
		if (testName != tr.getMethod().getMethodName()) {
			Reporter.log("-Failed-", true);
			Reporter.log("Test method name is:- " + tr.getMethod().getMethodName(), true);
			Reporter.log(
					"Test name is:- "
							+ tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(),
					true);
			group = Arrays
					.toString(tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups())
					.replace("[", "");
			group = group.replace("]", "");
			testName = tr.getMethod().getMethodName();
			System.out.println("value of testname is in if condition   " + testName);
			try {

				ds.setValueIntoCell("sheet1", 0, i, group);
				ds.setValueIntoCell("sheet1", 1, i,
						tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
				ds.setValueIntoCell("sheet1", 2, i,
						tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description());
				ds.setValueIntoCell("sheet1", 3, i, "Fail");
				// i--;
				i++;
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.setProperty(ESCAPE_PROPERTY, "false");

			File file = new File("");
			Calendar lCDateTime = Calendar.getInstance();
			// System.out.println("Calender - Time in milliseconds :"+
			// lCDateTime.getTimeInMillis());
			String a = lCDateTime.getTimeInMillis() + ".jpg";
			Reporter.setCurrentTestResult(tr);
			try {
				System.out.println(file.getCanonicalPath());
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String f = file.getCanonicalPath() + "\\target\\surefire-reports\\"
						+ lCDateTime.getTimeInMillis() + ".jpg";
				FileUtils.copyFile(scrFile, new File(f));
				Reporter.log("<a href=" + "../surefire-reports/" + a
						+ " target=\"_blank\">ScreenShot_" + tr.getName() + "</a>");

				Reporter.setCurrentTestResult(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {

		Reporter.log("Skipped Test Method name is:- " + tr.getMethod().getMethodName(), true);
		Reporter.log(
				"Test name is:- "
						+ tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(),
				true);
		try {
			String group = Arrays
					.toString(tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups())
					.replace("[", "");
			group = group.replace("]", "");
			ds.setValueIntoCell("sheet1", 0, i, group);
			ds.setValueIntoCell("sheet1", 1, i,
					tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
			ds.setValueIntoCell("sheet1", 2, i,
					tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description());
			ds.setValueIntoCell("sheet1", 3, i, "Skip");
			i++;
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		if (testName == tr.getMethod().getMethodName()) {
			i--;
		}

		Reporter.log("Passed Test Method name is:- " + tr.getMethod().getMethodName(), true);
		Reporter.log(
				"Test name is:- "
						+ tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(),
				true);
		Reporter.log(
				"Test desc is:- "
						+ tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description(),
				true);
		try {
			String group = Arrays
					.toString(tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups())
					.replace("[", "");
			group = group.replace("]", "");
			ds.setValueIntoCell("sheet1", 0, i, group);
			ds.setValueIntoCell("sheet1", 1, i,
					tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
			ds.setValueIntoCell("sheet1", 2, i,
					tr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description());
			ds.setValueIntoCell("sheet1", 3, i, "Pass");
			i++;
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void onFinish(ITestContext arg0) {
		ds.closeFile();

	}

	@Override
	public void onStart(ITestContext arg0) {
		Reporter.log("in before class  ", true);
		String filepath = "src/test/resources/Book1.xls";
		filepath = System.getProperty("user.dir") + "/" + filepath;
		File file = new File(filepath);
		if (workbook == null) {
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("en", "EN"));

			try {
				workbook = Workbook.createWorkbook(file, wbSettings);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			workbook.createSheet("sheet1", 0);
			try {
				workbook.write();
				workbook.close();
				Reporter.log("Sheet created", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ds = new DataSheet();
		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
