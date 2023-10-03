package api.POST;

import api.POJO.SuccessfulRegistrationData;
import api.POJO.UserForRegistrationData;
import api.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.Const.*;
import static io.restassured.RestAssured.given;

public class RegistrationNewUserTest {

    @Test
    public void checkRegistration(){

        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationOK200());
        UserForRegistrationData userForRegistrationData = new UserForRegistrationData("eve.holt@reqres.in", "pistol");

        SuccessfulRegistrationData successfulRegistrationData =
        given()
                .body(userForRegistrationData)
        .when()
                .post("/api/register")
        .then()
                .log().all()
                .extract().as(SuccessfulRegistrationData.class);

        Assert.assertNotNull(successfulRegistrationData.getId());
        Assert.assertNotNull(successfulRegistrationData.getToken());
        Assert.assertEquals(successfulRegistrationData.getId(), expectedID);
        Assert.assertEquals(successfulRegistrationData.getToken(), expectedToken);

    }
}
