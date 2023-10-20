package api.clients;

import api.dto.EmojiDataResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class EmojiRestTestClient extends AbstractBaseRestClient {
    public EmojiRestTestClient(String url) {
        super(url);
    }

    @Step("Extracting all information about all emoji")
    public List<EmojiDataResponse> getEmoji() {
        return given()
                .contentType(ContentType.JSON)
                .spec(requestSpec)
                .get("/1/emoji")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .extract().body().jsonPath().getList("trello", EmojiDataResponse.class);
    }
}
