package api.clients;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.clients.ConfigProvider.*;
import static io.restassured.RestAssured.given;

public abstract class AbstractBaseRestClient {
    protected final RequestSpecification requestSpec;
    protected final RequestSpecification invalidRequestSpec;

    public AbstractBaseRestClient(String url) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .log().all();

        invalidRequestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", KEY)
                .queryParam("token", INVALID_TOKEN)
                .log().all();

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }

    }
}