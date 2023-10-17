package api.clients;

import api.dto.CreateLabelResponse;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

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

    public static Map<String, String> constructDefaultLabelKeyValue(String labelName, String labelColor, String idBoard) {
        return Map.of("name", labelName, "color", labelColor, "idBoard", idBoard);
    }
}