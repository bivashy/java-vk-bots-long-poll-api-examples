package bot.longpoll.examples;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesGetByConversationMessageId;
import api.longpoll.bots.model.objects.additional.VkList;
import api.longpoll.bots.model.objects.basic.Message;
import api.longpoll.bots.model.response.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetMessageByConversationMessageIdExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetMessageByConversationMessageIdExample.class);
    private static final int PEER_ID = 2000000008;
    private static final List<Integer> CONVERSATION_MESSAGE_IDS = Collections.singletonList(6);

    public void getMessageByConversationMessageId() {
        try {
            GenericResult<VkList<Message>> result = new MessagesGetByConversationMessageId(this)
                    .setConversationMessageIds(CONVERSATION_MESSAGE_IDS)
                    .setPeerId(PEER_ID)
                    .setGroupId(getGroupId())
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getMessageByConversationMessageIdAsync() {
        CompletableFuture<GenericResult<VkList<Message>>> future = new MessagesGetByConversationMessageId(this)
                .setConversationMessageIds(CONVERSATION_MESSAGE_IDS)
                .setPeerId(PEER_ID)
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
        GetMessageByConversationMessageIdExample example = new GetMessageByConversationMessageIdExample();
        example.getMessageByConversationMessageId();
        example.getMessageByConversationMessageIdAsync();
    }
}
