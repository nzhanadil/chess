package chess.players;

abstract public class Player {

    String color;
    String name;
    String type;

    public Player(String color, String name, String type) {
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public abstract void move();

}
