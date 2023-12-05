package chess.players;

import chess.Board;

abstract public class Player extends Board {

    String color;
    String name;
    String type;

    public Player(String color, String name, String type) {
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract void makeMove();

}
