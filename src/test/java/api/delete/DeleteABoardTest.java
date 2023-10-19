package api.delete;

import api.BaseTest;
import api.dto.CreateBoardResponse;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class DeleteABoardTest extends BaseTest {

    private String idCard;

    @Test(description = "AS2-10")
    @Description("Positive: Delete a Board ")
    public void deleteBoardTest() {
        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", "Test board");

        CreateBoardResponse response = boardRestTestClient.createNewBoard(boardParams);
        idCard = response.getId();

        boardRestTestClient.deleteBoardIfExist(idCard);

        String boardMessage = boardRestTestClient
                .getBoardId(idCard)
                .body()
                .asString();

        Assert.assertEquals(boardMessage, "The requested resource was not found.");
    }

    @Test
    public void deleteBoardWithInvalidID() {

        Response response = boardRestTestClient.tryToDeleteBoardIfExist("0025585647850200205", HTTP_BAD_REQUEST);

        Assert.assertEquals(response.getBody().asString(), "invalid id");
    }
}