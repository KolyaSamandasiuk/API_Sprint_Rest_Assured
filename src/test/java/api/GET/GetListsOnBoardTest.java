package api.GET;

import api.BaseTest;
import api.POJO.ListsDataResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GetListsOnBoardTest extends BaseTest {

    public String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = baseRestClient.createNewBoard().get("id");
    }

    @Test
    public void getListsOnBoard() {
        for (int i = 1; i < 4; i++) {
            baseRestClient.createList("List " + i, ID_BOARD);
        }

        List<String> listNames = baseRestClient.getLists(ID_BOARD).stream().map(ListsDataResponse::getName).collect(Collectors.toList());

        List<String> expectedList = new ArrayList<>();
        expectedList.add("List 3");
        expectedList.add("List 2");
        expectedList.add("List 1");
        expectedList.add("Нужно сделать");
        expectedList.add("В процессе");
        expectedList.add("Готово");

        Assert.assertEquals(listNames, expectedList);
    }

    @AfterMethod
    public void delete() {
        baseRestClient.deleteBoard(ID_BOARD);
    }
}
