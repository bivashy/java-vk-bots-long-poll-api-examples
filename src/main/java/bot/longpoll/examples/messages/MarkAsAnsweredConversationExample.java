package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class MarkAsAnsweredConversationExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarkAsAnsweredConversationExample.class);
    private static final int PEER_ID = 2000000001;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            MarkAsAnsweredConversationExample example = new MarkAsAnsweredConversationExample();
            example.markAsAnsweredConversation();
            example.markAsAnsweredConversationAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void markAsAnsweredConversation() throws VkApiException {
        IntegerResponseBody responseBody = vk.messages.markAsAnsweredConversation()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void markAsAnsweredConversationAsync() {
        CompletableFuture<IntegerResponseBody> future = vk.messages.markAsAnsweredConversation()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
