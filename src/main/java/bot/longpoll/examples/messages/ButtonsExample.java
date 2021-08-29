package bot.longpoll.examples.messages;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.Button;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.Template;
import api.longpoll.bots.model.response.IntegerResponse;
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
            Button button1 = new Button()
                    .setAction(new Button.TextAction().setLabel("Positive button"))
                    .setColor(Button.ButtonColor.POSITIVE);
            Button button2 = new Button()
                    .setAction(new Button.TextAction().setLabel("Negative button"))
                    .setColor(Button.ButtonColor.NEGATIVE);
            Button button3 = new Button()
                    .setAction(new Button.TextAction().setLabel("Primary button"))
                    .setColor(Button.ButtonColor.PRIMARY);
            Button button4 = new Button()
                    .setAction(new Button.TextAction().setLabel("Secondary button"))
                    .setColor(Button.ButtonColor.SECONDARY);

            List<Button> row1 = Arrays.asList(button1, button2);
            List<Button> row2 = Arrays.asList(button3, button4);

            Keyboard keyboard = new Keyboard().setButtons(Arrays.asList(row1, row2));

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

    public void sendCarouselButtons() {
        try {
            Button button1 = new Button().setAction(new Button.TextAction().setLabel("button1"));
            Template.Carousel.Element element1 = new Template.Carousel.Element()
                    .setTitle("Title1")
                    .setDescription("Description1")
                    .setButtons(Collections.singletonList(button1));

            Button button2 = new Button().setAction(new Button.TextAction().setLabel("button2"));
            Template.Carousel.Element element2 = new Template.Carousel.Element()
                    .setTitle("Title2")
                    .setDescription("Description2")
                    .setButtons(Collections.singletonList(button2));

            Template.Carousel carousel = new Template.Carousel().setElements(Arrays.asList(element1, element2));

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("Carousel example")
                    .setTemplate(carousel)
                    .execute();

            System.out.println("Send carousel buttons response: " + response);
        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    public void sendCallbackButton() {
        try {
            Button button = new Button()
                    .setAction(new Button.CallbackAction().setLabel("Click me"))
                    .setColor(Button.ButtonColor.POSITIVE);
            Keyboard keyboard = new Keyboard()
                    .setButtons(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vkBotsApi.messages().send()
                    .setPeerId(PEER_ID)
                    .setMessage("A Callback button example")
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
                    .setEventData(new Button.ShowSnackbar().setText("Hi there!"))
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
            example.sendCarouselButtons();
            example.sendCallbackButton();
            botsLongPoll = new BotsLongPoll(example);
            botsLongPoll.run();
        } catch (VkApiException e) {
            log.error("Something went wrong...", e);
        }
    }
}
