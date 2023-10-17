package api.delete;

import api.BaseTest;
import api.dto.CreateBoardResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

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
}