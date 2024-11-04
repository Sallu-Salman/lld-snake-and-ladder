package model;

import lombok.Getter;

import java.util.*;

public class Board {
    @Getter
    private int size;
    private Cell[] cells;
    private Set<Integer> BLACK_LIST;

    public Board(int size, int snakeCount, int ladderCount) {
        this.size = size;

        cells = new Cell[size];
        for(int i = 0; i < size; i++) {
            cells[i] = new Cell(i+1);
        }
        BLACK_LIST = new HashSet<>();
        BLACK_LIST.add(1);
        BLACK_LIST.add(size);

        for(int i = 0; i < snakeCount; i++) {
            createSnake();
        }

        for(int i = 0; i < ladderCount; i++) {
            createLadder();
        }
    }

    private void createSnake() {
        Random random = new Random();

        int source, destination;
        do {
            source = random.nextInt(100) + 1;
        } while (getCell(source).getShifter() != null || BLACK_LIST.contains(source));

        do {
            destination = random.nextInt(100) + 1;
        } while (destination >= source || getCell(destination).getShifter() != null || BLACK_LIST.contains(source));

        Shifter snake = new Snake(getCell(source), getCell(destination));
        getCell(source).setShifter(snake);
    }

    private void createLadder() {
        Random random = new Random();

        int source, destination;
        do {
            source = random.nextInt(100) + 1;
        } while (getCell(source).getShifter() != null);

        do {
            destination = random.nextInt(100) + 1;
        } while (destination <= source || getCell(destination).getShifter() != null);

        Shifter ladder = new Ladder(getCell(source), getCell(destination));
        getCell(source).setShifter(ladder);
    }

    public Cell getCell(int cellNumber) {
        return Arrays
                .stream(cells)
                .filter(c -> c.getId() == cellNumber)
                .findFirst()
                .orElse(null);
    }
}
