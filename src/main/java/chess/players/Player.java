package chess.players;

import chess.Board;

import java.util.Random;

abstract public class Player extends Board {

    Random random = new Random();
    String color;
    String name;

    public Player(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public abstract int[] makeMove();

}
