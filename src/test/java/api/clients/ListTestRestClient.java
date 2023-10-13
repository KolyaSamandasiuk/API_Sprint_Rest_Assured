package api.clients;

import api.dto.CreateListResponse;
import api.dto.ListsDataResponse;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class ListTestRestClient extends AbstractBaseRestClient {

    public ListTestRestClient(String url) {
        super(url);
    }

    public CreateListResponse createList(String listName, String boardId) {
        return given()
                .spec(requestSpec)
                .queryParam("name", listName)
                .when()
                .post("/1/boards/{id}/lists", boardId)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(CreateListResponse.class);
    }

    public List<ListsDataResponse> getLists(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/lists", boardId)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(new TypeRef<List<ListsDataResponse>>() {
                });
    }
}
