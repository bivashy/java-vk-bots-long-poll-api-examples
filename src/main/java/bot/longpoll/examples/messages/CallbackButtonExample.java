package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.EventData;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.CallbackButton;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class CallbackButtonExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackButtonExample.class);
    /**
     * Conversation ID.
     */
    private static final int PEER_ID = 918650328;

    public static void main(String[] args) {
        try {
            CallbackButtonExample callbackButtonExample = new CallbackButtonExample();
            callbackButtonExample.sendCallbackButton();
            callbackButtonExample.startPolling();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void sendCallbackButton() throws VkApiException {
        Button button = new CallbackButton(Button.Color.PRIMARY, new CallbackButton.Action("Callback button", null));
        Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                .setInline(true);

        Send.Response response = vk.messages.send()
                .setPeerId(PEER_ID)
                .setMessage("Callback button example")
                .setKeyboard(keyboard)
                .execute();

        System.out.println("Send callback button response: " + response);
    }

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        try {
            IntegerResponse response = vk.messages.sendEventAnswer()
                    .setUserId(messageEvent.getUserId())
                    .setPeerId(messageEvent.getPeerId())
                    .setEventId(messageEvent.getEventId())
                    .setEventData(new EventData.ShowSnackbar("Callback button was clicked!"))
                    .execute();

            System.out.println("Send event answer response: " + response);
            stopPolling();
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
