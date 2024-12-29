package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {
    private static ExtentReports extent;

    public static void initializeReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }

    public static void finalizeReport() {
        extent.flush();
    }
}
