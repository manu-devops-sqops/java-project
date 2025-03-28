package com.test.sampleapp.integration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by MRomeh.
 */
public class GetHealthStep extends CucumberRoot {
    private ResponseEntity<String> response; // output

    @When("^the client calls /health$")
    public void the_client_issues_GET_health() throws Throwable {
        response = template.getForEntity("/actuator/health", String.class);
    }

    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " +
                response.getBody(), currentStatusCode.value(), is(statusCode));
    }

}
