package api.clients;

import api.dto.AttachmentDataResponse;
import api.dto.CardDataResponse;
import api.dto.CreateLabelResponse;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class CardTestRestClient extends AbstractBaseRestClient {
    public CardTestRestClient(String url) {
        super(url);
    }

    @Step("Creating a new card by list id - {idList}, with parameters - {listKeyValue}")
    public CardDataResponse createCard(Map<String, String> listKeyValue, String idList) {
        return given()
                .spec(requestSpec)
                .queryParams(listKeyValue)
                .when()
                .post("/1/cards?idList={id}", idList)
                .then()
                .statusCode(HTTP_OK)
                .extract().as(CardDataResponse.class);
    }

    @Step("Creating a new card by list id - {idList}, with parameters - {listKeyValue}")
    public AttachmentDataResponse createAttachmentOnCard(Map<String, String> attachmentKeyValue, String idCard) {
        return given()
                .spec(requestSpec)
                .queryParams(attachmentKeyValue)
                .when()
                .post("/1/cards/{id}/attachments", idCard)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(AttachmentDataResponse.class);
    }

    public ValidatableResponse deleteCardIfExist(String cardId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/cards/{id}", cardId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }

    public Response getCardInfoByCardId(String cardId) {
        return given()
                .spec(requestSpec)
                .get("/1/cards/{id}", cardId);
    }

    public static Map<String, String> constructDefaultCardKeyValue() {
        return Map.of("name", "Test card " + RandomStringUtils.randomAlphanumeric(2));
    }

    public static Map<String, String> constructAttachmentKeyValue(String name, String mimeType, String url, String setCover) {
        return Map.of("name",name,"mimeType",mimeType,"url",url, "setCover", setCover);
    }
}