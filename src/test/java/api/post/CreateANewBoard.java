package api.post;

import api.BaseTest;
import api.dto.CreateBoardResponse;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateANewBoard extends BaseTest {
    private String ID_BOARD;

    @Test(description = "AS2-4")
    @Description("Create a board")
    public void createBoardTest() {

        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", "Test board");

        CreateBoardResponse response = boardRestTestClient.createNewBoard(boardParams);
        ID_BOARD = response.getId();

        Assert.assertNotNull(response, "Board creation failed");
        Assert.assertNotNull(response.getId(), "Board ID is null");
        Assert.assertEquals(response.getName(), "Test board", "Board name doesn't match");

        deleteBoard();
    }

    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(ID_BOARD);
    }

    @Test(description = "AS2-36")
    @Description(" Negative: Create a Board with invalid name ")
    public void createBoardWithEmptyNameTest() {

        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", " ");

        JsonPath jp = boardRestTestClient.tryToCreateABoard(boardParams, HTTP_BAD_REQUEST).jsonPath();
        String message = jp.getString("message");

        assertThat(message).isEqualTo("invalid value for name");
    }
}
