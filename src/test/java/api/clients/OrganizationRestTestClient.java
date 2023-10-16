package api.clients;

import api.dto.OrganizationDataResponse;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;

public class OrganizationRestTestClient extends AbstractBaseRestClient {
    public OrganizationRestTestClient(String url) {
        super(url);
    }

    public OrganizationDataResponse createNewOrganization(String organizationName) {
        Map<String, String> organizationParams = new HashMap<>();
        organizationParams.put("displayName", organizationName);

        return given()
                .spec(requestSpec)
                .queryParams(organizationParams)
                .when()
                .post("/1/organizations")
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .as(OrganizationDataResponse.class);
    }



    public ValidatableResponse deleteOrganizationIfExist(String organizationId) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/1/organizations/{id}", organizationId)
                .then()
                .statusCode(anyOf(is(HTTP_OK), is(HTTP_NOT_FOUND)));
    }


    public OrganizationDataResponse getOrganizationById(String organizationId) {
        return given()
                .spec(requestSpec)
                .get("/1/organizations/{id}", organizationId)
                .then()
                .extract()
                .as(OrganizationDataResponse.class);
    }

}
