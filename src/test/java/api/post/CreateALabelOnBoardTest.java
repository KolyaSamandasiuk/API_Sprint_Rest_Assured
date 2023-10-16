package api.post;

import api.BaseTest;
import api.dto.CreateLabelResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class CreateALabelOnBoardTest extends BaseTest {

    private String boardId;
    private String labelId;

    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test
    public void createLabelOnBoard() {
         labelId = labelRestTestClient.createLabel(constructDefaultListKeyValue("label1","blue",boardId)).getId();
         List<CreateLabelResponse> LabelDataResponse = labelRestTestClient.getLabelOnABoard(boardId);
         Assert.assertNotNull(labelId, "Label has not been created.");
         Assert.assertFalse(LabelDataResponse.isEmpty(), "The list of labels on the board is empty.");
    }

    @AfterMethod
    public void delete() {
        boardRestTestClient.deleteBoardIfExist(boardId);
    }
    private Map<String, String> constructDefaultBoardKeyValue() {
        return Map.of("name", "Test board " + RandomStringUtils.randomAlphanumeric(3));
    }

    private Map<String, String> constructDefaultListKeyValue(String labelName,String labelColor,String idBoard) {
        return Map.of("name",  labelName,"color",labelColor,"idBoard",idBoard) ;
    }
}