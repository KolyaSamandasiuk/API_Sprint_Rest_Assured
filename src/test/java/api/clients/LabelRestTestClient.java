package api.clients;

import api.dto.CreateLabelResponse;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class LabelRestTestClient extends AbstractBaseRestClient {

    public LabelRestTestClient(String url) {
        super(url);
    }

    public List<CreateLabelResponse> getLabelOnABoard(String boardId) {
        return given()
                .spec(requestSpec)
                .when()
                .log().all()
                .get("/1/boards/{id}/labels", boardId)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(new TypeRef<List<CreateLabelResponse>>() {
                });
    }
}