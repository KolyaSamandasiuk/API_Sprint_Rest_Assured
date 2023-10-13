package api.clients;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ActionsRestTestClient extends AbstractBaseRestClient{

    public ActionsRestTestClient(String url) {
        super(url);
    }

    public ValidatableResponse getActionOfBoard(String boardId) {
        return given()
                .spec(requestSpec)
                .when()
                .log().all()
                .get("/1/boards/{boardId}/actions",boardId)
                .then();
    }
}