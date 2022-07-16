package bot.longpoll.examples.board;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteBoardCommentExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteBoardCommentExample.class);
    private static final int TOPIC_ID = 47564267;
    private static final int COMMENT_ID = 9;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            DeleteBoardCommentExample example = new DeleteBoardCommentExample();
            example.deleteBoardComment();
            example.deleteBoardCommentAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void deleteBoardComment() throws VkApiException {
        IntegerResponseBody responseBody = vk.board.deleteComment()
                .setTopicId(TOPIC_ID)
                .setCommentId(COMMENT_ID)
                .setGroupId(GROUP_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void deleteBoardCommentAsync() {
        CompletableFuture<IntegerResponseBody> future = vk.board.deleteComment()
                .setTopicId(TOPIC_ID)
                .setCommentId(COMMENT_ID)
                .setGroupId(GROUP_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
