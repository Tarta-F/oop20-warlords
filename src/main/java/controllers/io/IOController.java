package controllers.io;

import java.io.IOException;
import java.util.List;

import model.Score;

public interface IOController {
    /**
     * Read file where is stored all previous game's score.
     * @return {@link Score} as String.
     * @throws IOException
     */
    List<String> readScore() throws IOException;

    /**
     * Write on file a new {@link Score}.
     * @param score
     * @throws IOException
     */
    void writeNewScore(Score score) throws IOException;

    /**
     * Delete file where all previous game's score is stored.
     */
    void clearFile();

}
