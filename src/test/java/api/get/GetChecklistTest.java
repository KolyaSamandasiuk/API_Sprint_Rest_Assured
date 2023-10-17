package api.get;

import api.BaseTest;
import api.dto.ChecklistDataResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;


public class GetChecklistTest extends BaseTest {
    private final String CHECKLIST_NAME = "Name Of CheckList";
    private String boardId;
    private String listId;
    private String cardId;
    private String checklistId;


    @BeforeMethod
    @Step("Fulfillment of the prerequisites for the test")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        listId = listTestRestClient.createList(constructDefaultListKeyValue(), boardId).getId();
        cardId = cardTestRestClient.createCard(constructDefaultCardKeyValue(), listId).getId();
        checklistId = checklistRestTestClient.createChecklist(Map.of("name", CHECKLIST_NAME), cardId).getId();
    }

    @Test(description = "AS2-26")
    @Description("Get Checklist on a Board")
    public void getChecklist() {
        ChecklistDataResponse response = checklistRestTestClient.getChecklistById(checklistId);

        Assert.assertEquals(CHECKLIST_NAME, response.getName());
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }

}