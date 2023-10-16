package api.clients;

import api.dto.CardDataResponse;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class CardTestRestClient extends AbstractBaseRestClient {

    public CardTestRestClient(String url) {
        super(url);
    }

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

    public ValidatableResponse deleteCardIfExist(String cardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/cards/{id}", cardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }

    public static Map<String, String> constructDefaultListKeyValue() {
        return Map.of("name", "Test list " + RandomStringUtils.randomAlphanumeric(2));
    }
}