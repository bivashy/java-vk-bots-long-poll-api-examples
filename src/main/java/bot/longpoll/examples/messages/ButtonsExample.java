package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.methods.impl.photos.GetMessagesUploadServer;
import api.longpoll.bots.methods.impl.photos.SaveMessagesPhoto;
import api.longpoll.bots.methods.impl.upload.UploadPhoto;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonsExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(ButtonsExample.class);
    private static final Integer PEER_ID = 918650328;

    public void sendButtons() {
        try {
            JsonObject payload = new JsonObject();
            payload.addProperty("order", "number 9");
            payload.addProperty("quantity", 2);
            Button number9s = new TextButton(Button.Color.POSITIVE, new TextButton.Action(
                    "I'll have two number 9s",
                    payload // payload is optional
            ));

            payload = new JsonObject();
            payload.addProperty("order", "number 9 large");
            Button negativeButton = new TextButton(Button.Color.NEGATIVE, new TextButton.Action(
                    "a number 9 large",
                    payload // payload is optional
            ));

            payload = new JsonObject();
            payload.addProperty("order", "primary");
            payload.addProperty("extra", "dip");
            Button primaryButton = new TextButton(Button.Color.PRIMARY, new TextButton.Action(
                    "a number 6 with extra dip",
                    payload // payload is optional
            ));

            payload = new JsonObject();
            payload.addProperty("order", "number 7");
            Button secondaryButton = new TextButton(Button.Color.SECONDARY, new TextButton.Action(
                    "a number 7",
                    payload // payload is optional
            ));

            List<Button> row1 = Arrays.asList(number9s, negativeButton);
            List<Button> row2 = Arrays.asList(primaryButton, secondaryButton);

            Keyboard keyboard = new Keyboard(Arrays.asList(row1, row2));
            //keyboard.setInline(true); // keyboard can be inline
            //keyboard.setOneTime(true); // keyboard can be hidden after button clicked

            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("What do you wish?")
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

            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("Please share your location. I promise no one will know.")
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
                    "Java VK Bots Long Poll API"
                    // your payload can be here
            ));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("Could you please add a star to this project?")
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
                    "VK App",
                    "hash_string"
                    // your payload can be here
            ));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("Check out this app!")
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
                    "action=transfer-to-group&group_id=1&aid=10"
                    // your payload can be here
            ));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("Please send money for new graphics card")
                    .setKeyboard(keyboard)
                    .execute();

            System.out.println("Send VK Pay button response: " + response);
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    public void sendCarousel() throws IOException {
        try {
            SaveMessagesPhoto.Response.ResponseObject savedPhoto = uploadPhoto(new File("static/blaming.jpg"));
            Button button1 = new TextButton(Button.Color.POSITIVE, new TextButton.Action("button1"));
            Element element1 = new Element()
                    .setPhotoId(savedPhoto.getOwnerId(), savedPhoto.getId())
                    .setTitle("Title1")
                    .setDescription("Description1")
                    .setButtons(button1);

            savedPhoto = uploadPhoto(new File("static/not_understanding.jpg"));
            Button button2 = new TextButton(Button.Color.NEGATIVE, new TextButton.Action("button2"));
            Element element2 = new Element()
                    .setPhotoId(savedPhoto.getOwnerId(), savedPhoto.getId())
                    .setTitle("Title2")
                    .setDescription("Description2")
                    .setButtons(button2);

            Template carousel = new Carousel(Arrays.asList(element1, element2));

            Send.Response response = vk.messages.send()
                    .setPeerId(PEER_ID)
                    .setMessage("Carousel example")
                    .setTemplate(carousel)
                    .execute();

            System.out.println("Send carousel response: " + response);
        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        }
    }

    private SaveMessagesPhoto.Response.ResponseObject uploadPhoto(File photo) throws VkApiException, IOException {
        try (InputStream inputStream = new FileInputStream(photo)) {
            GetMessagesUploadServer.Response.ResponseObject uploadServer = vk.photos.getMessagesUploadServer()
                    .setPeerId(PEER_ID)
                    .execute()
                    .getResponseObject();
            UploadPhoto.Response uploadedPhoto = new UploadPhoto()
                    .setUrl(uploadServer.getUploadUrl())
                    .setPhoto(photo.getName(), inputStream)
                    .execute();
            return vk.photos.saveMessagesPhoto()
                    .setServer(uploadedPhoto.getServer())
                    .setPhoto(uploadedPhoto.getPhoto())
                    .setHash(uploadedPhoto.getHash())
                    .execute()
                    .getResponseObject()
                    .get(0);
        }
    }

    public void sendCallbackButton() {
        try {
            Button button = new CallbackButton(Button.Color.PRIMARY, new CallbackButton.Action("Callback button", null));
            Keyboard keyboard = new Keyboard(Collections.singletonList(Collections.singletonList(button)))
                    .setInline(true);

            Send.Response response = vk.messages.send()
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
            IntegerResponse response = vk.messages.sendEventAnswer()
                    .setUserId(messageEvent.getUserId())
                    .setPeerId(messageEvent.getPeerId())
                    .setEventId(messageEvent.getEventId())
                    .setEventData(new EventData.ShowSnackbar("Callback button was clicked!"))
                    .execute();

            System.out.println("Send event answer response: " + response);
            stopPolling();
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
            example.startPolling();
        } catch (VkApiException | IOException e) {
            log.error("Something went wrong...", e);
        }
    }
}
