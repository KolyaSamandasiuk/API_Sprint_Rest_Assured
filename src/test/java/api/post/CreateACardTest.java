package api.post;

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

public class CreateACardTest extends BaseTest {
    private String idBoard;
    private String idList;
    private String idCard;
    private static final String CARD_NAME = "New Test Card";
    private static final String DESCRIPTION_OF_CARD = "The description for the Test Card";

    @BeforeClass
    public void createListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
    }

    @Test
    public void createСard() {
        Map<String, String> cardParams = new HashMap<>();
        cardParams.put("name", CARD_NAME);
        cardParams.put("desc", DESCRIPTION_OF_CARD);

        CardDataResponse response = cardTestRestClient.createCard(cardParams, idList);
        idCard = response.getId();

        Assert.assertEquals(response.getName(), "New Test Card", "Card name doesn't match");
        Assert.assertEquals(response.getDesc(), "The description for the Test Card", "Card description is empty");
    }

    @AfterClass
    public void deleteBoard() {
        cardTestRestClient.deleteCardIfExist(idCard);
    }
}