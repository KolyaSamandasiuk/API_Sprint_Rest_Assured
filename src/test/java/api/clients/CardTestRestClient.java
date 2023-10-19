package api.clients;

import api.dto.AttachmentDataResponse;
import api.dto.CardDataResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class CardTestRestClient extends AbstractBaseRestClient {
    public CardTestRestClient(String url) {
        super(url);
    }

    @Step("Creating a new card by list id - {idList}, with parameters - {listKeyValue}")
    public CardDataResponse createCard(Map<String, String> listKeyValue, String idList) {
        return given()
                .spec(requestSpec)
                .queryParams(listKeyValue)
                .when()
                .post("/1/cards?idList={id}", idList)
                .then()
                .statusCode(HTTP_OK)
                .extract().as(CardDataResponse.class);
    }

    @Step("Creating a new attachment by card id - {idCard}, with parameters - {attachmentKeyValue}")
    public AttachmentDataResponse createAttachmentOnCard(Map<String, String> attachmentKeyValue, String idCard) {
        return given()
                .spec(requestSpec)
                .queryParams(attachmentKeyValue)
                .when()
                .post("/1/cards/{id}/attachments", idCard)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(AttachmentDataResponse.class);
    }

    @Step("Try create attachment by card id({idCard}). Expected status code: {statusCode}. Parameters - {attachmentKeyValue}")
    public Response tryCreateAttachmentOnCard(Map<String, String> attachmentKeyValue, String idCard, int statusCode) {
        return given()
                .spec(requestSpec)
                .queryParams(attachmentKeyValue)
                .when()
                .post("/1/cards/{id}/attachments", idCard)
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    @Step("Delete the test card by id: {0}")
    public ValidatableResponse deleteCardIfExist(String cardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/cards/{id}", cardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }

    public Response getCardId(String cardId) {
        return given()
                .spec(requestSpec)
                .get("/1/cards/{id}", cardId);
    }

    public CardDataResponse getCardInfoById(String cardId) {
        return given()
                .spec(requestSpec)
                .get("/1/cards/{id}", cardId)
                .then()
                .extract().as(CardDataResponse.class);
    }

    public static Map<String, String> constructDefaultCardKeyValueWithDesc(String nameValue, String descValue) {
        return Map.of("name", nameValue, "desc", descValue);
    }

    @Step("Add new comment to card by id: {idCard} with query params: {commentKeyValue}")
    public Response addNewCommentToCard(Map<String, String> commentKeyValue, String idCard) {
        return given()
                .spec(requestSpec)
                .queryParams(commentKeyValue)
                .post("/1/cards/{id}/actions/comments", idCard)
                .then()
                .statusCode(HTTP_OK)
                .extract().response();
    }

    public static Map<String, String> constructDefaultCardKeyValue() {
        return Map.of("name", "Test card " + RandomStringUtils.randomAlphanumeric(3));
    }

    public static Map<String, String> constructDefaultCommentKeyValue() {
        return Map.of("text", "Test comment");
    }

    public static Map<String, String> constructAttachmentKeyValue(String name, String mimeType, String url, String setCover) {
        return Map.of("name", name, "mimeType", mimeType, "url", url, "setCover", setCover);
    }
}