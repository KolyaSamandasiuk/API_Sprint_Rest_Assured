package api.clients;

import api.dto.CreateLabelResponse;
import api.dto.CreateListResponse;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

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

        public Response createLabelResponse (Map < String, String > labelKeyValue,
        int statusCode){
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

        public List<CreateLabelResponse> getLabelOnABoard (String boardId){
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
    }