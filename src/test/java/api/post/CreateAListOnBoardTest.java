package api.post;

import api.BaseTest;
import api.dto.CreateListResponse;
import api.dto.ListsDataResponse;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateAListOnBoardTest extends BaseTest {

    private String boardId;

    @BeforeClass
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test(description = "AS2-21")
    @Description("Create a List on a Board")
    public void createListOnBoard(){
        CreateListResponse createListResponse = listTestRestClient.createList(constructDefaultListKeyValue("List name"), boardId);
        List<ListsDataResponse> listsDataResponse = listTestRestClient.getLists(boardId);

        assertThat(listsDataResponse).hasSize(4);
        assertThat(createListResponse).usingRecursiveComparison().isEqualTo(listsDataResponse.get(0));
        assertThat(listsDataResponse.get(1).getName()).isEqualTo("To Do");
        assertThat(listsDataResponse.get(2).getName()).isEqualTo("Doing");
        assertThat(listsDataResponse.get(3).getName()).isEqualTo("Done");
    }

    @Test
    public void shouldNotCreateWithoutName(){
        Response response = listTestRestClient.createList(constructDefaultListKeyValue(""), boardId, HTTP_BAD_REQUEST);
        String message = response.asString();

        assertThat(message).isEqualTo("invalid value for name");
    }

    @AfterClass
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(boardId);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }

    private Map<String, String> constructDefaultListKeyValue(String listName) {
        return Map.of("name",  listName);
    }
}