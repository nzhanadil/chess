package chess.players;

public class AIPlayer extends Player {

    public AIPlayer(String color, String name) {
        super(color, name);
    }

    @Override
    public int[] makeMove() {
        return new int[]{};
    }
}
