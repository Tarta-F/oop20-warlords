package model;

import java.util.Optional;
import java.util.Set;

public interface Lane {
    int getLaneNumber();
    void addUnit(final Unit u);
    Set<Unit> getUnitsAtPosition(int position);
    int getLenght();
    Optional<Integer> getScore(PlayerType player);
    /*
     * Per ogni truppa controlla se ha un target, altrimenti la sposta
     * Se arriva alla fine -> Remove Unit (Despawn + rimuovi HP)
     */
    void update(); 
    // Map<Unit,Integer> getUnits();
    // Set<Pair<<Unit,Integer>> getUnits();

}
