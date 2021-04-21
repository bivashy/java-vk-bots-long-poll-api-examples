package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesGetConversationsById;
import api.longpoll.bots.model.objects.basic.Conversation;
import api.longpoll.bots.model.response.ExtendedVkList;
import api.longpoll.bots.model.response.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetConversationsByIdExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetConversationsByIdExample.class);
    private static final List<Integer> PEER_IDS = Collections.singletonList(2000000008);

    public void getConversationsById() {
        try {
            GenericResult<ExtendedVkList<Conversation>> result = new MessagesGetConversationsById(getAccessToken())
                    .setGroupId(getGroupId())
                    .setPeerIds(PEER_IDS)
                    .setExtended(true)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void editMessageAsync() {
        CompletableFuture<GenericResult<ExtendedVkList<Conversation>>> future = new MessagesGetConversationsById(getAccessToken())
                .setGroupId(getGroupId())
                .setPeerIds(PEER_IDS)
                .setExtended(true)
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
        GetConversationsByIdExample example = new GetConversationsByIdExample();
        example.getConversationsById();
        example.editMessageAsync();
    }
}
