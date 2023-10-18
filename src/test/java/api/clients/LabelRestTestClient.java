package api.clients;

import api.dto.BoardDataResponse;
import api.dto.CreateLabelResponse;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class LabelRestTestClient extends AbstractBaseRestClient {

    public LabelRestTestClient(String url) {
        super(url);
    }

    public CreateLabelResponse createLabel(Map<String, String> labelKeyValue) {
        return createLabelResponse(labelKeyValue, HTTP_OK)
                .as(CreateLabelResponse.class);
    }

    @Step("Creating a new label")
    public Response createLabelResponse(Map<String, String> labelKeyValue, int statusCode) {
        return given()
                .queryParams(labelKeyValue)
                .spec(requestSpec)
                .when()
                .post("1/labels")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    @Step("Extracting information about all labels by board id: {boardId}")
    public List<CreateLabelResponse> getLabelsOnABoard(String boardId) {
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

    @Step("Extracting information about label by label id: {labelId}")
    public CreateLabelResponse getLabel(String labelId) {
        return given()
                .spec(requestSpec)
                .when()
                .log().all()
                .get("/1/labels/{id}", labelId)
                .then()
                .extract().as(CreateLabelResponse.class);
    }

    @Step("Delete a label by id: {labelId}")
    public ValidatableResponse deleteLabel(String labelId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/labels/{id}", labelId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }

    @Step("Trying to find the label by id: {checklabelId}")
    public Response tryGetLabelById(String checklabelId) {
        return given()
                .spec(requestSpec)
                .get("/1/labels/{id}", checklabelId);
    }

    @Step("Assigning parameters {1} to a label with an id: {0}")
    public CreateLabelResponse putLabelInfo(String labelId, Map<String, String> infoToLabel) {
        return given()
                .spec(requestSpec)
                .body(infoToLabel)
                .when()
                .put("/1/labels/{id}", labelId)
                .then()
                .statusCode(HTTP_OK)
                .extract().as(CreateLabelResponse.class);
    }

    public static Map<String, String> constructDefaultLabelKeyValue(String labelName, String labelColor, String idBoard) {
        return Map.of("name", labelName, "color", labelColor, "idBoard", idBoard);
    }
}