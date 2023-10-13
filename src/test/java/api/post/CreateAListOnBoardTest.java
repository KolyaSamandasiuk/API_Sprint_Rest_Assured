package api.post;

import api.BaseTest;
import api.dto.CreateListResponse;
import api.dto.ListsDataResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAListOnBoardTest extends BaseTest {

    private String boardId;

    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test
    public void createListOnBoard(){
        CreateListResponse createListResponse = listTestRestClient.createList("Test list name", boardId);
        List<ListsDataResponse> listsDataResponse = listTestRestClient.getLists(boardId);

        assertThat(listsDataResponse).hasSize(4);
        assertThat(createListResponse).usingRecursiveComparison().isEqualTo(listsDataResponse.get(0));
        assertThat(listsDataResponse.get(1).getName()).isEqualTo("To Do");
        assertThat(listsDataResponse.get(2).getName()).isEqualTo("Doing");
        assertThat(listsDataResponse.get(3).getName()).isEqualTo("Done");
    }

    @AfterMethod
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(boardId);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }
}
