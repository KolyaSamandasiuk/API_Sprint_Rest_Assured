package api.clients;

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

    private static String NAME_VALUE = "Test card";
    private static String DESC_VALUE = "Description of Test card";

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

    public CardDataResponse getCardById(String cardId) {
        return given()
                .spec(requestSpec)
                .get("/1/cards/{id}", cardId)
                .then()
                .extract().as(CardDataResponse.class);
    }

    public static Map<String, String> constructDefaultCardKeyValueWithDesc() {
        return Map.of("name", NAME_VALUE, "desc", DESC_VALUE);
    }

    public static Map<String, String> constructDefaultCardKeyValue() {
        return Map.of("name", "Test card " + RandomStringUtils.randomAlphanumeric(2));
    }
}