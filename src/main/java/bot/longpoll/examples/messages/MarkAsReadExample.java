package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class MarkAsReadExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarkAsReadExample.class);
    private static final int PEER_ID = 2000000001;
    private static final int START_MESSAGE_ID = 4539;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            MarkAsReadExample example = new MarkAsReadExample();
            example.markAsRead();
            example.markAsReadAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void markAsRead() throws VkApiException {
        IntegerResponse response = vk.messages.markAsRead()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .setStartMessageId(START_MESSAGE_ID)
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void markAsReadAsync() {
        CompletableFuture<IntegerResponse> future = vk.messages.markAsRead()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .setStartMessageId(START_MESSAGE_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
