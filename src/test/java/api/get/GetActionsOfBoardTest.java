package api.get;

import api.BaseTest;
import jdk.jfr.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class GetActionsOfBoardTest extends BaseTest {

    private String id_board;

    @BeforeMethod
    public void createBoard() {
        id_board = boardRestTestClient.createNewBoard(Map.of("name", "Test board")).getId();
    }

    @Test
    @Description("Getting information about action on the boards")
    public void getActionsOfBoard() {
        actionsRestTestClient.getActionOfBoard(id_board)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("ResponseSchemaDirectory/schema.json"));
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(id_board);
    }
}