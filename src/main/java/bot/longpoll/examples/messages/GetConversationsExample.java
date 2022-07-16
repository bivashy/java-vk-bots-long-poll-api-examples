package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetConversationsExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetConversationsExample.class);
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            GetConversationsExample example = new GetConversationsExample();
            example.getConversations();
            example.getConversationsAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getConversations() throws VkApiException {
        GetConversations.ResponseBody responseBody = vk.messages.getConversations()
                .setGroupId(GROUP_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getConversationsAsync() {
        CompletableFuture<GetConversations.ResponseBody> future = vk.messages.getConversations()
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
