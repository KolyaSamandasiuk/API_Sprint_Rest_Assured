package api.delete;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.ChecklistRestTestClient.constructDefaultChecklistKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;

public class DeleteAChecklistTest extends BaseTest {
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
        checklistId = checklistRestTestClient.createChecklist(constructDefaultChecklistKeyValue(),cardId).getId();
    }

    @Test(description = "AS2-27")
    @Description("Positive : Delete checklist")
    public void DeleteAChecklist() {
        checklistRestTestClient.deleteChecklistIfExist(checklistId);

        String boardMessage = checklistRestTestClient
                .tryGetChecklistById(checklistId)
                .body()
                .asString();

        Assert.assertEquals(boardMessage, "The requested resource was not found.");
    }
}