package bot.longpoll.examples.users;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.users.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetUserExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserExample.class);
    private static final String USER_ID = "918650328";

    public static void main(String[] args) {
        try {
            GetUserExample example = new GetUserExample();
            example.getUser();
            example.getUserAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getUser() throws VkApiException {
        Get.ResponseBody responseBody = vk.users.get()
                .setUserIds(USER_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getUserAsync() {
        CompletableFuture<Get.ResponseBody> future = vk.users.get()
                .setUserIds(USER_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
