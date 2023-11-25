package chess.figures;

abstract public class Piece {
    String color;
    int x, y;

    public Piece(String color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract String getSymbol();

    public abstract boolean isValidMove();
}
