package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetByConversationMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetMessageByConversationMessageIdExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageByConversationMessageIdExample.class);
    private static final int PEER_ID = 2000000008;
    private static final int CONVERSATION_MESSAGE_ID = 6;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            GetMessageByConversationMessageIdExample example = new GetMessageByConversationMessageIdExample();
            example.getMessageByConversationMessageId();
            example.getMessageByConversationMessageIdAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getMessageByConversationMessageId() throws VkApiException {
        GetByConversationMessageId.ResponseBody responseBody = vk.messages.getByConversationMessageId()
                .setConversationMessageIds(CONVERSATION_MESSAGE_ID)
                .setPeerId(PEER_ID)
                .setGroupId(GROUP_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getMessageByConversationMessageIdAsync() {
        CompletableFuture<GetByConversationMessageId.ResponseBody> future = vk.messages.getByConversationMessageId()
                .setConversationMessageIds(CONVERSATION_MESSAGE_ID)
                .setPeerId(PEER_ID)
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
