package api.post;

import api.BaseTest;
import api.dto.CommentDto;
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
   // private final Map<String, String> paramsForCard = Map.of("idList", idList, "name", "Some name for card");

    @BeforeTest
    public void createNewBoard(){
        idBoard = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        idList = listTestRestClient.createList(constructDefaultListKeyValue("List name for test"), idBoard).getId();
        idCard = cardsTestRestClient.createCard("name card", idList).getId();
    }

    @Test
    public void addCommentToCard(){
        CommentDto response = cardsTestRestClient.addNewCommentToCard(constructDefaultCommentKeyValue(), idCard);

        assertThat(response.getData().getText()).isEqualTo(constructDefaultCommentKeyValue().get("text"));
        assertThat(response.getDisplay().getEntities().getComment().getText()).isEqualTo(constructDefaultCommentKeyValue().get("text"));
    }

    @AfterMethod
    public void deleteBoard(){
        boardRestTestClient.deleteBoardIfExist(idBoard);
    }
    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }

    private Map<String, String> constructDefaultListKeyValue(String listName) {
        return Map.of("name",  listName);
    }

    private Map<String, String> constructDefaultCommentKeyValue() {
        return Map.of("text", "Test comment");
    }
}
