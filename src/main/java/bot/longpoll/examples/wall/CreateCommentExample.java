package bot.longpoll.examples.wall;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.wall.CreateComment;
import api.longpoll.bots.model.objects.additional.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CreateCommentExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCommentExample.class);
    private static final int POST_ID = 9;
    private static final int MEDIA_ID = 457239025;
    private static final int GROUP_ID = 886761559;
    private static final UploadedFile UPLOADED_FILE = new UploadedFile("photo", -GROUP_ID, MEDIA_ID, null);

    public static void main(String[] args) {
        try {
            CreateCommentExample example = new CreateCommentExample();
            example.createComment();
            example.createCommentAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void createComment() throws VkApiException {
        CreateComment.ResponseBody responseBody = vk.wall.createComment()
                .setPostId(POST_ID)
                .setOwnerId(-GROUP_ID)
                .setMessage("Sync comment")
                .setAttachment(UPLOADED_FILE)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void createCommentAsync() {
        CompletableFuture<CreateComment.ResponseBody> future = vk.wall.createComment()
                .setPostId(POST_ID)
                .setOwnerId(-GROUP_ID)
                .setMessage("Async comment")
                .setAttachment(UPLOADED_FILE)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
