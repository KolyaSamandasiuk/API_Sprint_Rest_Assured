package api.clients;

import api.dto.CardDataResponse;
import api.dto.CreateListResponse;
import api.dto.ListsDataResponse;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class ListTestRestClient extends AbstractBaseRestClient {

    public ListTestRestClient(String url) {
        super(url);
    }

    @Step("Create list on Board , with parameters: {listKeyValue},  boardId ")
    public CreateListResponse createList(Map<String, String> listKeyValue, String boardId) {
        return createList(listKeyValue, boardId, HTTP_OK)
                .as(CreateListResponse.class);
    }

    @Step("Creating new list by board id - {boardId}, with parameters - {listKeyValue}")
    public Response createList(Map<String, String> listKeyValue, String boardId, int statusCode) {
        return given()
                .spec(requestSpec)
                .queryParams(listKeyValue)
                .when()
                .post("/1/boards/{id}/lists", boardId)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    @Step("Get board lists by id: {boardId}")
    public List<ListsDataResponse> getLists(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/lists", boardId)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(new TypeRef<List<ListsDataResponse>>() {
                });
    }

    @Step("Move list with id: {listId} to board with id: {boardId}")
    public ListsDataResponse moveList(String listId, String boardId, int statusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .put("/1/lists/{listId}/idBoard?value={boardId}", listId, boardId)
                .then()
                .statusCode(statusCode)
                .extract()
                .as(ListsDataResponse.class);
    }

    @Step("Update list information with id: {idList}. Transfer the query parameters using listKeyValue: {listKeyValue} ")
    public ListsDataResponse updateListFromBoard(Map<String, String> listKeyValue, String idList, int statusCode) {
        return given()
                .spec(requestSpec)
                .queryParams(listKeyValue)
                .when()
                .put("/1/lists/{id}", idList)
                .then()
                .statusCode(statusCode)
                .extract()
                .as(ListsDataResponse.class);
    }

    @Step("Try to move list with invalid id: {listId} to board with id: {boardId}")
    public Response tryToMoveList(String listId, String boardId, int statusCode) {
        return given()
                .spec(requestSpec)
                .when()
                .put("/1/lists/{listId}/idBoard?value={boardId}", listId, boardId)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    @Step("Moving all cards from list by id: {listId} and with parameters: {moveCardsInListKeyValue}")
    public List<CardDataResponse> moveAllCardsInList(Map<String, String> moveCardsInListKeyValue, String listId) {
        return given()
                .spec(requestSpec)
                .queryParams(moveCardsInListKeyValue)
                .when()
                .post("/1/lists/{id}/moveAllCards", listId)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(new TypeRef<List<CardDataResponse>>() {
                });
    }

    public static Map<String, String> constructDefaultListKeyValue() {
        return Map.of("name", "Test list " + RandomStringUtils.randomAlphanumeric(3));
    }

    public static Map<String, String> constructMoveAllCardsToListKeyValue(String boardId, String listId) {
        return Map.of("idBoard", boardId, "idList", listId);
    }

    public static Map<String, String> constructPutListKeyValue(String name) {
        return Map.of("name", name + " " + RandomStringUtils.randomAlphanumeric(3));
    }
}