package com.acme.framework.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Factory for Appium mobile driver instances (iOS or Android).
 *
 * Configuration is read entirely from environment variables so this
 * works across local runs and CI without code changes:
 *
 *   MOBILE_PLATFORM "ios" or "android" (required)
 *   APPIUM_SERVER_URL Appium server URL (default: http://127.0.0.1:4723)
 *   DEVICE_NAME e.g. "iPhone 15" / "Pixel 8" (required)
 *   PLATFORM_VERSION e.g. "17.0" / "14" (required)
 *   APP_PATH path to the .app/.ipa or .apk under test (required)
 *   AUTOMATION_NAME optional override; defaults per platform
 *                          (XCUITest for iOS, UiAutomator2 for Android)
 *
 * This is a from-scratch implementation matching the createDriver()
 * call already wired into MobileLaunchSteps — replace whatever
 * currently exists at this path with this version.
 */
public class MobileDriverFactory {

    private static final String DEFAULT_APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public static AppiumDriver createDriver() {
        String platform = requireEnv("MOBILE_PLATFORM").toLowerCase();

        try {
            URL serverUrl = new URL(
                    System.getenv().getOrDefault("APPIUM_SERVER_URL", DEFAULT_APPIUM_SERVER_URL));

            switch (platform) {
                case "ios":
                    return new IOSDriver(serverUrl, buildCapabilities("XCUITest"));
                case "android":
                    return new AndroidDriver(serverUrl, buildCapabilities("UiAutomator2"));
                default:
                    throw new IllegalArgumentException(
                            "Unsupported MOBILE_PLATFORM: " + platform + " (expected 'ios' or 'android')");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid APPIUM_SERVER_URL: " + e.getMessage(), e);
        }
    }

    private static DesiredCapabilities buildCapabilities(String defaultAutomationName) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", requireEnv("DEVICE_NAME"));
        caps.setCapability("platformVersion", requireEnv("PLATFORM_VERSION"));
        caps.setCapability("app", requireEnv("APP_PATH"));
        caps.setCapability("automationName",
                System.getenv().getOrDefault("AUTOMATION_NAME", defaultAutomationName));
        return caps;
    }

    private static String requireEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(name + " environment variable is not set");
        }
        return value;
    }
}
