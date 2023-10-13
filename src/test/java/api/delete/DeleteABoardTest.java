package api.delete;

import api.BaseTest;
import api.clients.BoardRestTestClient;
import api.dto.CreateBoardResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.Const.BASE_URL;

public class DeleteABoardTest extends BaseTest {

    private BoardRestTestClient boardRestTestClient = new BoardRestTestClient(BASE_URL);
    private String ID_BOARD;

    @Test
    public void deleteBoardTest() {
        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", "Test board");

        CreateBoardResponse response = boardRestTestClient.createNewBoard(boardParams);
        ID_BOARD = response.getId();

        boardRestTestClient.deleteBoardIfExist(ID_BOARD);

        String boardMessage = boardRestTestClient
                .getBoardId(ID_BOARD)
                .body()
                .asString();

        Assert.assertEquals(boardMessage, "The requested resource was not found.");
    }
}