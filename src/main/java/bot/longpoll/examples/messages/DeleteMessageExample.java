package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesDelete;
import api.longpoll.bots.model.response.messages.MessagesDeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DeleteMessageExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(DeleteMessageExample.class);
    private static final List<Integer> MESSAGE_IDS = Collections.singletonList(726);

    public void deleteMessage() {
        try {
            MessagesDeleteResult result = new MessagesDelete(getAccessToken())
                    .setDeleteForAll(true)
                    .setMessageIds(MESSAGE_IDS)
                    .setGroupId(getGroupId())
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void deleteMessageAsync() {
        CompletableFuture<MessagesDeleteResult> future = new MessagesDelete(getAccessToken())
                .setDeleteForAll(true)
                .setMessageIds(MESSAGE_IDS)
                .setGroupId(getGroupId())
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
        DeleteMessageExample example = new DeleteMessageExample();
        example.deleteMessage();
        example.deleteMessageAsync();
    }
}
