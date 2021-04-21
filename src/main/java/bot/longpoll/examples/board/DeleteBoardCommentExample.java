package bot.longpoll.examples.board;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.board.BoardDeleteComment;
import api.longpoll.bots.model.response.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteBoardCommentExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(DeleteBoardCommentExample.class);
    private static final int TOPIC_ID = 47564267;
    private static final int COMMENT_ID = 9;

    public void deleteBoardComment() {
        try {
            GenericResult<Integer> result = new BoardDeleteComment(getAccessToken())
                    .setTopicId(TOPIC_ID)
                    .setCommentId(COMMENT_ID)
                    .setGroupId(getGroupId())
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void deleteBoardCommentAsync() {
        CompletableFuture<GenericResult<Integer>> future = new BoardDeleteComment(getAccessToken())
                .setTopicId(TOPIC_ID)
                .setCommentId(COMMENT_ID)
                .setGroupId(getGroupId())
                .executeAsync();

        // Main thread is free...

        System.out.println("Async result: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "004a7677ca1492027fa6e974b9be3f8f650f691f8e5e587c4cf39e7b0cb678f0ff4ca47b58fe4c9b8b59c";
    }

    @Override
    public int getGroupId() {
        return 168975658;
    }

    public static void main(String[] args) {
        DeleteBoardCommentExample example = new DeleteBoardCommentExample();
        example.deleteBoardComment();
        example.deleteBoardCommentAsync();
    }
}
