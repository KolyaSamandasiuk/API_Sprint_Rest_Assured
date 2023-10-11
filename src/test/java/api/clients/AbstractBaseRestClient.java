package api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Map;

import static api.clients.ConfigProvider.*;
import static io.restassured.RestAssured.given;

public abstract class AbstractBaseRestClient {
    protected final RequestSpecification requestSpec;
    private final static Map<String, String> KEYANDTOKEN = Map.of("key",KEY,"token", TOKEN);

    public AbstractBaseRestClient(String url) {

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParams(KEYANDTOKEN);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}

