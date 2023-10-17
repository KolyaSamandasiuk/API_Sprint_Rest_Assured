package api.post;

import api.BaseTest;
import api.dto.ChecklistDataResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;


public class CreateAChecklist extends BaseTest {

    private final String CARD_NAME = "New Test Checklist";

    private String boardId;
    private String listId;
    private String cardId;

    @BeforeMethod
    @Step("Fulfillment of the prerequisites for the test")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        listId = listTestRestClient.createList(constructDefaultListKeyValue(), boardId).getId();
        cardId = cardTestRestClient.createCard(constructDefaultCardKeyValue(), listId).getId();
    }

    @Test(description = "AS2-24")
    @Description("Positive: Create a checklist")
    public void createChecklist() {
        Map<String, String> checklistParams = new HashMap<>();
        checklistParams.put("name", CARD_NAME);

        ChecklistDataResponse response = checklistRestTestClient.createChecklist(checklistParams, cardId);

        Assert.assertEquals(response.getName(), CARD_NAME);
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }
}
