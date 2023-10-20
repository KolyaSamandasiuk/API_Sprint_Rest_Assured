package api.get;

import api.BaseTest;
import api.clients.EmojiRestTestClient;
import api.dto.EmojiDataResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static api.Const.BASE_URL;

public class GetAEmojiTest extends BaseTest {
    @Test(description = "AS2-43")
    @Description("Get list available Emoji")
    public void getEmojiList() {
        emojiRestTestClient = new EmojiRestTestClient(BASE_URL);
        List<String> emojiNames =  emojiRestTestClient.getEmoji().stream().map(EmojiDataResponse::getName).collect(Collectors.toList());

        Assert.assertTrue(emojiNames.size()>0);
    }
}

