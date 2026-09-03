package com.acme.tests.steps.web;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import com.acme.framework.driver.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * ASSUMPTIONS — verify against your actual WebDriverFactory class:
 *   - WebDriverFactory.createDriver() returns a Selenium WebDriver
 *     (e.g. ChromeDriver) already configured for the CI environment
 *     (headless mode, options, etc. handled internally).
 *   - Login page URL is read from an env var so this works across
 *     environments without hardcoding.
 * Adjust the method name / signature to match the real factory API if different.
 * <p>
 * This scenario is tagged @web and is currently excluded from the
 * main CI run (filter: "not @mobile and not @web"), so it won't execute
 * in the pipeline yet — but it needs to compile.
 */
public class WebLoginSteps {

    private WebDriver driver;

    private static final String LOGIN_URL =
            System.getenv().getOrDefault("WEB_LOGIN_URL", "http://localhost:3000/login");

    @Given("the web application is configured")
    public void the_web_application_is_configured() {
        driver = WebDriverFactory.createDriver();
        assertNotNull(driver, "WebDriverFactory failed to create a driver session");
    }

    @When("I open the login page")
    public void i_open_the_login_page() {
        driver.get(LOGIN_URL);
    }

    @Then("the page should load successfully")
    public void the_page_should_load_successfully() {
        assertFalse(driver.getTitle() == null || driver.getTitle().isEmpty(),
                "Login page did not load (empty title) at " + LOGIN_URL);
        driver.quit();
    }
}
