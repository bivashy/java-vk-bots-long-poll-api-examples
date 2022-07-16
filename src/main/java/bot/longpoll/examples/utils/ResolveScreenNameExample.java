package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.utils.ResolveScreenName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class ResolveScreenNameExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResolveScreenNameExample.class);
    private static final String SCREEN_NAME = "durov";

    public static void main(String[] args) {
        try {
            ResolveScreenNameExample example = new ResolveScreenNameExample();
            example.resolveScreenName();
            example.resolveScreenNameAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void resolveScreenName() throws VkApiException {
        ResolveScreenName.ResponseBody responseBody = vk.utils.resolveScreenName()
                .setScreenName(SCREEN_NAME)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void resolveScreenNameAsync() {
        CompletableFuture<ResolveScreenName.ResponseBody> future = vk.utils.resolveScreenName()
                .setScreenName(SCREEN_NAME)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
