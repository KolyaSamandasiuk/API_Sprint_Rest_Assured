package api.post;

import api.BaseTest;
import api.dto.AttachmentDataResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.CardTestRestClient.constructAttachmentKeyValue;
import static api.clients.CardTestRestClient.constructDefaultCardKeyValue;
import static api.clients.ListTestRestClient.constructDefaultListKeyValue;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateAttachmentTest extends BaseTest {
    private final String NAME = "NewAttachment";
    private final String MIME_TYPE = "pdf";
    private final String URL = "https://drive.google.com/file/d/1voEXDM9lUlXAdp7ZkIaUt3Fhtgjwdcwa/view?usp=sharing";
    private final String SET_COVER = "false";

    private String boardId;
    private String listId;
    private String cardId;
    private SoftAssertions assertions;

    @BeforeMethod
    @Step("Preparing for the test")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        listId = listTestRestClient.createList(constructDefaultListKeyValue(), boardId).getId();
        cardId = cardTestRestClient.createCard(constructDefaultCardKeyValue(), listId).getId();
    }

    @Test(description = "AS2-33")
    @Description("Positive: Create Attachment On Card")
    public void createAttechment() {
        assertions = new SoftAssertions();
        AttachmentDataResponse response = cardTestRestClient.createAttachmentOnCard(constructAttachmentKeyValue(NAME, MIME_TYPE, URL, SET_COVER), cardId);

        assertions.assertThat(response.getName() == NAME);
        assertions.assertThat(response.getMimeType() == MIME_TYPE);
        assertions.assertThat(response.getUrl() == URL);
        assertions.assertAll();
    }

    @Test(description = "AS2-39")
    @Description("Negative: Creating an attachment with incorrect data")
    public void InvalidCreateAttechment() {
        assertions = new SoftAssertions();
        JsonPath jp = cardTestRestClient.tryCreateAttachmentOnCard(constructAttachmentKeyValue(NAME, MIME_TYPE, "", SET_COVER), cardId,400).jsonPath();
        String message = jp.getString("message");

        assertions.assertThat(message == "Must provide either url or file");
    }

    @AfterMethod
    public void deleteBoard() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }
}
