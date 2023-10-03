package api.GET;

import api.POJO.UserData;
import api.Specifications;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;


import static api.Const.BASE_URL;
import static io.restassured.RestAssured.given;

public class ListUsersTest {

    @Test
    public void checkUsersEmail() {
        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationOK200());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(elementOfList -> Assert.assertTrue(elementOfList.getEmail().endsWith("@reqres.in")));
        //   users.forEach(elementOfList -> Assert.assertTrue(elementOfList.getAvatar().contains(elementOfList.getId().toString())));
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(element -> element.getId().toString()).collect(Collectors.toList());

        Assert.assertTrue(ids.size() == 6 && avatars.size() == 6 && users.size() == 6);
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
}

