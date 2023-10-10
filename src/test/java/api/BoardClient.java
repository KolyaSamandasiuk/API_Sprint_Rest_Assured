package api;

import api.POJO.CreateBoardResponse;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BoardClient extends BaseRestClient {

    public BoardClient(String url) {
        super(url);
    }

    public  CreateBoardResponse createNewBoard() {
        return given()
                .spec(requestSpec)
                .queryParam("name", "BoardNameTest")
                .when()
                .post("/1/boards/")
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_BAD_REQUEST)))
                .extract().as(CreateBoardResponse.class);
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
}
