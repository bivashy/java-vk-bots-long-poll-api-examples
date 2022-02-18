package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteMessageExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMessageExample.class);
    private static final int PEER_ID = 2000000008;
    private static final int CONVERSATION_MESSAGE_ID = 37;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            DeleteMessageExample example = new DeleteMessageExample();
            example.deleteMessage();
            example.deleteMessageAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void deleteMessage() throws VkApiException {
        Delete.Response response = vk.messages.delete()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .setConversationMessageIds(CONVERSATION_MESSAGE_ID)
                .setDeleteForAll(true)
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void deleteMessageAsync() {
        CompletableFuture<Delete.Response> future = vk.messages.delete()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .setConversationMessageIds(CONVERSATION_MESSAGE_ID)
                .setDeleteForAll(true)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
