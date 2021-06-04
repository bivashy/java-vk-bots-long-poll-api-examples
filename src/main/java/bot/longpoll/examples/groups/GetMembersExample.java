package bot.longpoll.examples.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.groups.GroupsGetMembers;
import api.longpoll.bots.model.response.groups.GroupsGetMembersResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class GetMembersExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetMembersExample.class);

    public void getMembers() {
        try {
            GroupsGetMembersResult result = new GroupsGetMembers(getAccessToken())
                    .setGroupId(String.valueOf(getGroupId()))
                    .execute();

            System.out.println("Sync result: " + result);
            result = new GroupsGetMembers(getAccessToken())
                    .setGroupId(String.valueOf(getGroupId()))
                    .setFields(Collections.singletonList("bdate"))
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getMembersAsync() {
        CompletableFuture<GroupsGetMembersResult> future = new GroupsGetMembers(getAccessToken())
                .setGroupId(String.valueOf(getGroupId()))
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
        GetMembersExample example = new GetMembersExample();
        example.getMembers();
        //example.getMembersAsync();
    }
}
