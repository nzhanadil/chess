package chess;

public class Game extends Board{
    public static void main(String[] args) {
        createBoard();
        createPlayers();
        while (!isGameOver) {
            play();
        }
    }
}
