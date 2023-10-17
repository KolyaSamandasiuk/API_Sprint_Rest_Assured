package api.post;

import api.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AddNewCommentToCardTest extends BaseTest {

    private String idCard;
    private String idBoard;
    private String idList;

    @BeforeTest
    public void createNewBoard(){
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
        idCard = cardsTestRestClient.createCard("name card", idList).getId();
    }

    @Test
    public void addCommentToCard(){
        Response response = cardsTestRestClient.addNewCommentToCard(constructDefaultCommentKeyValue(), idCard);
        JsonPath jsonPath = response.jsonPath();
        
        assertThat(getFieldValueFromJsonPath(jsonPath, "text")).isEqualTo(constructDefaultCommentKeyValue().get("text"));
        assertThat(getFieldValueFromJsonPath(jsonPath, "card.id")).isEqualTo(idCard);
        assertThat(getFieldValueFromJsonPath(jsonPath, "list.id")).isEqualTo(idCard);
        assertThat(getFieldValueFromJsonPath(jsonPath, "board.id")).isEqualTo(idCard);
    }

    @AfterMethod
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(idBoard);
    }
    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }

    private Map<String, String> constructDefaultListKeyValue() {
        return Map.of("name", "List name for test" + RandomStringUtils.randomAlphanumeric(3));
    }

    private Map<String, String> constructDefaultCommentKeyValue() {
        return Map.of("text", "Test comment" + RandomStringUtils.randomAlphanumeric(3));
    }

    public String getFieldValueFromJsonPath(JsonPath jsonPath, String field) {
        return jsonPath.get("data." + field);
    }
}
