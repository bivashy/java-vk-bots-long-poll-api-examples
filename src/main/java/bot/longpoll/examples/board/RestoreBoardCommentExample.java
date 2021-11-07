package bot.longpoll.examples.board;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class RestoreBoardCommentExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(RestoreBoardCommentExample.class);
    private static final int TOPIC_ID = 47564267;
    private static final int COMMENT_ID = 9;

    public void restoreBoardComment() {
        try {
            IntegerResponse response = vk.board.restoreComment()
                    .setGroupId(getGroupId())
                    .setTopicId(TOPIC_ID)
                    .setCommentId(COMMENT_ID)
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void restoreBoardCommentAsync() {
        CompletableFuture<IntegerResponse> future = vk.board.restoreComment()
                .setGroupId(getGroupId())
                .setTopicId(TOPIC_ID)
                .setCommentId(COMMENT_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }

    @Override
    public int getGroupId() {
        return 886761559;
    }

    public static void main(String[] args) {
        RestoreBoardCommentExample example = new RestoreBoardCommentExample();
        example.restoreBoardComment();
        example.restoreBoardCommentAsync();
    }
}
