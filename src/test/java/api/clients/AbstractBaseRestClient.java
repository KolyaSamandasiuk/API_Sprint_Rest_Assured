package api.clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class AbstractBaseRestClient {

    public static final String API_KEY = "fec02878441280194eb9dd15bacbf7e1";
    //private static final String API_KEY = "dc684dd9a09e920bab27127fae9fb11f";
    public static final String API_TOKEN = "ATTA3721405d3957bd7b11db71930f42d036b0273a1ec92a791c2ffae446c6f520638DB70AA4";
    //private static final String API_TOKEN = "ATTA55705559daf95dcfa0685ffa8a77777089ec6190685c4e458064fb6d89c092a17E3F016E";
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