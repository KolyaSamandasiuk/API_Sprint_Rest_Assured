package api.clients;

import api.dto.ChecklistDataResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ChecklistRestTestClient extends AbstractBaseRestClient{
    public ChecklistRestTestClient(String url) {
        super(url);
    }

    @Step("Creating a new checklist by card id: {cardId} with parameters: {checklistKeyValue}")
    public ChecklistDataResponse createChecklist(Map<String, String> checklistKeyValue, String cardId) {
        return given()
                .spec(requestSpec)
                .queryParams(checklistKeyValue)
                .when()
                .post("/1/checklists?idCard={cardId}",cardId)
                .then()
                .statusCode(HTTP_OK)
                .extract().as(ChecklistDataResponse.class);
    }

    @Step("Delete the test checklist by id: {0}")
    public ValidatableResponse deleteChecklistIfExist(String checklistId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/checklists/{id}", checklistId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }

    @Step("Trying to find the checklist by id: {checklistId}")
    public Response tryGetChecklistById(String checklistId) {
        return given()
                .spec(requestSpec)
                .get("/1/checklists/{id}", checklistId);
    }

    public static Map<String, String> constructDefaultChecklistKeyValue() {
        return Map.of("name", "Test checklist " + RandomStringUtils.randomAlphanumeric(2));
    }
}
