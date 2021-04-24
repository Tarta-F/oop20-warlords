package controllers.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.nio.file.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.score.Score;
import model.score.ScoreImpl;

public final class IOControllerImpl implements IOController {

    private static final String MSG = "No result detected.";
    private static final String FILE_PATH = System.getProperty("user.home") + File.separator + "WarlordsScore.json";
    private static final Type SCORE_TYPE = new TypeToken<List<ScoreImpl>>() { }.getType();
    private final File scoreFile;
    private final Gson gsonRead;
    private final Gson gsonWrite;

    public IOControllerImpl() {
        this.scoreFile = new File(FILE_PATH);
        this.gsonRead = new Gson();
        this.gsonWrite = new GsonBuilder().setPrettyPrinting().create();
    }

    private void writeFirstScore(final Score score) {
        final List<Score> list = List.of(score);
        final String addFirst = this.gsonWrite.toJson(list);
        try (FileWriter file = new FileWriter(this.scoreFile)) {
            file.write(addFirst); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndWriteNew(final Score score) throws FileNotFoundException {
        try (JsonReader reader = new JsonReader(new FileReader(this.scoreFile))) {
            final List<Score> oldResults = gsonRead.fromJson(reader, SCORE_TYPE); // contains the whole Score list
            oldResults.add(score);
            final String newScore = this.gsonWrite.toJson(oldResults);
            try (FileWriter file = new FileWriter(this.scoreFile)) {
                file.write(newScore);
                file.flush();
            }
        }    catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readScore() throws IOException {
        if (this.scoreFile.exists()) {
            try (JsonReader reader = new JsonReader(new FileReader(this.scoreFile))) {
                final List<Score> oldResults = this.gsonRead.fromJson(reader, SCORE_TYPE); // contains the whole Score list
                final List<String> resultList = new ArrayList<>();
                oldResults.forEach(sc -> resultList.add(sc.toString()));
                return resultList;
            }
        } else {
            return List.of(MSG);
        }
    }

    @Override
    public void writeNewScore(final Score score) throws IOException {
        if (this.scoreFile.exists()) {
            this.readAndWriteNew(score);
        } else if (this.scoreFile.createNewFile()) {
            this.writeFirstScore(score);
        }
    }

    @Override
    public void clearFile() {
        try {
            Files.deleteIfExists(this.scoreFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
