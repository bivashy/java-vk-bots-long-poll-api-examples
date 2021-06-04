package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesGetConversationMembers;
import api.longpoll.bots.model.response.messages.MessagesGetConversationMembersResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetConversationMembersExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetConversationMembersExample.class);
    private static final int PEER_ID = 2000000008;

    public void editMessage() {
        try {
            MessagesGetConversationMembersResult result = new MessagesGetConversationMembers(getAccessToken())
                    .setPeerId(PEER_ID)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void editMessageAsync() {
        CompletableFuture<MessagesGetConversationMembersResult> future = new MessagesGetConversationMembers(getAccessToken())
                .setPeerId(PEER_ID)
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
        GetConversationMembersExample example = new GetConversationMembersExample();
        example.editMessage();
        example.editMessageAsync();
    }
}
