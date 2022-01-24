package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class PhotoExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoExample.class);
    private static final int PEER_ID = 918650328;
    private static final File PHOTO = new File("static/smiling_cat.png");

    public static void main(String[] args) {
        try {
            PhotoExample example = new PhotoExample();
            example.sendPhoto();
            example.sendPhotoAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void sendPhoto() throws VkApiException {
        Send.Response response = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Sent you photo:")
                .addPhoto(PHOTO)
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void sendPhotoAsync() {
        CompletableFuture<Send.Response> future = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Sent you photo async:")
                .addPhoto(PHOTO)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
