package api.clients;

import api.dto.CreateBoardResponse;
import io.restassured.path.json.JsonPath;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BoardClient extends AbstractBaseRestClient {

    public BoardClient(String url) {
        super(url);
    }

    public  CreateBoardResponse createNewBoard(Map<String, String> stringMap) {
        return given()
                .spec(requestSpec)
                .queryParams(stringMap)
                .when()
                .post("/1/boards/")
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)))
                .extract().as(CreateBoardResponse.class);
    }

    public JsonPath deleteBoardIfExist(String boardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)))
                .extract().jsonPath();
    }
}