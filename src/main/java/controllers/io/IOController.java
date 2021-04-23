package controllers.io;

import java.io.IOException;
import java.util.List;

import model.Score;

public interface IOController {
    //List<Score> o List<String> readScore();
    List<String> readScore() throws IOException;

    void writeNewScore(Score score) throws IOException;

}
