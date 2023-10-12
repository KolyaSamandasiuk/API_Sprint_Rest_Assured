package api.clients;

import api.dto.CreateListResponse;
import api.dto.ListsDataResponse;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ListClient extends AbstractBaseRestClient {

    public ListClient(String url) {
        super(url);
    }

    public CreateListResponse createList(String listName, String boardId) {
        return given()
                .spec(requestSpec)
                .queryParams("name", listName)
                .when()
                .post("/1/boards/{id}/lists", boardId)
                .then().log().all()
                .extract().as(CreateListResponse.class);
    }

    public List<ListsDataResponse> getLists(String boardId) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/lists", boardId)
                .then()
                .extract()
                .as(new TypeRef<List<ListsDataResponse>>() {
                });
    }
}
