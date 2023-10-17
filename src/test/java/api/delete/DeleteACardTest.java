package api.delete;

import api.BaseTest;
import api.dto.CardDataResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;

public class DeleteACardTest extends BaseTest {

    private String idBoard;
    private String idList;
    private String idCard;
    private static String CARD_NAME = "New Test Card";

    @BeforeMethod
    @Step("Fulfillment of the prerequisites for the test")
    public void createListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
    }

    @Test(description = "AS2-17")
    @Description("Positive: Delete a Card ")
    public void deleteCardTest() {
        Map<String, String> cardParams = new HashMap<>();
        cardParams.put("name", CARD_NAME);

        CardDataResponse response = cardTestRestClient.createCard(cardParams, idList);
        idCard = response.getId();

        cardTestRestClient.deleteCardIfExist(idCard);

        String cardMessage = cardTestRestClient
                .getCardId(idCard)
                .body()
                .asString();

        Assert.assertEquals(cardMessage, "The requested resource was not found.");
    }

    @AfterMethod
    @Step("Delete the test board")
    public void deleteBoard() {
        cardTestRestClient.deleteCardIfExist(idCard);
    }
}