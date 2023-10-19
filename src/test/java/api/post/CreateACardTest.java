package api.post;

import api.BaseTest;
import api.dto.CardDataResponse;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;

public class CreateACardTest extends BaseTest {
    private String idBoard;
    private String idList;
    private String idCard;
    private static String CARD_NAME = "New Test Card";
    private static String DESCRIPTION_OF_CARD = "The description for the Test Card";

    @BeforeMethod
    @Step("Fulfillment of the prerequisites for the test")
    public void createListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
    }

    @Test(description = "AS2-12")
    @Description("Positive: Create a Card")
    public void createСard() {
        Map<String, String> cardParams = new HashMap<>();
        cardParams.put("name", CARD_NAME);
        cardParams.put("desc", DESCRIPTION_OF_CARD);

        CardDataResponse response = cardTestRestClient.createCard(cardParams, idList);
        idCard = response.getId();

        Assert.assertEquals(response.getName(), CARD_NAME, "Card name doesn't match");
        Assert.assertEquals(response.getDesc(), DESCRIPTION_OF_CARD, "Card description is empty");
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(idBoard);
    }
}