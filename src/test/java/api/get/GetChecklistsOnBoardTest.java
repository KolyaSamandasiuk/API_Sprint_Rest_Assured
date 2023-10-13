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

    private String id_board;
    private String list_id;
    private String card_id;
    private String checklist_id;

    @BeforeMethod
    public void createBoard() {
        id_board = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        list_id = listTestRestClient.createList(constructDefaultBoardKeyValue(), id_board).getId();
        card_id = cardTestRestClient.createCard(list_id).getId();
        checklist_id = checklistTestRestClient.createChecklist(card_id).getId();
    }

    @Test
    @Description("Getting checklist on a boards")
    public void getChecklistOnBoard() {
        checklistTestRestClient.deleteChecklist(checklist_id);
        String response = checklistTestRestClient.getChecklistAfterDelete(id_board).jsonPath().getString("");

        Assert.assertEquals(response, "[]");
    }

    @AfterMethod
    public void delete() {
        cardTestRestClient.deleteCart(card_id);
        boardRestTestClient.deleteBoardIfExist(id_board);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }
}