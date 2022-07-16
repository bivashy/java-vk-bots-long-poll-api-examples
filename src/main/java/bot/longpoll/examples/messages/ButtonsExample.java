package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ButtonsExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(ButtonsExample.class);
    /**
     * Conversation ID.
     */
    private static final int PEER_ID = 918650328;

    public static void main(String[] args) {
        try {
            new ButtonsExample().sendButtons();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void sendButtons() throws VkApiException {
        Send.ResponseBody responseBody = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("What do you wish?") // message is mandatory
                .setKeyboard(createKeyboard())
                .execute();

        System.out.println("Send buttons responseBody: " + responseBody);
    }

    private Keyboard createKeyboard() {
        // button 1
        JsonObject payload = new JsonObject();
        payload.addProperty("order", "number 9");
        payload.addProperty("quantity", 2);
        Button number9s = new TextButton(Button.Color.POSITIVE, new TextButton.Action(
                "I'll have two number 9s",
                payload // payload is optional
        ));

        // button 2
        payload = new JsonObject();
        payload.addProperty("order", "number 9 large");
        Button number9large = new TextButton(Button.Color.NEGATIVE, new TextButton.Action(
                "a number 9 large",
                payload // payload is optional
        ));

        // button 3
        payload = new JsonObject();
        payload.addProperty("order", "primary");
        payload.addProperty("extra", "dip");
        Button number6extraDip = new TextButton(Button.Color.PRIMARY, new TextButton.Action(
                "a number 6 with extra dip",
                payload // payload is optional
        ));

        // button 4
        payload = new JsonObject();
        payload.addProperty("order", "number 7");
        Button number7 = new TextButton(Button.Color.SECONDARY, new TextButton.Action(
                "a number 7",
                payload // payload is optional
        ));

        List<Button> row1 = Arrays.asList(number9s, number9large);
        List<Button> row2 = Arrays.asList(number6extraDip, number7);

        return new Keyboard(Arrays.asList(row1, row2)).setInline(true);
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
