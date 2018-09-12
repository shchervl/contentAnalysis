package org.softkiss.testautomation.pageobject;

import io.qameta.allure.Step;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.softkiss.testautomation.client.ClientFactory;
import org.softkiss.testautomation.client.ClientType;
import org.softkiss.testautomation.environment.EnvironmentConfigurator;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

public class BasePage extends HtmlElement {

    protected static final Logger LOGGER = Logger.getLogger(BasePage.class);
    private RemoteWebDriver webDriver = ClientFactory.getInstance().getDriver();
    private WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, ClientFactory.TIME_WAIT_SECONDS);

    public BasePage() {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)), this);
    }

    public RemoteWebDriver getWebDriver() {
        return this.webDriver;
    }

    public void sendKeys(final WebElement webElement, String text) {
        waitForClickable(webElement);
        if (EnvironmentConfigurator.getInstance().getTestClient().equals(ClientType.IE.toString())) {
            webElement.sendKeys("a");
        }
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void pressEnter(final WebElement webElement) {
        waitForClickable(webElement);
        webElement.sendKeys(Keys.ENTER);
    }

    public WebElement waitForVisibility(WebElement webElement) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException nse) {
            LOGGER.error("", nse);
            return null;
        }
        return webElement;
    }

    public WebElement waitForClickable(WebElement webElement) {
        waitForVisibility(webElement);
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (NoSuchElementException nse) {
            LOGGER.error("Try to wait little more (wait for clickable)", nse);
        }
        return webElement;
    }

    public void pause() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public RemoteWebDriver switchToNewlyOpenedTab() {
        RemoteWebDriver webDriverNewTab;
        List<String> currentTabs = new ArrayList<>(getWebDriver().getWindowHandles());
        webDriverNewTab = (RemoteWebDriver) getWebDriver().switchTo().window(currentTabs.get(getWebDriver().getWindowHandles().size() - 1));
        webDriverNewTab.manage().window().maximize();
        return webDriverNewTab;
    }

    protected void moveMouseCursorToWebElement(WebElement webElement) {
        waitForClickable(webElement);
        scrollToElement(webElement);
        Actions action = new Actions(getWebDriver());
        action.moveToElement(webElement).perform();
    }

    protected Actions clickAndHoldWebElement(WebElement webElement) {
        waitForClickable(webElement);
        scrollToElement(webElement);
        Actions action = new Actions(getWebDriver());
        action.clickAndHold(webElement).perform();
        return action;
    }

    protected void doubleClickElement(WebElement webElement) {
        scrollToElement(webElement);
        waitForClickable(webElement);
        Actions action = new Actions(getWebDriver());
        action.doubleClick(webElement).perform();
    }

    protected Object executeJS(final String script, final Object... params) {
        return getWebDriver().executeScript(script, params);
    }

    protected void dragAndDropWebElementHTML5(WebElement draggedWebElement, WebElement targetWebElement) {
        new Actions(getWebDriver()).clickAndHold(draggedWebElement).release(targetWebElement).build().perform();
    }

    protected WebElement scrollToElement(WebElement we) {
        executeJS("arguments[0].scrollIntoView(true);", we);
        return we;
    }

    protected String getValueFromElement(WebElement webElement) {
        return executeJS("return arguments[0].value", webElement).toString();
    }

    @Step
    public void refreshPage() {
        getWebDriver().navigate().refresh();
    }

    public boolean isReadOnly(WebElement webElement) {
        waitForVisibility(webElement);
        try {
            String ngReadonly = webElement.getAttribute("ng-readonly");
            return (ngReadonly.equalsIgnoreCase("readonly") || ngReadonly.equalsIgnoreCase("true"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clicWhileWebElementIsNotVisible(WebElement toClick, WebElement toGet) {
        toClick.click();
        pause();
        try {
            toGet.isDisplayed();
        } catch (Exception e) {
        }
    }

}