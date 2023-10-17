package api.clients;

import api.dto.CardDataResponse;
import api.dto.ChecklistDataResponse;
import api.dto.CreateBoardResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

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

    public static Map<String, String> constructDefaultChecklistKeyValue() {
        return Map.of("name", "Test checklist " + RandomStringUtils.randomAlphanumeric(2));
    }
}
