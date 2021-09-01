package bot.longpoll.examples.messages;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.EventData;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.Template;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.CallbackButton;
import api.longpoll.bots.model.objects.additional.buttons.LocationButton;
import api.longpoll.bots.model.objects.additional.buttons.OpenLinkButton;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.additional.buttons.VKAppsButton;
import api.longpoll.bots.model.objects.additional.buttons.VKPayButton;
import api.longpoll.bots.model.objects.additional.carousel.Carousel;
import api.longpoll.bots.model.objects.additional.carousel.Element;
import api.longpoll.bots.model.response.IntegerResponse;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonsExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(ButtonsExample.class);
    private static final Integer PEER_ID = 918650328;
    private static BotsLongPoll botsLongPoll;

    public void sendButtons() {
        try {
            JsonObject positiveButtonPayload = new JsonObject();
            positiveButtonPayload.addProperty("button", "positive");
            Button positiveButton = new TextButton(Button.Color.POSITIVE, new TextButton.Action(
                    "Positive button",
                    positiveButtonPayload
            ));

            JsonObject negativeButtonPayload = new JsonObject();
            negativeButtonPayload.addProperty("button", "negative");
            Button negativeButton = new TextButton(Button.Color.NEGATIVE, new TextButton.Action(
                    "Negative button",
                    negativeButtonPayload
            ));

            JsonObject primaryButtonPayload = new JsonObject();
            primaryButtonPayload.addProperty("button", "primary");
            Button primaryButton = new TextButton(Button.Color.PRIMARY, new TextButton.Action(
                    "Primary button",
                    primaryButtonPayload
            ));

            JsonObject secondaryButtonPayload = new JsonObject();
            secondaryButtonPayload.addProperty("button", "secondary");
            Button secondaryButton = new TextButton(Button.Color.SECONDARY, new TextButton.Action(
                    "Secondary button",
                    secondaryButtonPayload
            ));

            List<Button> row1 = Arrays.asList(positiveButton, negativeButton);
            List<Button> row2 = Arrays.asList(primaryButton, secondaryButton);

            Keyboard keyboard = new Keyboard(Arrays.asList(row1, row2));

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Buttons example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send buttons response: " + response);
        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void sendLocationButton() {
        try {
            Button button = new LocationButton(new LocationButton.Action(null));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Location button")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send location button response: " + response);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    public void sendOpenLinkButton() {
        try {
            Button button = new OpenLinkButton(new OpenLinkButton.Action(
                    "https://github.com/yvasyliev/java-vk-bots-long-poll-api",
                    "Open Link button",
                    null
            ));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Open Link button example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send Open Link button response: " + response);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    public void sendVKAppsButton() {
        try {
            Button button = new VKAppsButton(new VKAppsButton.Action(
                    123,
                    456,
                    "VK Apps button",
                    null,
                    null
            ));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("VK Apps button example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send VK Apps button response: " + response);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    public void sendVKPayButton() {
        try {
            Button button = new VKPayButton(new VKPayButton.Action(
                    "action=transfer-to-group&group_id=1&aid=10",
                    null
            ));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("VK Pay button example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send VK Pay button response: " + response);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    public void sendCarousel() {
        try {
            Button button1 = new TextButton(Button.Color.POSITIVE, new TextButton.Action("button1", null));
            Element element1 = new Element()
                    .setTitle("Title1")
                    .setDescription("Description1")
                    .setButtons(button1);

            Button button2 = new TextButton(Button.Color.NEGATIVE, new TextButton.Action("button2", null));
            Element element2 = new Element()
                    .setTitle("Title2")
                    .setDescription("Description2")
                    .setButtons(button2);

            Template carousel = new Carousel(Arrays.asList(element1, element2));

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Carousel example")
                    .setTemplate(carousel)
                    .execute();

            System.out.println("Send carousel response: " + response);
        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void sendCallbackButton() {
        try {
            Button button = new CallbackButton(Button.Color.PRIMARY, new CallbackButton.Action("Callback button", null));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Callback button example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send callback button response: " + response);
        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        try {
            IntegerResponse response = vkBotsApi.messages().sendEventAnswer()
                    .setUserId(messageEvent.getUserId())
                    .setPeerId(messageEvent.getPeerId())
                    .setEventId(messageEvent.getEventId())
                    .setEventData(new EventData.ShowSnackbar("Callback button was clicked!"))
                    .execute();

            System.out.println("Send event answer response: " + response);
            botsLongPoll.stop();
        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
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
            ButtonsExample example = new ButtonsExample();
            example.sendButtons();
            example.sendLocationButton();
            example.sendOpenLinkButton();
            example.sendVKAppsButton();
            example.sendVKPayButton();
            example.sendCarousel();
            example.sendCallbackButton();
            botsLongPoll = new BotsLongPoll(example);
            botsLongPoll.run();
        } catch (VkApiException e) {
            log.error("Something went wrong...", e);
        }
    }
}
