package api.post;

import api.BaseTest;
import api.dto.CreateLabelResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.Map;

public class CreateALabelOnBoardTest extends BaseTest {
    private String boardId;
    private String labelId;
    private String color = "blue";
    private String name = "label1";

    @BeforeMethod
    public void createBoard() {
        boardId = boardRestTestClient.createNewBoard(constructDefaultBoardKeyValue()).getId();
    }

    @Test
    public void createLabelOnBoard() {
         labelId = labelRestTestClient.createLabel(constructDefaultListKeyValue(name,color,boardId)).getId();
         CreateLabelResponse response = labelRestTestClient.getLabel(labelId);

         Assert.assertEquals(name, response.getName());
         Assert.assertEquals(color, response.getColor());
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