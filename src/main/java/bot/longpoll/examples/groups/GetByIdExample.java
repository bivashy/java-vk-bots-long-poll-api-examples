package bot.longpoll.examples.groups;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.groups.GroupsGetById;
import api.longpoll.bots.model.response.groups.GroupsGetByIdResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetByIdExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetByIdExample.class);

    public void getById() {
        try {
            GroupsGetByIdResult result = new GroupsGetById(getAccessToken())
                    .setGroupId(String.valueOf(getGroupId()))
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getByIdAsync() {
        CompletableFuture<GroupsGetByIdResult> future = new GroupsGetById(getAccessToken())
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
        GetByIdExample example = new GetByIdExample();
        example.getById();
        example.getByIdAsync();
    }
}
