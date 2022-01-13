package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CreateChatExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateChatExample.class);
    private static final int USER_ID = 918650328;

    public static void main(String[] args) {
        try {
            CreateChatExample example = new CreateChatExample();
            example.createChat();
            example.createChatAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void createChat() throws VkApiException {
        IntegerResponse response = vk.messages.createChat()
                .setTitle("New Chat")
                .setUserIds(USER_ID)
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void createChatAsync() {
        CompletableFuture<IntegerResponse> future = vk.messages.createChat()
                .setTitle("New Chat Async")
                .setUserIds(USER_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
