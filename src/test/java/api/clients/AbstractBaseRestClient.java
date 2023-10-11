package api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class AbstractBaseRestClient {
    protected final RequestSpecification requestSpec;
    private final static Map<String, String> KEY_AND_TOKEN = Map.of("key", System.getProperty("apiKey"),
            "token", System.getProperty("apiToken"));

    public AbstractBaseRestClient(String url) {

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParams(KEY_AND_TOKEN);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}

