package api.clients;

import api.dto.CreateCardResponse;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class CardClient extends AbstractBaseRestClient{
    public CardClient(String url) {
        super(url);
    }
    public CreateCardResponse createCard(String listId ) {
        return given()
                .spec(requestSpec)
                .queryParam("idList",listId)
                .when()
                .post("/1/cards")
                .then()
                .statusCode(HTTP_OK)
                .log().all()
                .extract().as(CreateCardResponse.class);
    }
}
