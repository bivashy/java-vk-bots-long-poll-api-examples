package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesDeleteChatPhoto;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.messages.MessagesDeleteChatPhotoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteChatPhotoExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(DeleteChatPhotoExample.class);
    private static final int CHAT_ID = 1;

    public void deleteChatPhoto() {
        try {
            GenericResult<MessagesDeleteChatPhotoResponse> result = new MessagesDeleteChatPhoto(this)
                    .setGroupId(getGroupId())
                    .setChatId(CHAT_ID)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void deleteChatPhotoAsync() {
        CompletableFuture<GenericResult<MessagesDeleteChatPhotoResponse>> future = new MessagesDeleteChatPhoto(this)
                .setGroupId(getGroupId())
                .setChatId(CHAT_ID)
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
        DeleteChatPhotoExample example = new DeleteChatPhotoExample();
        example.deleteChatPhoto();
        example.deleteChatPhotoAsync();
    }
}
