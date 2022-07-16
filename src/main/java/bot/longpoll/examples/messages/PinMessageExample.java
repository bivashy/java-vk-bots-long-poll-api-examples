package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Pin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class PinMessageExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(PinMessageExample.class);
    private static final int PEER_ID = 2000000001;
    private static final int CONVERSATION_MESSAGE_ID = 4539;

    public static void main(String[] args) {
        try {
            PinMessageExample example = new PinMessageExample();
            example.pinMessage();
            example.pinMessageAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void pinMessage() throws VkApiException {
        Pin.ResponseBody responseBody = vk.messages.pin()
                .setConversationMessageId(CONVERSATION_MESSAGE_ID)
                .setPeerId(PEER_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void pinMessageAsync() {
        CompletableFuture<Pin.ResponseBody> future = vk.messages.pin()
                .setConversationMessageId(CONVERSATION_MESSAGE_ID)
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
