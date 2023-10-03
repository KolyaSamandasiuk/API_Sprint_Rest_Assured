package api.PUT;

import api.POJO.SuccessUpdateUserData;
import api.POJO.UpdateUserData;
import api.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static api.Const.*;
import static io.restassured.RestAssured.given;

public class UpdateUserTest {

    @Test
    public void checkUpdateUser(){
        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationUnique(200));
        UpdateUserData updateUserData = new UpdateUserData("morpheus", "zion resident");

        SuccessUpdateUserData successUpdateUserData =
        given()
                .body(updateUserData)
                .when()
                .put("/api/users/2")
                .then().log().all().extract().as(SuccessUpdateUserData.class);

        ZoneId zoneId = ZoneId.of("+00:00");
        LocalDateTime currentDateTime = LocalDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);

        Assert.assertTrue(successUpdateUserData.getUpdatedAt().contains(formattedDateTime));
        Assert.assertEquals(successUpdateUserData.getName(), expectedName);
        Assert.assertEquals(successUpdateUserData.getJob(), expectedJob);
    }
}
