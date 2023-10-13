package api.clients;

import api.dto.CreateChecklistResponse;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ChecklistTestRestClient extends AbstractBaseRestClient {

    public ChecklistTestRestClient(String url) {
        super(url);
    }

    public CreateChecklistResponse createChecklist( String cardId ) {
        return given()
                .spec(requestSpec)
                .queryParam("idCard",cardId)
                .when()
                .post("/1/checklist")
                .then()
                .statusCode(HTTP_OK)
                .extract().as(CreateChecklistResponse.class);
    }

    public JsonPath deleteChecklist(String checklistId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/checklists/{id}", checklistId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)))
                .extract().jsonPath();
    }
}
