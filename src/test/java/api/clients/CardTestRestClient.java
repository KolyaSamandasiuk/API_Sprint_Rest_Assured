package api.clients;

import api.dto.CardDataResponse;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;


public class CardTestRestClient extends AbstractBaseRestClient {

    public CardTestRestClient(String url) {
        super(url);
    }

    public CardDataResponse createCard(String listId) {
        return given()
                .spec(requestSpec)
                .queryParam("idList", listId)
                .when()
                .post("/1/cards")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(CardDataResponse.class);
    }

    public ValidatableResponse deleteCart(String listId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/cards/{id}", listId)
                .then()
                .statusCode(HTTP_OK);
    }
}