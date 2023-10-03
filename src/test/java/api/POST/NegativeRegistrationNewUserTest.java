package api.POST;

import api.POJO.UnSuccessfullRegistrationData;
import api.POJO.UserForRegistrationData;
import api.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.Const.BASE_URL;
import static api.Const.expectedErrorMessage;
import static io.restassured.RestAssured.given;

public class NegativeRegistrationNewUserTest {

    @Test
    public void checkNegativeRegistration(){

        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationNotOK400());

        UserForRegistrationData user = new UserForRegistrationData("sydney@fife", "");

        UnSuccessfullRegistrationData unSuccess =
                given()
                        .body(user)
                .when()
                        .post("/api/register")
                .then()
                        .log().all()
                        .extract().as(UnSuccessfullRegistrationData.class);

        Assert.assertNotNull(unSuccess.getError());
        Assert.assertEquals(unSuccess.getError(), expectedErrorMessage);
    }
}
