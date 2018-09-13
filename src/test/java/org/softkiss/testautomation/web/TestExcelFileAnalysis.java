package org.softkiss.testautomation.web;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.softkiss.testautomation.BaseTest;
import org.softkiss.testautomation.client.WorkBookAnalyzer;
import org.softkiss.testautomation.model.AnalyticsData;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.softkiss.testautomation.client.FileDownloader.downloadFile;
import static org.testng.Assert.assertTrue;


@Feature("Analytic in spread sheets")
public class TestExcelFileAnalysis extends BaseTest {


    @AfterMethod(groups = {"init"}, alwaysRun = true)
    @Override
    public void afterMethodStop(ITestResult testResult) throws IOException {
        super.afterMethodStop(testResult);
    }

    @AfterClass(groups = {"init"}, alwaysRun = true)
    @Override
    public void afterClassStop() {
    }

    @Story("Anylyze analytics content in spread sheet")
    @Test
    public void testThatAnalyticsRecordIsPresent() throws IOException, InterruptedException, InvalidFormatException {
        String fileURL = "https://docs.google.com/spreadsheets/d/1ABkB7zsdsP_ohf3itrpYqGPsSrEaSjbxeiTnLIG5rUY/export?format=xlsx&id=1ABkB7zsdsP_ohf3itrpYqGPsSrEaSjbxeiTnLIG5rUY";
        String fileName = "TestExcel.xlsx";
        AnalyticsData analyticsData = new AnalyticsData("Coffee", 15.25, 100000.0, 12000.0);

        downloadFile(fileURL, fileName);
        assertTrue(new WorkBookAnalyzer(new File(fileName)).getAnalyticsData().contains(analyticsData));
    }
}



