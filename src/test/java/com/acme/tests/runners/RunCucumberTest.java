package com.acme.tests.runners;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = "cucumber.glue",
        value = "com.acme.tests.steps")

public class RunCucumberTest {
}