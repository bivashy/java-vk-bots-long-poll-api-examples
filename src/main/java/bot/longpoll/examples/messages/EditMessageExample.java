package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.additional.VkAttachment;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public class EditMessageExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(EditMessageExample.class);
    private static final int PEER_ID = 918650328;
    private static final File GIF = new File("static/vibing_cat.gif");

    public void editMessage() throws IOException {
        try {
            VkAttachment vkAttachment = attachDoc();
            IntegerResponse response = vk.messages.edit()
                    .setPeerId(PEER_ID)
                    .setMessageId(699)
                    .setMessage("Corrected message")
                    .setAttachments(vkAttachment)
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void editMessageAsync() throws VkApiException, IOException {
        VkAttachment vkAttachment = attachDoc();
        CompletableFuture<IntegerResponse> future = vk.messages.edit()
                .setPeerId(PEER_ID)
                .setMessageId(700)
                .setMessage("Corrected message")
                .setAttachments(vkAttachment)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    public VkAttachment attachDoc() throws VkApiException, IOException {
        try (InputStream inputStream = new FileInputStream(GIF)) {
            return vk.messages.attachDoc()
                    .setType("doc")
                    .setPeerId(PEER_ID)
                    .setDoc(GIF.getName(), inputStream)
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
        EditMessageExample example = new EditMessageExample();
        example.editMessage();
        example.editMessageAsync();
    }
}
