package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board board;
    Dice dice;

    public Game(int playerCount, int boardSize, int diceCount) {
        int snakeCount = 5;
        int ladderCount = 5;
        board = new Board(boardSize, snakeCount, ladderCount);

        players = new LinkedList<>();
        for(int i = 0; i < playerCount; i++) {
            Player player = new Player("PLAYER " + (i + 1));
            player.setCurrentCell(board.getCell(1));
            players.offerLast(player);
        }

        dice = new Dice(diceCount);
    }

    public void runGame() {
        boolean hasFinished = true;
        while (hasFinished) {
            Player player = players.pollFirst();
            players.offerLast(player);

            System.out.println(player.getName() + " hit enter to roll dice: ");
            new Scanner(System.in).next();

            int diceValue = dice.rollDice();
            System.out.println("Dice value: " + diceValue);

            movePlayer(player, diceValue);
            System.out.println(player.getName() + " current position: " + player.getCurrentCell().getId());

            if(checkWinningPosition(player)) {
                hasFinished = false;
                System.out.println(player.getName() + " has won the game");
            }
        }
    }

    private boolean checkWinningPosition (Player player) {
        return player.getCurrentCell().getId() == board.getSize();
    }

    private void movePlayer(Player player, int diceValue) {
        int newCellValue = Math.min(player.getCurrentCell().getId() + diceValue, 100);

        Shifter shifter = board.getCell(newCellValue).getShifter();
        if(shifter != null) {
            newCellValue = shifter.getDestination().getId();
            System.out.println(player.getName() + " shifted by " + (shifter instanceof Ladder? "Ladder" : "Snake"));
        }

        player.setCurrentCell(board.getCell(newCellValue));
    }
}
