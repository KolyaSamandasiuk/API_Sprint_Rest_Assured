package api.delete;

import api.BaseTest;
import api.dto.CardDataResponse;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;

public class DeleteACardTest extends BaseTest {

    private String idBoard;
    private String idList;
    private String idCard;
    private static final String CARD_NAME = "New Test Card";

    @BeforeClass
    public void createListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
    }

    @Test
    public void deleteCardTest() {
        Map<String, String> cardParams = new HashMap<>();
        cardParams.put("name", CARD_NAME);

        CardDataResponse response = cardTestRestClient.createCard(cardParams, idList);
        idCard = response.getId();

        cardTestRestClient.deleteCardIfExist(idCard);

        String cardMessage = cardTestRestClient
                .getCardInfoByCardId(idCard)
                .body()
                .asString();

        Assert.assertEquals(cardMessage, "The requested resource was not found.");
    }

    @AfterClass
    public void deleteBoard() {
        cardTestRestClient.deleteCardIfExist(idCard);
    }
}