package api.get;

import api.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static api.clients.AbstractBaseRestClient.API_KEY;
import static api.clients.AbstractBaseRestClient.API_TOKEN;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class GetActionsOfBoardTest extends BaseTest {

    private static String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardClient.createNewBoard(Map.of("name", "Test board")).getId();
    }

    @Test
    public void GetActionsOfBoard() {
        apiClient.getJsonResponse("key=" + API_KEY + "&token=" + API_TOKEN, ID_BOARD)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoardIfExist(ID_BOARD);
    }
}