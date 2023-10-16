package api.delete;

import api.BaseTest;
import api.dto.OrganizationDataResponse;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class DeleteAnOrganizationTest extends BaseTest {
    private String ID_ORGANIZATION;

    @Test
    public void deleteOrganizationTest() {

        String organizationName = "Test21";
        OrganizationDataResponse response = organizationRestTestClient.createNewOrganization(organizationName);
        ID_ORGANIZATION = response.getId();

        ValidatableResponse deleteResponse = organizationRestTestClient.deleteOrganizationIfExist(ID_ORGANIZATION);

        deleteResponse.assertThat().statusCode(200);
    }
}
