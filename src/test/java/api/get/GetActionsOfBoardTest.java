package api.get;

import api.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static api.clients.AbstractBaseRestClient.API_KEY;
import static api.clients.AbstractBaseRestClient.API_TOKEN;
import static io.restassured.RestAssured.given;

public class GetActionsOfBoardTest extends BaseTest {

    public String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardClient.createNewBoard(Map.of("name", "Test board")).getId();
    }

    @Test
    public void GetActionsOfBoard() {

        Response response = given()
                .baseUri("https://api.trello.com/1")
                .when()
                .get("/boards/" + ID_BOARD + "/actions" + "?key=" + API_KEY + "&token=" + API_TOKEN)
                .then()
                .statusCode(200)
                .extract().
                response();
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoardIfExist(ID_BOARD);
    }
    }