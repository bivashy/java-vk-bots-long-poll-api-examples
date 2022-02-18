package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.SearchConversations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class SearchConversationsExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchConversationsExample.class);
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            SearchConversationsExample example = new SearchConversationsExample();
            example.searchConversations();
            example.searchConversationsAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void searchConversations() throws VkApiException {
        SearchConversations.Response response = vk.messages.searchConversations()
                .setGroupId(GROUP_ID)
                .setQ("java")
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void searchConversationsAsync() {
        CompletableFuture<SearchConversations.Response> future = vk.messages.searchConversations()
                .setGroupId(GROUP_ID)
                .setQ("java")
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
