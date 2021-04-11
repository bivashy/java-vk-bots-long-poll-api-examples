package bot.longpoll.examples;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesGetHistory;
import api.longpoll.bots.model.response.GenericResult;
import api.longpoll.bots.model.response.messages.MessagesGetHistoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetHistoryExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetHistoryExample.class);
    private static final int PEER_ID = 918650328;

    public void getHistory() {
        try {
            GenericResult<MessagesGetHistoryResponse> result = new MessagesGetHistory(this)
                    .setPeerId(PEER_ID)
                    .setGroupId(getGroupId())
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getHistoryAsync() {
        CompletableFuture<GenericResult<MessagesGetHistoryResponse>> future = new MessagesGetHistory(this)
                .setPeerId(PEER_ID)
                .setGroupId(getGroupId())
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
        GetHistoryExample example = new GetHistoryExample();
        example.getHistory();
        example.getHistoryAsync();
    }
}
