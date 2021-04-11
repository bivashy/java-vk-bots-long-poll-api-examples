package bot.longpoll.examples;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesEditChat;
import api.longpoll.bots.model.response.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class EditChatExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(EditChatExample.class);
    private static final int CHAT_ID = 8;

    public void editChat() {
        try {
            GenericResult<Integer> result = new MessagesEditChat(this)
                    .setChatId(CHAT_ID)
                    .setTitle("Title changed sync")
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void editChatAsync() {
        CompletableFuture<GenericResult<Integer>> future = new MessagesEditChat(this)
                .setChatId(CHAT_ID)
                .setTitle("Title changed async")
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
        EditChatExample example = new EditChatExample();
        example.editChat();
        example.editChatAsync();
    }
}
