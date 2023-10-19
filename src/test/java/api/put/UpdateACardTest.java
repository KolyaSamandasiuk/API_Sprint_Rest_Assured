package api.put;

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
import static api.clients.CardTestRestClient.constructDefaultCardKeyValueWithDesc;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;

public class UpdateACard extends BaseTest {
    private final String NAME_VALUE = "Test Card";
    private final String DESC_VALUE = "The description for the Test Card";
    private final String CHANGED_NAME_VALUE = "New Test Card";
    private final String CHANGED_DESC_VALUE = "New description for the Test Card";
    private String idBoard;
    private String idList;
    private String idCard;

    @BeforeMethod
    @Step("Fulfillment of the prerequisites for the test")
    public void createCardOfListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
        idCard = cardTestRestClient.createCard(constructDefaultCardKeyValueWithDesc(NAME_VALUE, DESC_VALUE), idList).getId();
    }

    @Test(description = "AS2-19")
    @Description("Positive: Update a card by id")
    public void updateCardInformationById() {
        Map<String, String> cardParams = new HashMap<>();
        cardParams.put("name", CHANGED_NAME_VALUE);
        cardParams.put("desc", CHANGED_DESC_VALUE);
        CardDataResponse response = cardTestRestClient.putCardInfo(idCard, cardParams);

        Assert.assertEquals(CHANGED_NAME_VALUE, response.getName());
        Assert.assertEquals(CHANGED_DESC_VALUE, response.getDesc());
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(idBoard);
    }
}