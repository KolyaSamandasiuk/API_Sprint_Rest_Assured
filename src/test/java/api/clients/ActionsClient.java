package api.clients;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ActionsClient extends AbstractBaseRestClient{

    public ActionsClient(String url) {
        super(url);
    }

    public ValidatableResponse getJsonResponse(String apiKey, String boardId) {
        return given()
                .spec(requestSpec)
                .queryParam("key", apiKey)
                .queryParam("token", apiKey)
                .when()
                .log().all()
                .get("/1/boards/{boardId}/actions",boardId)
                .then();
    }
}