package api.get;

import api.BaseTest;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class GetChecklistsOnBoardTest extends BaseTest {

    private static String ID_BOARD;
    private static String LIST_ID;
    private static String CARD_ID;
    private static String CHECKLIST_ID;

    @BeforeMethod
    public void createBoard() {
        ID_BOARD = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        LIST_ID = listTestRestClient.createList(constructDefaultBoardKeyValue(), ID_BOARD).getId();
        CARD_ID = cardTestRestClient.createCard(LIST_ID).getId();
        CHECKLIST_ID = checklistTestRestClient.createChecklist(CARD_ID).getId();
    }

    @Test
    @Description("Getting checklist on a boards")
    public void getChecklistOnBoard() {
        checklistTestRestClient.deleteChecklist(CHECKLIST_ID);
        String response = checklistTestRestClient.getChecklistAfterDelete(ID_BOARD).jsonPath().getString("");

        Assert.assertEquals(response, "[]");
    }

    @AfterMethod
    public void delete() {
        cardTestRestClient.deleteCart(CARD_ID);
        boardRestTestClient.deleteBoardIfExist(ID_BOARD);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }
}