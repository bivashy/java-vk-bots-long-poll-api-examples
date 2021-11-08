package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.methods.impl.photos.GetMessagesUploadServer;
import api.longpoll.bots.methods.impl.photos.SaveMessagesPhoto;
import api.longpoll.bots.methods.impl.upload.UploadPhoto;
import api.longpoll.bots.model.objects.additional.Template;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.additional.carousel.Carousel;
import api.longpoll.bots.model.objects.additional.carousel.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CarouselExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(CarouselExample.class);
    /**
     * Conversation ID.
     */
    private static final Integer PEER_ID = 918650328;

    public void sendCarousel() throws IOException, VkApiException {
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
            new CarouselExample().sendCarousel();
        } catch (VkApiException | IOException e) {
            log.error("Something went wrong...", e);
        }
    }
}
