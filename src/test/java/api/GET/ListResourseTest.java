package api.GET;

import api.POJO.ResourseData;
import api.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.stream.Collectors;

import static api.Const.BASE_URL;
import static io.restassured.RestAssured.given;

public class ListResourseTest {

    @Test
    public void checkYearsInResourseList() {

        Specifications.installSpecification(Specifications.requestSpecification(BASE_URL), Specifications.responseSpecificationOK200());

        List<ResourseData> dataList =
                given()
                        .when()
                        .get("/api/unknown")
                        .then().log().all()
                        .extract().body().jsonPath().getList("data", ResourseData.class);

        List<Integer> years = dataList.stream().map(ResourseData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();

        Assert.assertEquals(years, sortedYears);
    }
}
