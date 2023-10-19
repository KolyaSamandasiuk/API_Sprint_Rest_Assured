package api.clients;

import api.dto.BoardDataResponse;
import api.dto.CreateBoardResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BoardRestTestClient extends AbstractBaseRestClient {

    public BoardRestTestClient(String url) {
        super(url);
    }

    @Step("Creating a test board with parameters: {createBoardKeyValue}")
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

    @Step("Trying to create a board with parameters {createBoardKeyValue} and status code: {statusCode}")
    public Response tryToCreateABoard(Map<String, String> createBoardKeyValue, int statusCode) {
        return given()
                .spec(requestSpec)
                .queryParams(createBoardKeyValue)
                .when()
                .post("/1/boards/")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    @Step("Delete the test board by id: {0}")
    public ValidatableResponse deleteBoardIfExist(String boardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_BAD_REQUEST)));
    }

    public Response tryToDeleteBoardIfExist(String boardId, int statusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/boards/{id}", boardId)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    @Step("Extracting information from the board by id: {boardId}")
    public BoardDataResponse getBoardById(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}", boardId)
                .then()
                .extract().as(BoardDataResponse.class);
    }

    @Step("Get boardId with parameter: String boardId")
    public Response getBoardId(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}", boardId);
    }

    @Step("New parameters are assigned: id - {0}, parameters - {1}")
    public BoardDataResponse putBoardInfo(String boardId, Map<String, String> infoToBoard) {
        return given()
                .spec(requestSpec)
                .body(infoToBoard)
                .when()
                .put("/1/boards/{id}", boardId)
                .then()
                .statusCode(HTTP_OK)
                .extract().as(BoardDataResponse.class);
    }

    public static Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }
}