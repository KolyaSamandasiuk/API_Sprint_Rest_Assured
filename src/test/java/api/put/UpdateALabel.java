package api.put;

import api.BaseTest;
import api.dto.CreateLabelResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.LabelRestTestClient.constructDefaultLabelKeyValue;

public class UpdateALabel extends BaseTest {
    private final String CHANGED_NAME_VALUE = "urgent";
    private final String CHANGED_COLOR_VALUE = "green";

    public String labelId;
    public String boardId;

    @BeforeMethod
    @Step("Test preparation")
    public void preconditions() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
        labelId = labelRestTestClient.createLabel(constructDefaultLabelKeyValue("important", "yellow", boardId)).getId();
    }

    @Test(description = "AS2-35")
    @Description("Positive: Update a label by id")
    public void updateLabelById() {
        Map<String, String> labelUpdates = new HashMap<>();
        labelUpdates.put("name", CHANGED_NAME_VALUE);
        labelUpdates.put("color", CHANGED_COLOR_VALUE);

        CreateLabelResponse response = labelRestTestClient.putLabelInfo(labelId, labelUpdates);

        Assert.assertEquals(CHANGED_NAME_VALUE, response.getName());
        Assert.assertEquals(CHANGED_COLOR_VALUE, response.getColor());
    }

    @AfterMethod
    public void deleteBoard() { boardRestTestClient.deleteBoardIfExist(boardId);}
}
