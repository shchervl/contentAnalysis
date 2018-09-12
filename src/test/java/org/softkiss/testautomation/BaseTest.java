package org.softkiss.testautomation;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.slf4j.LoggerFactory;
import org.softkiss.testautomation.client.ClientFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
    public void afterMethodStop(ITestResult testResult) {
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