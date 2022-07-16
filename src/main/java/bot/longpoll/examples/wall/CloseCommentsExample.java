package bot.longpoll.examples.wall;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CloseCommentsExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloseCommentsExample.class);
    private static final int POST_ID = 9;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            CloseCommentsExample example = new CloseCommentsExample();
            example.closeComments();
            example.closeCommentsAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void closeComments() throws VkApiException {
        IntegerResponseBody integerResponseBody = vk.wall.closeComments()
                .setPostId(POST_ID)
                .setOwnerId(-GROUP_ID)
                .execute();

        System.out.println("Sync integerResponseBody: " + integerResponseBody);
    }

    public void closeCommentsAsync() {
        CompletableFuture<IntegerResponseBody> future = vk.wall.closeComments()
                .setPostId(POST_ID)
                .setOwnerId(-GROUP_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
