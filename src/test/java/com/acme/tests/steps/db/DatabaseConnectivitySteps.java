package com.acme.tests.steps.db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.acme.framework.db.DatabaseClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectivitySteps {

    private final DatabaseClient dbClient = new DatabaseClient();
    private boolean queryReturnedResult;

    @Given("database credentials are configured")
    public void database_credentials_are_configured() {
        dbClient.connect();
    }

    @When("I execute a read-only validation query")
    public void i_execute_a_read_only_validation_query() {
        queryReturnedResult = dbClient.executeValidationQuery("SELECT 1 FROM DUAL");
    }

    @Then("the query should return a result")
    public void the_query_should_return_a_result() {
        assertTrue(queryReturnedResult, "Validation query returned no rows");
        dbClient.close();
    }
}
