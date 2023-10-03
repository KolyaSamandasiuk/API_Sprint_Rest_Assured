package api.GET;

import api.POJO.ResourseData;
import api.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static api.Const.BASE_URL;
import static io.restassured.RestAssured.given;

public class CheckDataObjectsTest {

    @Test
    public void checkFirstAndSecondObjects() {

        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationUnique(200));

        List<ResourseData> resourseData =
                given()
                        .when()
                        .get("/api/unknown")
                        .then().log().all().extract().jsonPath().getList("data", ResourseData.class);

        Assert.assertEquals(resourseData.get(0).getId(), 1);
        Assert.assertEquals(resourseData.get(0).getName(), "cerulean");
        Assert.assertEquals(resourseData.get(0).getYear(),  2000);
        Assert.assertEquals(resourseData.get(0).getColor(), "#98B2D1");
        Assert.assertEquals(resourseData.get(0).getPantone_value(), "15-4020");

        Assert.assertEquals(resourseData.get(1).getId(), 2);
        Assert.assertEquals(resourseData.get(1).getName(), "fuchsia rose");
        Assert.assertEquals(resourseData.get(1).getYear(),  2001);
        Assert.assertEquals(resourseData.get(1).getColor(), "#C74375");
        Assert.assertEquals(resourseData.get(1).getPantone_value(), "17-2031");

    }
}
