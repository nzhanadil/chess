package chess;

public class Player {

    String color;
    String name;
    boolean isUnderCheck;
    boolean isMate;

    public Player(String color, String name) {
        this.color = color;
        this.name = name;
        this.isUnderCheck = false;
        this.isMate = false;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public boolean isUnderCheck() {
        return isUnderCheck;
    }
}
