package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Shifter {
    private Cell source;
    private Cell destination;

    public void shift(Player player) {
        player.setCurrentCell(destination);
    }
}
