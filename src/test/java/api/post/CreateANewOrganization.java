package api.post;

import api.BaseTest;
import api.dto.OrganizationDataResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateANewOrganization extends BaseTest {

    private String ID_ORGANIZATION;

    @Test
    public void createOrganizationTest() {

        String organizationName = "Test21";
        OrganizationDataResponse response = organizationRestTestClient.createNewOrganization(organizationName);
        ID_ORGANIZATION = response.getId();

        Assert.assertNotNull(response.getId(), "Organization ID is null");
    }

    @AfterMethod
    public void deleteOrganization() {
        organizationRestTestClient.deleteOrganizationIfExist(ID_ORGANIZATION);
    }

}
