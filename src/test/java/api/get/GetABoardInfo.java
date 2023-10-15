package api.get;

import api.BaseTest;
import api.dto.BoardDataResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class GetABoardInfo extends BaseTest {

    private String NAME_VALUE = "Test board";
    private String DESC_VALUE = "Decription is empty";

    public String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardRestTestClient.createNewBoard(Map.of("name", NAME_VALUE, "desc", DESC_VALUE)).getId();
    }

    @Test(description = "AS2-3")
    @Description("Getting information about the board by id")
    public void getABoardById() {

        BoardDataResponse response = boardRestTestClient.getBoardById(ID_BOARD);
        Assert.assertEquals(NAME_VALUE, response.getName());
        Assert.assertEquals(DESC_VALUE, response.getDesc());
    }

    @AfterMethod
    @Step("Delete the test board")
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(ID_BOARD);
    }
}