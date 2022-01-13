package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.OpenLinkButton;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class OpenLinkButtonExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenLinkButtonExample.class);

    /**
     * Conversation ID.
     */
    private static final int PEER_ID = 918650328;

    public static void main(String[] args) {
        try {
            new OpenLinkButtonExample().sendOpenLinkButton();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void sendOpenLinkButton() throws VkApiException {
        JsonObject payload = new JsonObject();
        payload.addProperty("data", "some data");
        Button button = new OpenLinkButton(new OpenLinkButton.Action(
                "https://github.com/yvasyliev/java-vk-bots-long-poll-api",
                "Java VK Bots Long Poll API",
                payload // payload is optional
        ));
        Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                .setInline(true);

        Send.Response response = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Could you please add a star to this project?")
                .setKeyboard(keyboard)
                .execute();

        System.out.println("Send Open Link button response: " + response);
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
