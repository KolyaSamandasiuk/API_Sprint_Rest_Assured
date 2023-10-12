package api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.clients.ConfigProvider.KEY;
import static api.clients.ConfigProvider.TOKEN;
import static io.restassured.RestAssured.given;

public abstract class AbstractBaseRestClient {
    protected final RequestSpecification requestSpec;

    public AbstractBaseRestClient(String url) {

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }
}

