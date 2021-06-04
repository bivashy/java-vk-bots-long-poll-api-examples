package bot.longpoll.examples.doc;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.docs.DocsGetWallUploadServer;
import api.longpoll.bots.methods.docs.DocsSave;
import api.longpoll.bots.methods.upload.UploadDoc;
import api.longpoll.bots.model.response.docs.DocsGetUploadServerResult;
import api.longpoll.bots.model.response.docs.DocsSaveResult;
import api.longpoll.bots.model.response.other.UploadDocResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class UploadWallDocument extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(UploadWallDocument.class);
    private static final File PHOTO = new File("static/smiling_cat.png");

    public void sendMessage() {
        try {
            DocsGetUploadServerResult docsGetUploadServerResult = new DocsGetWallUploadServer(getAccessToken())
                    .setGroupId(getGroupId())
                    .execute();

            UploadDocResult uploadDocResult = new UploadDoc()
                    .setUploadUrl(docsGetUploadServerResult.getResponse().getUploadUrl())
                    .setFile(PHOTO)
                    .execute();

            DocsSaveResult result = new DocsSave(getAccessToken())
                    .setFile(uploadDocResult.getFile())
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollException e) {
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
        UploadWallDocument example = new UploadWallDocument();
        example.sendMessage();
    }
}
