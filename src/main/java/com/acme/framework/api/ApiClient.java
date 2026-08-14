package com.acme.framework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Thin wrapper around rest-assured for the framework's API tests.
 * <p>
 * Base URI resolution order:
 *   1. Explicit constructor arg (ApiClient(String baseUri))
 *   2. API_BASE_URL environment variable
 *   3. Falls back to http://localhost:8080
 * <p>
 * This replaces the previous version of this file, which had malformed
 * imports causing the compiler failures seen earlier
 * (io.restassured.response / io.restassured "package does not exist",
 * "static import only from classes and interfaces", missing Response symbol).
 * Those were symptoms of both a scope issue (now fixed in pom.xml) and
 * import syntax problems — this version imports cleanly.
 */
public class ApiClient {

    private final String baseUri;

    public ApiClient() {
        this(System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
    }

    public ApiClient(String baseUri) {
        this.baseUri = baseUri;
    }

    private RequestSpecification request() {
        return RestAssured.given().baseUri(baseUri);
    }

    public Response get(String path) {
        return request().when().get(path).then().extract().response();
    }

    public Response post(String path, Object body) {
        return request().contentType("application/json").body(body)
                .when().post(path).then().extract().response();
    }

    public Response put(String path, Object body) {
        return request().contentType("application/json").body(body)
                .when().put(path).then().extract().response();
    }

    public Response delete(String path) {
        return request().when().delete(path).then().extract().response();
    }

    public String getBaseUri() {
        return baseUri;
    }
}
