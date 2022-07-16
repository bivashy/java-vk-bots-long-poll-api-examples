package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetMessageByIdExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetMessageByIdExample.class);
    private static final int MESSAGE_ID = 718;

    public static void main(String[] args) {
        try {
            GetMessageByIdExample example = new GetMessageByIdExample();
            example.getMessageById();
            example.getMessageByIdAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getMessageById() throws VkApiException {
        GetById.ResponseBody responseBody = vk.messages.getById()
                .setMessageIds(MESSAGE_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getMessageByIdAsync() {
        CompletableFuture<GetById.ResponseBody> future = vk.messages.getById()
                .setMessageIds(MESSAGE_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
