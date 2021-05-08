package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesEdit;
import api.longpoll.bots.model.response.IntegerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class EditMessageExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(EditMessageExample.class);
    private static final int PEER_ID = 918650328;
    private static final File GIF = new File("static/vibing_cat.gif");

    public void editMessage() {
        try {
            IntegerResult result = new MessagesEdit(getAccessToken())
                    .setPeerId(PEER_ID)
                    .setMessageId(699)
                    .setMessage("Corrected message")
                    .addDoc(GIF)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void editMessageAsync() {
        CompletableFuture<IntegerResult> future = new MessagesEdit(getAccessToken())
                .setPeerId(PEER_ID)
                .setMessageId(700)
                .setMessage("Corrected message")
                .addDoc(GIF)
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
        EditMessageExample example = new EditMessageExample();
        example.editMessage();
        example.editMessageAsync();
    }
}
