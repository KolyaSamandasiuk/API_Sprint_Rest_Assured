package api.post;

import api.BaseTest;
import api.dto.CardDataResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;
import static api.clients.ListTestRestClient.constructMoveAllCardsToListKeyValue;
import static javax.swing.UIManager.get;

public class MoveAllCardsInList extends BaseTest {
    private String boardId;
    private String listId;
    private String cardId;
    private String secondCardId;
    private String secondBoardId;
    private String secondListId;

    @BeforeMethod
    @Step("\"Preparing to the test")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        listId = listTestRestClient.createList(constructDefaultListKeyValue(), boardId).getId();
        cardId = cardTestRestClient.createCard(constructDefaultCardKeyValue(), listId).getId();
        secondCardId = cardTestRestClient.createCard(constructDefaultCardKeyValue(), listId).getId();
        secondBoardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        secondListId = listTestRestClient.createList(constructDefaultListKeyValue(), secondBoardId).getId();
    }

    @Test(description = "AS2-32")
    @Description("Positive: Move all Cards in List")
    public void moveCardsInList() {
        List<CardDataResponse> response = listTestRestClient.moveAllCardsInList(constructMoveAllCardsToListKeyValue(secondBoardId, secondListId), listId);

        Assert.assertEquals(response.get(0).getIdList(), secondListId, "Card was not moved to the target list");
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(boardId);
        boardRestTestClient.deleteBoardIfExist(secondBoardId);
    }
}