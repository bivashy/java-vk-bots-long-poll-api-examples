package bot.longpoll.examples.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.groups.GetBanned;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetBannedExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetBannedExample.class);

    public static void main(String[] args) {
        try {
            GetBannedExample example = new GetBannedExample();
            example.getBanned();
            example.getBannedAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getBanned() throws VkApiException {
        GetBanned.Response response = vk.groups.getBanned()
                .setGroupId(getGroupId())
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void getBannedAsync() {
        CompletableFuture<GetBanned.Response> future = vk.groups.getBanned()
                .setGroupId(getGroupId())
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
