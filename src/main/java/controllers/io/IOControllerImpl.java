package controllers.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class IOControllerImpl implements IOController {
    private File scoreFile;
    private static final String MSG = "No result detected.";
    private static final String FILE_PATH = System.getProperty("user.home") + File.separator + "WarlordsScore.json";
    private Gson gsonRead;
    private Gson gsonWrite;

    public IOControllerImpl() {
        this.scoreFile = new File(FILE_PATH);
        this.gsonRead = new Gson();
        this.gsonWrite = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public final List<String> readScore() throws IOException {
        if (!this.scoreFile.exists()) {
//          final Type SCORE_TYPE = new TypeToken<List<ScoreImpl>>() {}.getType();
//          JsonReader reader = new JsonReader(new FileReader(this.scoreFile));
//          List<ScoreImpl> oldResults = this.gsonRead.fromJson(reader, SCORE_TYPE); // contains the whole reviews list
            List<String> resultList = new ArrayList<>();
//          oldResults.forEach(sc -> resultList.add(sc.toString()));
            return resultList;
        } else {
           return List.of(MSG);
        }
    }

    @Override
    public final void writeNewScore() throws IOException {
        if (this.scoreFile.exists()) {
            this.readAndWriteNew();
        } else {
            try {
                this.scoreFile.createNewFile();
                this.writeFirstScore();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeFirstScore() {
        //List<ScoreImpl> list = new ArrayList<>(List.of(score));
        //String addFirst = this.gsonWrite.toJson(list);
        try (FileWriter file = new FileWriter(this.scoreFile)) {
            //file.write(addFirst); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndWriteNew() {
//        final Type SCORE_TYPE = new TypeToken<List<ScoreImpl>>() {}.getType();
//        JsonReader reader = new JsonReader(new FileReader(this.scoreFile));
//        List<ScoreImpl> oldResults = gsonRead.fromJson(reader, SCORE_TYPE); // contains the whole reviews list
//        oldResults.add(new ScoreImpl("p11", "p22", 1, 0));
//        oldResults.forEach(sc -> System.out.println(sc.toString()));
//        String newScore = this.gsonWrite.toJson(oldResults);
//        try (FileWriter file = new FileWriter(this.scoreFile)) {
//            file.write(newScore);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}