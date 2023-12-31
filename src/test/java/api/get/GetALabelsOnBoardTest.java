package api.get;

import api.BaseTest;
import api.dto.CreateLabelResponse;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static api.clients.BoardRestTestClient.constructDefaultBoardKeyValue;


public class GetALabelsOnBoardTest extends BaseTest {
    private String boardId;
    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test(description = "AS2-15")
    @Description("Get labels on a board")
    public void getLabelsOnBoard() {
        List<String> labelNames = labelRestTestClient.getLabelsOnABoard(boardId).stream().map(CreateLabelResponse::getColor).collect(Collectors.toList());
        List<String> expectedLabelColors = List.of("blue", "green", "orange", "purple", "red", "yellow");

        Assert.assertEquals(labelNames.size(), expectedLabelColors.size(), "Number of labels does not match the expected number.");
        Assert.assertTrue(labelNames.containsAll(expectedLabelColors), "Not all expected label colors are found.");
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }
}