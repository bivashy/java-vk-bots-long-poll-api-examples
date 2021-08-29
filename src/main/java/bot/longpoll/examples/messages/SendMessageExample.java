package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.http.params.MessagePhoto;
import api.longpoll.bots.methods.impl.messages.Send;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class SendMessageExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(SendMessageExample.class);
    private static final int PEER_ID = 918650328;
    private static final File PHOTO = new File("static/smiling_cat.png");

    public void sendMessage() {
        try {
            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Sent you photo:")
                    .setAttachments(new MessagePhoto(getAccessToken(), PEER_ID, PHOTO))
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void sendMessageAsync() {
        CompletableFuture<Send.Response> future = vkBotsApi.messages().send()
                .setPeerId(PEER_ID)
                .setMessage("Sent you photo async:")
                .setAttachments(new MessagePhoto(getAccessToken(), PEER_ID, PHOTO))
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
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
        SendMessageExample example = new SendMessageExample();
        example.sendMessage();
        example.sendMessageAsync();
    }
}
