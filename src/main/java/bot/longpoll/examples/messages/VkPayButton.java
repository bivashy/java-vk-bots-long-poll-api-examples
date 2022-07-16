package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.VKPayButton;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class VkPayButton extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(VkPayButton.class);

    /**
     * Conversation ID.
     */
    private static final int PEER_ID = 918650328;

    public static void main(String[] args) {
        try {
            new VkPayButton().sendVKPayButton();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void sendVKPayButton() throws VkApiException {
        JsonObject payload = new JsonObject();
        payload.addProperty("data", "some data");
        Button button = new VKPayButton(new VKPayButton.Action(
                "action=transfer-to-group&group_id=1&aid=10",
                payload // payload is optional
        ));
        Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                .setInline(true);

        Send.ResponseBody responseBody = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Please send money for new graphics card")
                .setKeyboard(keyboard)
                .execute();

        System.out.println("Send VK Pay button responseBody: " + responseBody);
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
