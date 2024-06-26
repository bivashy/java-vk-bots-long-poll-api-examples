package bot.longpoll.examples.doc;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.docs.GetWallUploadServer;
import api.longpoll.bots.methods.impl.docs.Save;
import api.longpoll.bots.methods.impl.upload.UploadDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class UploadWallDocument extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadWallDocument.class);
    private static final File PHOTO = new File("static/smiling_cat.png");
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            new UploadWallDocument().uploadWallDoc();
        } catch (VkApiException | IOException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void uploadWallDoc() throws VkApiException, IOException {
        GetWallUploadServer.ResponseBody wallUploadServer = vk.docs.getWallUploadServer()
                .setGroupId(GROUP_ID)
                .execute();

        UploadDoc.ResponseBody uploadDoc = new UploadDoc(wallUploadServer.getResponse().getUploadUrl(), PHOTO)
                .execute();

        Save.ResponseBody responseBody = vk.docs.save()
                .setFile(uploadDoc.getFile())
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
