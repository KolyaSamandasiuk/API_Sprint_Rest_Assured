package api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseRestClient {

    private static final String API_KEY = "dc684dd9a09e920bab27127fae9fb11f";
    private static final String API_TOKEN = "ATTA55705559daf95dcfa0685ffa8a77777089ec6190685c4e458064fb6d89c092a17E3F016E";
    protected final RequestSpecification requestSpec;
    private final static Map<String, String> keyAndToken = Map.of("key", API_KEY, "token", API_TOKEN);

    public BaseRestClient(String url) {

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParams(keyAndToken);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}

