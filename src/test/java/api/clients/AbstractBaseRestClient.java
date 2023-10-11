package api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class AbstractBaseRestClient {

    private static final String API_KEY = "7f07eabdc97e9555e6cfc283a3a3fb9e";
    private static final String API_TOKEN = "ATTA1418663ae4e3999c60e6d04258e61619af886960da297bf6709f56d1e631ff1084784FA0";
    protected final RequestSpecification requestSpec;
    private final static Map<String, String> KEYANDTOKEN = Map.of("key", API_KEY, "token", API_TOKEN);

    public AbstractBaseRestClient(String url) {

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParams(KEYANDTOKEN);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}

