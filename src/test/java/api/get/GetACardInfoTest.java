package api.get;

import api.BaseTest;
import api.dto.CardDataResponse;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;

public class GetACardInfoTest extends BaseTest {
    private static String NAME_VALUE = "Test card";
    private static String DESC_VALUE = "Description of Test card";
    public String idBoard;
    public String idList;
    private String idCard;

    @BeforeMethod
    public void createCardOfListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
        idCard = cardTestRestClient.createCard(constructDefaultCardKeyValue(), idList).getId();
    }

    @Test
    @Description("Getting information about the card by id")
    public void getACardById() {

        CardDataResponse response = cardTestRestClient.getCardById(idCard);

        Assert.assertEquals(NAME_VALUE, response.getName());
        Assert.assertEquals(DESC_VALUE, response.getDesc());
    }

    @AfterMethod
    public void deleteBoard() {
        cardTestRestClient.deleteCardIfExist(idCard);
    }
}