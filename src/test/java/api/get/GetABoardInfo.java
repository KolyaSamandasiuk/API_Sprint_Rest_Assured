package api.get;

import api.BaseTest;
import api.dto.BoardDataResponse;
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
    public void getABoardIndo() {
        BoardDataResponse response = boardClient.getBoardById(ID_BOARD);

        verifyBoardByNameAndDescription(response, NAME_VALUE, DESC_VALUE);
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoardIfExist(ID_BOARD);
    }

    private static void verifyBoardByNameAndDescription(BoardDataResponse boardData, String expectedName, String expectedDescription) {
        Assert.assertEquals(expectedName, boardData.getName());
        Assert.assertEquals(expectedDescription, boardData.getDesc());
    }
}