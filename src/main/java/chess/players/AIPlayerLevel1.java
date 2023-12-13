package chess.players;

import chess.figures.Piece;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayerLevel1 extends Player {

    public AIPlayerLevel1(String color, String name) {
        super(color, name);
    }

    @Override
    public int[] makeMove() {
        Random random = new Random();
        ArrayList<Piece> pieces = new ArrayList<Piece>(allFiguresWithAvailableMoves.keySet());
        Piece piece = pieces.get(random.nextInt(pieces.size()));
        ArrayList<int[]> moves = allFiguresWithAvailableMoves.get(piece);
        int[] move = moves.get(random.nextInt(moves.size()));
        return new int[]{piece.getRow(), piece.getCol(), move[0], move[1]};
    }
}
