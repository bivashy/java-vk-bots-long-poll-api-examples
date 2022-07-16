package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversationMembers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetConversationMembersExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetConversationMembersExample.class);
    private static final int PEER_ID = 2000000008;

    public static void main(String[] args) {
        try {
            GetConversationMembersExample example = new GetConversationMembersExample();
            example.editMessage();
            example.editMessageAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void editMessage() throws VkApiException {
        GetConversationMembers.ResponseBody responseBody = vk.messages.getConversationMembers()
                .setPeerId(PEER_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void editMessageAsync() {
        CompletableFuture<GetConversationMembers.ResponseBody> future = vk.messages.getConversationMembers()
                .setPeerId(PEER_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
