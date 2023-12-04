package chess;

public class Player {

    String color;
    String name;
    boolean isUnderCheck;

    public Player(String color, String name) {
        this.color = color;
        this.name = name;
        this.isUnderCheck = false;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
