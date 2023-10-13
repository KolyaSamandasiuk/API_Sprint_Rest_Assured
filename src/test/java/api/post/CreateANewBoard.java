package api.post;

import api.BaseTest;
import api.clients.BoardRestTestClient;
import api.dto.CreateBoardResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.Const.BASE_URL;

public class CreateANewBoard extends BaseTest {
    private String ID_BOARD;

    @Test
    public void createBoardTest() {

        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", "Test board");

        CreateBoardResponse response = boardRestTestClient.createNewBoard(boardParams);
        ID_BOARD = response.getId();

        Assert.assertNotNull(response, "Board creation failed");
        Assert.assertNotNull(response.getId(), "Board ID is null");
        Assert.assertEquals(response.getName(), "Test board", "Board name doesn't match");
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(ID_BOARD);
    }
}
