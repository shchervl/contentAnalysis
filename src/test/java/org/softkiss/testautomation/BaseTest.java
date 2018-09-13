package org.softkiss.testautomation;

import io.qameta.allure.Attachment;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.slf4j.LoggerFactory;
import org.softkiss.testautomation.client.ClientFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite(groups = {"init"}, alwaysRun = true)
    public void beforeSuiteInit() {
    }

    @BeforeClass(groups = {"init"}, alwaysRun = true)
    public void beforeClassInit() {
    }

    @BeforeMethod(groups = {"init"}, alwaysRun = true)
    public void beforeMethodInit() {
    }

    @AfterMethod(groups = {"init"}, alwaysRun = true)
    public void afterMethodStop(ITestResult testResult) throws IOException {
        if (testResult.getThrowable() != null) {
            takeScreenShot(testResult.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = {"init"}, alwaysRun = true)
    public void afterClassStop() {
        ClientFactory.getInstance().removeDriver();
    }

    @AfterSuite(groups = {"init"}, alwaysRun = true)
    public void afterSuiteStop() {
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String failureReason) {
        LOGGER.info(String.format("Taking screenshot due to fail in method %s", failureReason));
        return ClientFactory.getInstance().getDriver().getScreenshotAs(OutputType.BYTES);
    }

}