package api.put;

import api.BaseTest;
import api.dto.BoardDataResponse;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UpdateABoard extends BaseTest {
    private String NAME_VALUE = "Test board";
    private String DESC_VALUE = "Decription is empty";
    private String CHANGED_NAME_VALUE = "Test board";
    private String CHANGED_DESC_VALUE = "Decription is empty";


    Map<String, String> boardParams = new HashMap<>();
    public String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardRestTestClient.createNewBoard(Map.of("name", NAME_VALUE, "desc", DESC_VALUE)).getId();
    }

    @Test
    @Description("Update a Board by id")
    public void getABoardById() {

        boardParams.put("name", CHANGED_NAME_VALUE);
        boardParams.put("desc", CHANGED_DESC_VALUE);

        BoardDataResponse response = boardRestTestClient.putBoardInfo(ID_BOARD, boardParams);

        Assert.assertEquals(CHANGED_NAME_VALUE, response.getName());
        Assert.assertEquals(CHANGED_DESC_VALUE, response.getDesc());
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(ID_BOARD);
    }
}
