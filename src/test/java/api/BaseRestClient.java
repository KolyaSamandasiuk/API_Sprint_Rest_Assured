package api;

import api.POJO.CreateListResponse;
import api.POJO.ListsDataResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseRestClient {

    private static final String API_KEY = "dc684dd9a09e920bab27127fae9fb11f";
    private static final String API_TOKEN = "ATTA55705559daf95dcfa0685ffa8a77777089ec6190685c4e458064fb6d89c092a17E3F016E";
    protected final RequestSpecification requestSpec;
    private final static Map<String, String> keyAndToken = Map.of("key", API_KEY, "token", API_TOKEN);

    public BaseRestClient(String url) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        requestSpec = given().baseUri(url)
                .contentType(ContentType.JSON)
                .queryParams(keyAndToken);

        if (url.matches("^(https)://.*$")) {
            requestSpec.relaxedHTTPSValidation();
        }
    }

    public JsonPath createNewBoard() {
        JsonPath jsonResponse = given()
                .spec(requestSpec)
                .queryParam("name", "BoardNameTest")
                .when()
                .post("/1/boards/")
                .then()
                .statusCode(HTTP_OK)
                .extract().jsonPath();
        return jsonResponse;
    }

    public JsonPath deleteBoard(String BOARD_ID) {
        JsonPath jsonResponse = given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", BOARD_ID)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_BAD_REQUEST)))
                .extract().jsonPath();
        return jsonResponse;
    }

    public CreateListResponse createList(String ListName, String Board_id) {
        return given()
                .spec(requestSpec)
                .queryParam("name", ListName)
                .when()
                .post("/1/boards/{id}/lists", Board_id)
                .then().log().all()
                .extract().as(CreateListResponse.class);
    }

    public List<ListsDataResponse> getLists(String Board_id) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/lists", Board_id)
                .then()
                .extract()
                .as(new TypeRef<List<ListsDataResponse>>() {
                });
    }
}

