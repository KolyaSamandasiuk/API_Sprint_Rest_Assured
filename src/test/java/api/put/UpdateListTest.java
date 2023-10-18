package api.put;

import api.BaseTest;
import api.dto.ListsDataResponse;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;
import static api.clients.ListTestRestClient.constructPutListKeyValue;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateListTest extends BaseTest {

    private String idBoard;
    private String idList;

    @BeforeMethod
    public void createBoardAndList() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
    }

    @Test(description = "AS2-37")
    @Description("Positive: Update a list by id")
    public void updateTheListInformation() {
        ListsDataResponse updatedListResponse = listTestRestClient.updateListFromBoard(constructPutListKeyValue("Changed name for list"), idList);
        List<String> allListNames = listTestRestClient.getLists(idBoard).stream().map(ListsDataResponse::getName).toList();

        assertThat(updatedListResponse.getName()).isNotNull();
        assertThat(allListNames).isNotNull();
        assertThat(allListNames.size()).isEqualTo(4);
        assertThat(allListNames.get(0)).isEqualTo(updatedListResponse.getName());
    }

    @AfterMethod
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(idBoard);
    }
}
