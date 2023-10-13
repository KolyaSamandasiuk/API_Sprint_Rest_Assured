package api.delete;

import api.BaseTest;
import api.dto.CreateBoardResponse;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteABoardTest extends BaseTest {

    private String bordId;

    @BeforeClass
    public void setup() {
        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", "Test board");

        CreateBoardResponse response = boardRestTestClient.createNewBoard(boardParams);
        bordId = response.getId();
    }

    @Test
    public void deleteBoardTest() {
        boardRestTestClient.deleteBoard(bordId);

        String message = boardRestTestClient.getBoardById(bordId, HTTP_NOT_FOUND).asString();
        assertThat(message).isEqualTo("The requested resource was not found.");
    }
}
