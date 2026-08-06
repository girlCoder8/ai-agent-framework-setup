package com.acme.framework.api;
import io.restassured.response.Response; import static io.restassured.RestAssured.given;
public final class ApiClient { private final String baseUrl; public ApiClient(String baseUrl){this.baseUrl=baseUrl;} public Response get(String path){return given().baseUri(baseUrl).when().get(path).then().extract().response();} }
