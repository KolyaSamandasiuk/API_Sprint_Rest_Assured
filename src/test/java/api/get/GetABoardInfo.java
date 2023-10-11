package api.get;

import api.BaseTest;
import api.dto.BoardDataResponse;
import jdk.jfr.Description;
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
        ID_BOARD = boardClient.createNewBoard(Map.of("name", NAME_VALUE, "desc", DESC_VALUE)).getId();
    }

    @Test
    @Description("Getting information about the board by id")
    public void getABoardById() {
        BoardDataResponse response = boardClient.getBoardById(ID_BOARD);

        Assert.assertEquals(NAME_VALUE, response.getName());
        Assert.assertEquals(DESC_VALUE, response.getDesc());
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoardIfExist(ID_BOARD);
    }
}