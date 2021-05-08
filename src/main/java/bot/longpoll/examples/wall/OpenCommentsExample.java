package bot.longpoll.examples.wall;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.wall.WallOpenComments;
import api.longpoll.bots.model.response.IntegerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class OpenCommentsExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(OpenCommentsExample.class);
    private static final int POST_ID = 9;

    public void openComments() {
        try {
            IntegerResult result = new WallOpenComments(getAccessToken())
                    .setPostId(POST_ID)
                    .setOwnerId(-getGroupId())
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void openCommentsAsync() {
        CompletableFuture<IntegerResult> future = new WallOpenComments(getAccessToken())
                .setPostId(POST_ID)
                .setOwnerId(-getGroupId())
                .executeAsync();

        // Main thread is free...

        System.out.println("Async result: " + future.join());
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
        OpenCommentsExample example = new OpenCommentsExample();
        example.openComments();
        example.openCommentsAsync();
    }
}
