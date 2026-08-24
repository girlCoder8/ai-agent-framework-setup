package com.acme.tests.runners;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "com.acme.tests.steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,io.qameta...")  // keep your existing value here
public class RunCucumberTest {
}