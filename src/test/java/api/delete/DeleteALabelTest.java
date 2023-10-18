package api.delete;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.LabelRestTestClient.constructDefaultLabelKeyValue;

public class DeleteALabelTest extends BaseTest {
    private String boardId;
    private String labelId;

    @BeforeMethod
    @Step("Test preparation")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        labelId = labelRestTestClient.createLabel(constructDefaultLabelKeyValue("favorite", "blue", boardId)).getId();
    }

    @Test(description = "AS2-28")
    @Description("Positive: Delete a label")
    public void deleteALabel() {
        labelRestTestClient.deleteLabel(labelId);

        String labelMessage = labelRestTestClient
                .tryGetLabelById(labelId)
                .body()
                .asString();

        Assert.assertEquals(labelMessage, "The requested resource was not found.");
    }
}
