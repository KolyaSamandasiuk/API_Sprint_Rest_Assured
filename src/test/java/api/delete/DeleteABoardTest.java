package api.delete;

import api.BaseTest;
import api.clients.BoardRestTestClient;
import api.dto.CreateBoardResponse;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.Const.BASE_URL;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class DeleteABoardTest extends BaseTest {

    private BoardRestTestClient boardRestTestClient = new BoardRestTestClient(BASE_URL);
    private String ID_BOARD;

    @Test
    public void deleteBoardTest() {
        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", "Test board");

        CreateBoardResponse response = boardRestTestClient.createNewBoard(boardParams);
        ID_BOARD = response.getId();

        ValidatableResponse deleteResponse = boardRestTestClient.deleteBoardIfExist(ID_BOARD);

        int statusCode = deleteResponse.extract().statusCode();
        Assert.assertTrue(statusCode == HTTP_OK || statusCode == HTTP_NOT_FOUND, "Unexpected status code");

        Boolean boardWasFound = deleteResponse.extract().body().path("found");
        if (boardWasFound != null) {
            Assert.assertTrue(boardWasFound, "Board was not found");
        }

        String boardName = deleteResponse.extract().body().path("name");
        if (boardName != null) {
            Assert.assertEquals("Test board", boardName, "Board name does not match");
        }
    }
}
