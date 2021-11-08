package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.LocationButton;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class LocationButtonExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(LocationButtonExample.class);
    /**
     * Conversation ID.
     */
    private static final Integer PEER_ID = 918650328;

    public void sendLocationButton() throws VkApiException {
        JsonObject payload = new JsonObject();
        payload.addProperty("asking", PEER_ID);
        Button button = new LocationButton(new LocationButton.Action(payload)); // payload is optional
        Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                .setInline(true);

        Send.Response response = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Please share your location. I promise no one will know.")
                .setKeyboard(keyboard)
                .execute();

        System.out.println("Send location button response: " + response);

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
        try {
            new LocationButtonExample().sendLocationButton();
        } catch (VkApiException e) {
            log.error("Something went wrong...", e);
        }
    }
}
