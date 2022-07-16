package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class EditMessageExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditMessageExample.class);
    private static final int PEER_ID = 918650328;
    private static final File GIF = new File("static/vibing_cat.gif");

    public static void main(String[] args) {
        try {
            EditMessageExample example = new EditMessageExample();
            example.editMessage();
            example.editMessageAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void editMessage() throws VkApiException {
        IntegerResponseBody responseBody = vk.messages.edit()
                .setPeerId(PEER_ID)
                .setMessageId(699)
                .setMessage("Corrected message")
                .addDoc(GIF)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void editMessageAsync() {
        CompletableFuture<IntegerResponseBody> future = vk.messages.edit()
                .setPeerId(PEER_ID)
                .setMessageId(700)
                .setMessage("Corrected message")
                .addDoc(GIF)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
