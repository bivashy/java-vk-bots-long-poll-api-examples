package bot.longpoll.examples.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.groups.IsMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class IsMemberExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsMemberExample.class);
    private static final int USER_ID = 918650328;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            IsMemberExample example = new IsMemberExample();
            example.isMember();
            example.isMemberAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void isMember() throws VkApiException {
        IsMember.ResponseBody responseBody = vk.groups.isMember()
                .setGroupId(GROUP_ID)
                .setUserId(USER_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void isMemberAsync() {
        CompletableFuture<IsMember.ResponseBody> future = vk.groups.isMember()
                .setGroupId(GROUP_ID)
                .setUserId(USER_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
