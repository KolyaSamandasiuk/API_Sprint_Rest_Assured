package api.tests;

import api.BaseTest;
import api.dto.ListsDataResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class GetListsOnBoardTest extends BaseTest {

    private String boardId;
    private final List<String> expectedListNames = new ArrayList<>(List.of("List 3", "List 2", "List 1", "Нужно сделать",
            "В процессе", "Готово"));

    @BeforeMethod
    public void createBoard() {
        boardId = boardClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        expectedListNames.forEach(name -> listClient.postList(name, boardId));
    }

    @Test
    public void getListsOnBoard() {
        List<ListsDataResponse> lists = listClient.getLists(boardId);
        List<String> listNames = getReversedListNames(lists);

        assertThat(listNames).hasSize(expectedListNames.size());
        assertThat(listNames).isEqualTo(expectedListNames);
    }

    @AfterMethod
    public void delete() {
        boardClient.deleteBoardIfExist(boardId);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }

    private List<String> getReversedListNames(List<ListsDataResponse> lists) {
        List<String> listNames = lists.stream()
                .map(ListsDataResponse::getName)
                .collect(Collectors.toList());
        Collections.reverse(listNames);
        return listNames;
    }

}
