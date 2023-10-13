package api.clients;

import api.dto.ChecklistDataResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;


public class ChecklistTestRestClient extends AbstractBaseRestClient {

    public ChecklistTestRestClient(String url) {
        super(url);
    }

    public ChecklistDataResponse createChecklist(String cardId) {
        return given()
                .spec(requestSpec)
                .queryParams("idCard", cardId)
                .when()
                .post("/1/checklists")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(ChecklistDataResponse.class);
    }

    public ValidatableResponse deleteChecklist(String checklistId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/checklists/{id}", checklistId)
                .then()
                .statusCode(HTTP_OK);
    }

    public List<ChecklistDataResponse> getChecklist(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/checklists", boardId)
                .then()
                .log()
                .all()
                .extract()
                .as(new TypeRef<List<ChecklistDataResponse>>() {
                });
    }

    public ResponseBody getChecklistAfterDelete(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/checklists", boardId)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .response()
                .body();
    }
}