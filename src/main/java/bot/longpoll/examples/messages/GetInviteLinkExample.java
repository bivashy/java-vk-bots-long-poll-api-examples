package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetInviteLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetInviteLinkExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetInviteLinkExample.class);
    private static final int PEER_ID = 2000000001;

    public void getInviteLink() {
        try {
            GetInviteLink.Response response = vkBotsApi.messages().getInviteLink()
                    .setPeerId(PEER_ID)
                    .setGroupId(getGroupId())
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getInviteLinkAsync() {
        CompletableFuture<GetInviteLink.Response> future = vkBotsApi.messages().getInviteLink()
                .setPeerId(PEER_ID)
                .setGroupId(getGroupId())
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
        GetInviteLinkExample example = new GetInviteLinkExample();
        example.getInviteLink();
        example.getInviteLinkAsync();
    }
}
