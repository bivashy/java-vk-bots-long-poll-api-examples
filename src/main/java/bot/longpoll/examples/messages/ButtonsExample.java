package bot.longpoll.examples.messages;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.methods.messages.MessagesSendEventAnswer;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.Button;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.Template;
import api.longpoll.bots.model.response.GenericResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonsExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(ButtonsExample.class);
    private static final Integer PEER_ID = 918650328;
    private static BotsLongPoll BOTS_LONG_POLL;

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

            GenericResult<Object> result = new MessagesSend(getAccessToken())
                    .setPeerId(PEER_ID)
                    .setMessage("Buttons example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send buttons result: " + result);
        } catch (BotsLongPollAPIException | BotsLongPollException e) {
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

            GenericResult<Object> result = new MessagesSend(getAccessToken())
                    .setPeerId(PEER_ID)
                    .setMessage("Carousel example")
                    .setTemplate(carousel)
                    .execute();

            System.out.println("Send carousel buttons result: " + result);
        } catch (BotsLongPollAPIException | BotsLongPollException e) {
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

            GenericResult<Object> result = new MessagesSend(getAccessToken())
                    .setPeerId(PEER_ID)
                    .setMessage("A Callback button example")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send callback button result: " + result);
        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        try {
            GenericResult<Integer> result = new MessagesSendEventAnswer(getAccessToken())
                    .setUserId(messageEvent.getUserId())
                    .setPeerId(messageEvent.getPeerId())
                    .setEventId(messageEvent.getEventId())
                    .setEventData(new Button.ShowSnackbar().setText("Hi there!"))
                    .execute();

            System.out.println("Send event answer result: " + result);
            BOTS_LONG_POLL.stop();
        } catch (BotsLongPollAPIException | BotsLongPollException e) {
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
            BOTS_LONG_POLL = new BotsLongPoll(example);
            BOTS_LONG_POLL.run();
        } catch (BotsLongPollException | BotsLongPollAPIException e) {
            log.error("Something went wrong...", e);
        }
    }
}
