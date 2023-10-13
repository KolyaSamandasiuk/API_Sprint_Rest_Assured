package api.get;

import api.BaseTest;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class GetActionsOfBoardTest extends BaseTest {

    private static String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardRestTestClient.createNewBoard(Map.of("name", "Test board")).getId();
    }

    @Test
    @Description("Getting information about action on the boards")
    public void getActionsOfBoard() {
        actionsRestTestClient.getActionOfBoard( ID_BOARD)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ResponseSchemaDirectory/schema.json"));
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(ID_BOARD);
    }
}