package api.post;

import api.BaseTest;
import api.dto.CardDataResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CreateACardTest extends BaseTest {
    private String idBoard;
    private String idList;
    private String idCard;

    @BeforeClass
    public void createListOnABoard() {
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
    }

    @Test
    public void createСard() {
        Map<String, String> cardParams = new HashMap<>();
        cardParams.put("name", "New Test Card");
        cardParams.put("desc", "The description for the Test Card");

        CardDataResponse response = cardTestRestClient.createCard(cardParams, idList);
        idCard = response.getId();

        Assert.assertNotNull(response, "Card creation failed");
        Assert.assertNotNull(response.getId(), "Card ID is null");
        Assert.assertEquals(response.getName(), "New Test Card", "Card name doesn't match");
        Assert.assertEquals(response.getDesc(), "The description for the Test Card", "Card description is empty");
    }

    @AfterClass
    public void deleteBoard() {
        cardTestRestClient.deleteCardIfExist(idCard);
    }

    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(5));
    }

    private Map<String, String> constructDefaultListKeyValue() {
        return Map.of("name", "Test list " + RandomStringUtils.randomAlphanumeric(5));
    }
}