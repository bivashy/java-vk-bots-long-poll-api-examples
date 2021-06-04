package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesCreateChat;
import api.longpoll.bots.model.response.IntegerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CreateChatExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(CreateChatExample.class);
    private static final List<Integer> USER_IDS = Collections.singletonList(918650328);

    public void createChat() {
        try {
            IntegerResult result = new MessagesCreateChat(getAccessToken())
                    .setTitle("New Chat")
                    .setUserIds(USER_IDS)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void createChatAsync() {
        CompletableFuture<IntegerResult> future = new MessagesCreateChat(getAccessToken())
                .setTitle("New Char Async")
                .setUserIds(USER_IDS)
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
        CreateChatExample example = new CreateChatExample();
        example.createChat();
        example.createChatAsync();
    }
}
