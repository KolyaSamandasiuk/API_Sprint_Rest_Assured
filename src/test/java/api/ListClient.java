package api;

import api.POJO.CreateListResponse;
import api.POJO.ListsDataResponse;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ListClient extends BaseRestClient{

    public ListClient(String url) {
        super(url);
    }

    public CreateListResponse createList(String ListName, String Board_id) {
        return given()
                .spec(requestSpec)
                .queryParam("name", ListName)
                .when()
                .post("/1/boards/{id}/lists", Board_id)
                .then().log().all()
                .extract().as(CreateListResponse.class);
    }

    public List<ListsDataResponse> getLists(String Board_id) {
        return given()
                .spec(requestSpec)
                .get("/1/boards/{id}/lists", Board_id)
                .then()
                .extract()
                .as(new TypeRef<List<ListsDataResponse>>() {
                });
    }
}
