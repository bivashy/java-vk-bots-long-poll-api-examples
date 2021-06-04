package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.utils.UtilsResolveScreenName;
import api.longpoll.bots.model.response.utils.UtilsResolveScreenNameResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class ResolveScreenNameExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(ResolveScreenNameExample.class);
    private static final String SCREEN_NAME = "durov";

    public void resolveScreenName() {
        try {
            UtilsResolveScreenNameResult result = new UtilsResolveScreenName(getAccessToken())
                    .setScreenName(SCREEN_NAME)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void resolveScreenNameAsync() {
        CompletableFuture<UtilsResolveScreenNameResult> future = new UtilsResolveScreenName(getAccessToken())
                .setScreenName(SCREEN_NAME)
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
        ResolveScreenNameExample example = new ResolveScreenNameExample();
        example.resolveScreenName();
        example.resolveScreenNameAsync();
    }
}
