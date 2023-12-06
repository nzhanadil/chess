package chess.players;

import chess.Board;

abstract public class Player extends Board {

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

    public abstract void makeMove();

}
