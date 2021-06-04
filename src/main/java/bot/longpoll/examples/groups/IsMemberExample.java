package bot.longpoll.examples.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.groups.GroupsIsMember;
import api.longpoll.bots.model.response.groups.GroupsIsMemberResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class IsMemberExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(IsMemberExample.class);
    private static final int USER_ID = 918650328;

    public void isMember() {
        try {
            GroupsIsMemberResult result = new GroupsIsMember(getAccessToken())
                    .setGroupId(String.valueOf(getGroupId()))
                    .setUserId(USER_ID)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void isMemberAsync() {
        CompletableFuture<GroupsIsMemberResult> future = new GroupsIsMember(getAccessToken())
                .setGroupId(String.valueOf(getGroupId()))
                .setUserId(USER_ID)
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
        IsMemberExample example = new IsMemberExample();
        example.isMember();
        example.isMemberAsync();
    }
}
