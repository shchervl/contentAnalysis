package org.softkiss.testautomation.client;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.LoggerFactory;
import org.softkiss.testautomation.environment.EnvironmentConfigurator;

import java.net.MalformedURLException;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ClientFactory {

    public static final int TIME_WAIT_SECONDS = 5;
    private static final int SCRIPT_TIME_OUT_WAIT_SECONDS = 3 * 60;
    private static final int PAGE_LOAD_TIME_WAIT_SECONDS = 10 * 60;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ClientFactory.class);
    protected static EnvironmentConfigurator environmentConfigurator;
    private static ClientFactory instance = new ClientFactory();

    private ClientFactory() {
        environmentConfigurator = EnvironmentConfigurator.getInstance();
    }

    public static ClientFactory getInstance() {
        return instance;
    }

    ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>() {

        @Override
        protected RemoteWebDriver initialValue() {
            RemoteWebDriver webDriver = null;
            try {
                ClientType clientType = ClientType.valueOf(environmentConfigurator.getTestClient().toUpperCase());
                switch (clientType) {
//                    case IE:
//                        webDriver = startInternetExplorer();
//                        break;
//                    case FF:
//                        webDriver = startFirefox();
//                        break;
                    case GC:
                        webDriver = startChrome();
                        break;
                    default:
                        webDriver = startChrome();
                        break;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            if (environmentConfigurator.isGridUsed()) {
                webDriver.setFileDetector(new LocalFileDetector());
            }
            webDriver.manage().window().maximize();
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().setScriptTimeout(SCRIPT_TIME_OUT_WAIT_SECONDS, SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIME_WAIT_SECONDS, SECONDS);
            return webDriver;
        }


        private RemoteWebDriver startChrome() throws MalformedURLException {
            RemoteWebDriver webDriver;
            String chromedriverPath = "";
            if (SystemUtils.IS_OS_MAC) {
                chromedriverPath = currentThread().getContextClassLoader().getResource("chromedriver").getPath();
            }
            if (SystemUtils.IS_OS_WINDOWS) {
                chromedriverPath = currentThread().getContextClassLoader().getResource("chromedriver.exe").getPath();
            }
            System.setProperty("webdriver.chrome.driver", chromedriverPath);
            LOGGER.info("Property 'webdriver.chrome.driver' is set to " + System.getProperty("webdriver.chrome.driver"));
            webDriver = new ChromeDriver();
            return webDriver;
        }


// TODO: Rework with new version of IE driver
//        /**
//         * Launches Internet Explorer natively or on the remote machine according to settings
//         */
//        private RemoteWebDriver startInternetExplorer() throws MalformedURLException {
//            RemoteWebDriver webDriver;
//            DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
//            cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//            cap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//            cap.setCapability("ensureCleanSession", true);
//            cap.setCapability("enableElementCacheCleanup", true);
//            cap.setCapability("nativeEvents", true);
//            cap.setCapability("enablePersistentHover", true);
//
//            if (environmentConfigurator.isGridUsed()) {
//                webDriver = new RemoteWebDriver(new URL("http://" + environmentConfigurator.getSeleniumHub() + "/wd/hub"), cap);
//            } else {
//                if (System.getProperty("webdriver.ie.driver") == null) {
//                    String chromeDriverPath = currentThread().getContextClassLoader().getResource("IEDriverServer.exe").getPath();
//                    LOGGER.warn("webdriver.ie.driver is not set. will now try to use [" + chromeDriverPath + "]");
//                    System.setProperty("webdriver.ie.driver", chromeDriverPath);
//                }
//                webDriver = new InternetExplorerDriver(cap);
//            }
//            return webDriver;
//        }

//        TODO: rework with new version of Firefox
//        /**
//         * Launches Firefox natively or on the remote machine according to settings
//         */
//        private RemoteWebDriver startFirefox() throws MalformedURLException {
//            RemoteWebDriver webDriver = null;
//            FirefoxBinary fb = new FirefoxBinary();
//            fb.setTimeout(SECONDS.toMillis(TIME_WAIT_SECONDS * 2));
//            FirefoxProfile profile = new FirefoxProfile();
//            if (!environmentConfigurator.isGridUsed()) {
//                try {
//                    File firebugFile = new File(System.getProperty("user.dir") + "/firebug-2.0.4.xpi");
//                    File firepathFile = new File(System.getProperty("user.dir") + "/firepath-0.9.7-fx.xpi");
//                    if (firebugFile.canRead() && firepathFile.canRead()) {
//                        profile.addExtension(firebugFile);
//                        profile.addExtension(firepathFile);
//                    } else {
//                        LOGGER.warn("Firefox extensions: [firebug] [firepath] are not available");
//                    }
//                } catch (Exception e) {
//                    LOGGER.error("", e);
//                }
//            }
////            profile.setEnableNativeEvents(false);
//            profile.setPreference("dom.successive_dialog_time_limit", 0);
//            profile.setPreference("dom.popup_maximum", 200000);
//            profile.setPreference("app.update.enabled", false);
//
//            try {
//                if (environmentConfigurator.isGridUsed()) {
//                    DesiredCapabilities cap = DesiredCapabilities.firefox();
//                    cap.setCapability(FirefoxDriver.PROFILE, profile);
//                    webDriver = new RemoteWebDriver(new URL("http://" + environmentConfigurator.getSeleniumHub() + "/wd/hub"), cap);
//                } else {
//                    webDriver = new FirefoxDriver();
//                }
//            } catch (WebDriverException e) {
//                LOGGER.error("", e);
//            }
//            return webDriver;
//        }
//
    };

    public RemoteWebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
