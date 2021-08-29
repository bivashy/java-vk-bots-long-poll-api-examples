package bot.longpoll.examples.wall;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.wall.CreateComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CreateCommentExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(CreateCommentExample.class);
    private static final int POST_ID = 9;

    public void createComment() {
        try {
            CreateComment.Response response = vkBotsApi.wall().createComment()
                    .setPostId(POST_ID)
                    .setOwnerId(-getGroupId())
                    .setMessage("Sync comment")
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void createCommentAsync() {
        CompletableFuture<CreateComment.Response> future = vkBotsApi.wall().createComment()
                .setPostId(POST_ID)
                .setOwnerId(-getGroupId())
                .setMessage("Async comment")
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
        CreateCommentExample example = new CreateCommentExample();
        example.createComment();
        example.createCommentAsync();
    }
}
