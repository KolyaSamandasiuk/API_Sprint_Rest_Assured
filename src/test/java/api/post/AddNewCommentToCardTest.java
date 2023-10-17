package api.post;

import api.BaseTest;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCommentKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;
import static org.assertj.core.api.Assertions.assertThat;

public class AddNewCommentToCardTest extends BaseTest {

    private String idCard;
    private String idBoard;
    private String idList;

    @BeforeTest
    public void createNewBoard(){
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue(), idBoard).getId();
        idCard = cardTestRestClient.createCard(constructDefaultCardKeyValue(), idList).getId();
    }

    @Test(description = "AS2-23")
    @Description("Positive: Add a new comment to a card")
    public void addCommentToCard(){
        Response response = cardTestRestClient.addNewCommentToCard(constructDefaultCommentKeyValue(), idCard);
        JsonPath jsonPath = response.jsonPath();
        
        assertThat(getFieldValueFromJsonPath(jsonPath, "text")).isEqualTo(constructDefaultCommentKeyValue().get("text"));
        assertThat(getFieldValueFromJsonPath(jsonPath, "card.id")).isEqualTo(idCard);
        assertThat(getFieldValueFromJsonPath(jsonPath, "list.id")).isEqualTo(idList);
        assertThat(getFieldValueFromJsonPath(jsonPath, "board.id")).isEqualTo(idBoard);
    }

    @AfterMethod
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(idBoard);
    }

    public String getFieldValueFromJsonPath(JsonPath jsonPath, String field) {
        return jsonPath.get("data." + field);
    }
}
