package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.utils.UtilsCheckLink;
import api.longpoll.bots.model.response.utils.UtilsCheckLinkResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CheckLinkExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(CheckLinkExample.class);
    private static final String URL = "https://google.com";

    public void checkLink() {
        try {
            UtilsCheckLinkResult result = new UtilsCheckLink(getAccessToken())
                    .setUrl(URL)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void checkLinkAsync() {
        CompletableFuture<UtilsCheckLinkResult> future = new UtilsCheckLink(getAccessToken())
                .setUrl(URL)
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
        CheckLinkExample example = new CheckLinkExample();
        example.checkLink();
        example.checkLinkAsync();
    }
}
