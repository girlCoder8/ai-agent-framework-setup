package com.acme.tests.steps.mobile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.appium.java_client.AppiumDriver;
import com.acme.framework.mobile.MobileDriverFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ASSUMPTIONS — verify against your actual MobileDriverFactory class:
 *   - MobileDriverFactory.createDriver() returns an AppiumDriver
 *     (IOSDriver or AndroidDriver depending on a configured platform)
 *   - Platform/device capabilities are read internally by the factory
 *     (e.g. from a config file or env vars), not passed in here.
 * Adjust the method name / signature to match the real factory API if different.
 * <p>
 * This scenario is tagged @mobile and is currently excluded from the
 * main CI run (filter: "not @mobile and not @web"), so it won't execute
 * in the pipeline yet — but it needs to compile.
 */
public class MobileLaunchSteps {

    private AppiumDriver driver;

    @Given("a mobile device is configured")
    public void a_mobile_device_is_configured() {
        driver = MobileDriverFactory.createDriver();
        assertNotNull(driver, "MobileDriverFactory failed to create a driver session");
    }

    @When("I start the application session")
    public void i_start_the_application_session() {
        // Session is started as part of driver creation for Appium.
        // If your factory separates creation from session start, call
        // that explicit start method here instead.
        assertNotNull(driver.getSessionId(), "No active Appium session ID");
    }

    @Then("the mobile session should be active")
    public void the_mobile_session_should_be_active() {
        assertTrue(driver.getSessionId() != null, "Mobile session is not active");
        driver.quit();
    }
}
