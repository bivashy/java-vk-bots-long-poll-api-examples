package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.additional.VkAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public class SendMessageExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(SendMessageExample.class);
    private static final int PEER_ID = 918650328;
    private static final File PHOTO = new File("static/smiling_cat.png");

    public void sendMessage() throws IOException {
        try {
            VkAttachment vkAttachment = attachPhoto();
            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("Sent you photo:")
                    .setAttachments(vkAttachment)
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void sendMessageAsync() throws VkApiException, IOException {
        VkAttachment vkAttachment = attachPhoto();
        CompletableFuture<Send.Response> future = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Sent you photo async:")
                .setAttachments(vkAttachment)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    private VkAttachment attachPhoto() throws VkApiException, IOException {
        try (InputStream inputStream = new FileInputStream(PHOTO)) {
            return vk.messages.attachPhoto()
                    .setPeerId(PEER_ID)
                    .setPhoto(PHOTO.getName(), inputStream)
                    .execute();
        }
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }

    @Override
    public int getGroupId() {
        return 886761559;
    }

    public static void main(String[] args) throws VkApiException, IOException {
        SendMessageExample example = new SendMessageExample();
        example.sendMessage();
        example.sendMessageAsync();
    }
}
