package bot.longpoll.examples.doc;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.docs.GetWallUploadServer;
import api.longpoll.bots.methods.impl.docs.Save;
import api.longpoll.bots.methods.impl.upload.UploadDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadWallDocument extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(UploadWallDocument.class);
    private static final File PHOTO = new File("static/smiling_cat.png");

    public void uploadWallDoc() {
        try (InputStream inputStream = new FileInputStream(PHOTO)) {
            GetWallUploadServer.Response wallUploadServer = vk.docs.getWallUploadServer()
                    .setGroupId(getGroupId())
                    .execute();

            UploadDoc.Response uploadDoc = new UploadDoc()
                    .setUrl(wallUploadServer.getResponseObject().getUploadUrl())
                    .setDoc(PHOTO.getName(), inputStream)
                    .execute();

            Save.Response response = vk.docs.save()
                    .setFile(uploadDoc.getFile())
                    .execute();

            System.out.println("Sync response: " + response);

        } catch (VkApiException e) {
            log.error("Error during execution.", e);
        } catch (IOException e) {
            e.printStackTrace();
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
        UploadWallDocument example = new UploadWallDocument();
        example.uploadWallDoc();
    }
}
