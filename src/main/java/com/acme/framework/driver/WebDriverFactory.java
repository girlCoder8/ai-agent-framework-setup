package com.acme.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Factory for Selenium WebDriver instances.
 *
 * Headless mode is controlled by the HEADLESS environment variable and
 * defaults to true — this matters because ubuntu-latest GitHub Actions
 * runners have no display, so a non-headless ChromeDriver session will
 * fail to start there. Set HEADLESS=false locally if you want to watch
 * the browser run.
 *
 * This is a from-scratch implementation matching the createDriver()
 * call already wired into WebLoginSteps — replace whatever currently
 * exists at this path with this version.
 */
public class WebDriverFactory {

    private static final boolean HEADLESS =
            !"false".equalsIgnoreCase(System.getenv("HEADLESS"));

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        if (HEADLESS) {
            options.addArguments("--headless=new");
        }

        // Standard CI-safe flags — avoids sandbox/shared-memory issues
        // common on containerized runners.
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }
}
