package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.utils.UtilsGetShortLink;
import api.longpoll.bots.model.response.utils.UtilsGetShortLinkResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetShortLinkExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetShortLinkExample.class);
    private static final String URL = "https://google.com";

    public void getShortLink() {
        try {
            UtilsGetShortLinkResult result = new UtilsGetShortLink(getAccessToken())
                    .setUrl(URL)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getShortLinkAsync() {
        CompletableFuture<UtilsGetShortLinkResult> future = new UtilsGetShortLink(getAccessToken())
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
        GetShortLinkExample example = new GetShortLinkExample();
        example.getShortLink();
        example.getShortLinkAsync();
    }
}
