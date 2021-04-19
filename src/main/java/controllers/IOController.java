package controllers;

import java.io.IOException;
import java.util.List;

public interface IOController {
    //List<Score> o List<String> readScore();
    List<String> readScore() throws IOException;

    void writeNewScore() throws IOException;

}
