package com.acme.tests.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import com.acme.framework.api.ApiClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ASSUMPTIONS — verify against your actual ApiClient class:
 *   - ApiClient has a no-arg (or base-URL-aware) constructor
 *   - ApiClient.get(String path) returns a rest-assured Response
 *   - Base URL / health path are read from env, vars, so this works
 *     in CI without hardcoding an environment.
 * Adjust method names to match ApiClient's real API if different.
 */
public class ApiHealthSteps {

    private ApiClient apiClient;
    private Response response;

    private static final String HEALTH_PATH =
            System.getenv().getOrDefault("API_HEALTH_PATH", "/health");

    @Given("the API service is configured")
    public void the_api_service_is_configured() {
        apiClient = new ApiClient();
        assertNotNull(apiClient, "ApiClient failed to initialize");
    }

    @When("I request the health endpoint")
    public void i_request_the_health_endpoint() {
        response = apiClient.get(HEALTH_PATH);
        assertNotNull(response, "No response received from health endpoint: " + HEALTH_PATH);
    }

    @Then("the API response status should be {int}")
    public void the_api_response_status_should_be(Integer expectedStatus) {
        assertEquals(expectedStatus.intValue(), response.getStatusCode(),
                "Unexpected status code from " + HEALTH_PATH);
    }
}
