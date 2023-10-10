package api.GET;

import api.BaseTest;
import api.POJO.ListsDataResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static api.Const.expectedList;


public class GetListsOnBoardTest extends BaseTest {

    public String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardClient.createNewBoard().getId();
    }

    @Test
    public void getListsOnBoard() {
        for (int i = 1; i < 4; i++) {
            listClient.createList("List " + i, ID_BOARD);
        }

        List<String> listNames = listClient.getLists(ID_BOARD).stream().map(ListsDataResponse::getName).collect(Collectors.toList());

        Assert.assertEquals(listNames, expectedList);
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoard(ID_BOARD);
    }
}
