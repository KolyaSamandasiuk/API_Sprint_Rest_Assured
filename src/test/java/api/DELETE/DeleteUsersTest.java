package api.DELETE;

import api.Specifications;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.Const.BASE_URL;
import static io.restassured.RestAssured.given;

public class  DeleteUsersTest {

    @Test
    public void checkDeleteMethod(){
        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationUnique(204));

        Response response = given()
                .when()
                .delete("/api/users/2")
                .then().log().all().extract().response();
    }
}
