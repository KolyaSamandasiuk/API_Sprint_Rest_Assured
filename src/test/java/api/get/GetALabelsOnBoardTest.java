package api.get;

import api.BaseTest;
import api.dto.CreateLabelResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GetALabelsOnBoardTest extends BaseTest {
    private String boardId;
    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test
    public void getLabelsOnBoard() {
        List<String> labelNames = labelRestTestClient.getLabelsOnABoard(boardId).stream().map(CreateLabelResponse::getColor).collect(Collectors.toList());
        List<String> expectedLabelColors = List.of("blue", "green", "orange", "purple", "red", "yellow");

        Assert.assertFalse(labelNames.isEmpty(), "Label list is empty.");
        Assert.assertTrue(labelNames.containsAll(expectedLabelColors), "Not all expected label colors are found.");
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }
    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }
}