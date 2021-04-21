package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.response.GenericResult;
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
            GenericResult<Object> result = new MessagesSend(getAccessToken())
                    .setPeerId(PEER_ID)
                    .setMessage("Sent you photo:")
                    .addPhoto(PHOTO)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void sendMessageAsync() {
        CompletableFuture<GenericResult<Object>> future = new MessagesSend(getAccessToken())
                .setPeerId(PEER_ID)
                .setMessage("Sent you photo async:")
                .addPhoto(PHOTO)
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
        SendMessageExample example = new SendMessageExample();
        example.sendMessage();
        example.sendMessageAsync();
    }
}
