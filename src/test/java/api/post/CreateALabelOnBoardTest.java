package api.post;

import api.BaseTest;
import api.dto.CreateLabelResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;
import static api.clients.LabelRestTestClient.constructDefaultLabelKeyValue;

public class CreateALabelOnBoardTest extends BaseTest {
    private String boardId;
    private String labelId;

    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test(description = "AS2-5")
    @Description("Create a new label on a board")
    public void createLabelOnBoard() {
        String color = "blue";
        String name = "label1";
        labelId = labelRestTestClient.createLabel(constructDefaultLabelKeyValue(name, color, boardId)).getId();

        CreateLabelResponse response = labelRestTestClient.getLabel(labelId);

        Assert.assertEquals(name, response.getName());
        Assert.assertEquals(color, response.getColor());
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }
}