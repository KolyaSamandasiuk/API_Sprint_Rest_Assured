package api.post;

import api.BaseTest;
import api.dto.AttachmentDataResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.CardTestRestClient.constructAttachmentKeyValue;

public class CreateAttachmentTest extends BaseTest {
    private final String NAME = "NewAttachment";
    private final String MIME_TYPE = "pdf";
    private final String URL = "https://drive.google.com/file/d/1voEXDM9lUlXAdp7ZkIaUt3Fhtgjwdcwa/view?usp=sharing";
    private final String SET_COVER = "false";



    private String boardId;
    private String listId;
    private String cardId;

    @BeforeMethod
    @Step("Preparing for the test")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        listId = listTestRestClient.createList(constructDefaultListKeyValue(), boardId).getId();
        cardId = cardTestRestClient.createCard(constructDefaultCardKeyValue(), listId).getId();
    }

    @Test(description = "AS2-33")
    @Description("Positive: Create Attachment On Card")
    public void createAttecnmet() {
        AttachmentDataResponse response = cardTestRestClient.createAttachmentOnCard(constructAttachmentKeyValue(NAME,MIME_TYPE,URL,SET_COVER),cardId);

        Assert.assertEquals(response.getName(), NAME);
        Assert.assertEquals(response.getMimeType(), MIME_TYPE);
        Assert.assertEquals(response.getUrl(), URL);
        Allure.addAttachment("API Log", "This is API logs");
    }
}
