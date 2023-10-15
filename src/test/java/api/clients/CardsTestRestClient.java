package api.clients;

import api.dto.CommentDto;
import api.dto.CreateCardDataResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class CardsTestRestClient extends AbstractBaseRestClient {
    public CardsTestRestClient(String url) {
        super(url);
    }

    public CreateCardDataResponse createCard(String name, String idList) {
        return given()
                .spec(requestSpec)
                .queryParam("name", name)
                .queryParam("idList", idList)
                .when()
                .post("/1/cards")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(CreateCardDataResponse.class);
    }

    public CommentDto addNewCommentToCard(Map<String, String> commentKeyValue, String idCard) {
        return given()
                .spec(requestSpec)
                .queryParams(commentKeyValue)
                .post("/1/cards/{id}/actions/comments", idCard)
                .then()
                .statusCode(HTTP_OK)
                .extract().as(CommentDto.class);
    }
}
