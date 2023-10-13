package api.clients;

import api.dto.BoardDataResponse;
import api.dto.CreateBoardResponse;
import api.dto.ListsDataResponse;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BoardRestTestClient extends AbstractBaseRestClient {

    public BoardRestTestClient(String url) {
        super(url);
    }

    public CreateBoardResponse createNewBoard(Map<String, String> createBoardKeyValue) {
        return given()
                .spec(requestSpec)
                .queryParams(createBoardKeyValue)
                .when()
                .post("/1/boards/")
                .then()
                .statusCode(HTTP_OK)
                .extract().as(CreateBoardResponse.class);
    }

    public ValidatableResponse deleteBoardIfExist(String boardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }

    @Step("Extracting information from the board by id: {0}")
    public BoardDataResponse getBoardById(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}", boardId)
                .then()
                .extract().as(BoardDataResponse.class);
    }
}