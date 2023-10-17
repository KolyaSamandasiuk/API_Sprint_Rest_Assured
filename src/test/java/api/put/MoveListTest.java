package api.put;

import api.BaseTest;
import api.dto.ListsDataResponse;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoveListTest extends BaseTest {

    private String firstBoardID;
    private String secondBoardId;
    private String idList;

    @BeforeMethod
    public void createBoardsAndList(){
        firstBoardID = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), firstBoardID).getId();
        secondBoardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test(description = "AS2-29")
    @Description("Positive: Move List to Board")
    public void MoveList(){
        ListsDataResponse response = listTestRestClient.moveList(idList,secondBoardId);

        Assert.assertEquals(response.getIdBoard(), secondBoardId);
    }

    @AfterMethod
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(firstBoardID);
        boardRestTestClient.deleteBoardIfExist(secondBoardId);
    }

}
