package api.get;

import api.BaseTest;
import api.dto.ListsDataResponse;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class GetListsOnBoardTest extends BaseTest {

    private String boardId;
    private final List<String> expectedListNames = List.of("List 3", "List 2", "List 1", "To Do", "Doing", "Done");
    private final List<String> listNamesForCreate = List.of("List 1", "List 2", "List 3");

    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        listNamesForCreate.forEach(name -> listTestRestClient.createList(constructDefaultListKeyValue(name), boardId));
    }

    @Test(description = "AS2-20")
    @Description("Getting information about lists on board")
    public void getListsOnBoard() {
        List<String> listNames = listTestRestClient.getLists(boardId).stream().map(ListsDataResponse::getName).collect(Collectors.toList());

        assertThat(listNames).hasSize(expectedListNames.size());
        assertThat(listNames).isEqualTo(expectedListNames);
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }

    private Map<String, String> constructDefaultListKeyValue(String listName) {
        return Map.of("name",  listName);
    }
}

