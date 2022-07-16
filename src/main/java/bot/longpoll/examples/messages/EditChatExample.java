package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class EditChatExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditChatExample.class);
    private static final int CHAT_ID = 8;

    public static void main(String[] args) {
        try {
            EditChatExample example = new EditChatExample();
            example.editChat();
            example.editChatAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void editChat() throws VkApiException {
        IntegerResponseBody responseBody = vk.messages.editChat()
                .setChatId(CHAT_ID)
                .setTitle("Title changed sync")
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void editChatAsync() {
        CompletableFuture<IntegerResponseBody> future = vk.messages.editChat()
                .setChatId(CHAT_ID)
                .setTitle("Title changed async")
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
