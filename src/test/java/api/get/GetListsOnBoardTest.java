package api.get;

import api.BaseTest;
import api.dto.ListsDataResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GetListsOnBoardTest extends BaseTest {

    public String ID_BOARD;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardClient.createNewBoard(Map.of("name", "Test board")).getId();
    }

    @Test
    public void getListsOnBoard() {

        List<String> expectedListNames = List.of("List 3", "List 2", "List 1", "To Do", "Doing", "Done");
        List<String> reversedSubListNames = expectedListNames.subList(0, 3).stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    Collections.reverse(result);
                    return result;
                }));

        for (String item : reversedSubListNames) {
            listClient.createList(item, ID_BOARD);
        }

        List<String> listNames = listClient.getLists(ID_BOARD).stream().map(ListsDataResponse::getName).collect(Collectors.toList());

        Assert.assertEquals(listNames.size(), expectedListNames.size());
        Assert.assertEquals(listNames, expectedListNames);
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoardIfExist(ID_BOARD);
    }
}
