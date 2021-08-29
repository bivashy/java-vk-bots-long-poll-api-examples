package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class MarkAsImportantConversationExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(MarkAsImportantConversationExample.class);
    private static final int PEER_ID = 2000000001;

    public void markAsImportantConversation() {
        try {
            IntegerResponse response = vkBotsApi.messages().markAsImportantConversation()
                    .setGroupId(getGroupId())
                    .setPeerId(PEER_ID)
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void markAsImportantConversationAsync() {
        CompletableFuture<IntegerResponse> future = vkBotsApi.messages().markAsImportantConversation()
                .setGroupId(getGroupId())
                .setPeerId(PEER_ID)
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
        MarkAsImportantConversationExample example = new MarkAsImportantConversationExample();
        example.markAsImportantConversation();
        example.markAsImportantConversationAsync();
    }
}
