package chess.players;

import chess.figures.Piece;

import java.util.ArrayList;
import java.util.Map;

public class AIPlayerLevel2 extends Player {
    private Map<String, Integer> hierarchy;
    private ArrayList<int[]> goodMoves;

    public AIPlayerLevel2(String color, String name) {
        super(color, name);
        this.goodMoves = new ArrayList<>();
        this.hierarchy = Map.of("Q", 5, "R", 4, "B", 3, "N", 2, "P", 1);
    }

    @Override
    public int[] makeMove() {
        return new int[0];
    }

    private void getGoodMoves(){
        goodMoves.clear();

        for(Piece piece : allFiguresWithAvailableMoves.keySet()){
            for(int[] move : allFiguresWithAvailableMoves.get(piece)){
                if(board[move[0]][move[1]] != null && hierarchy.get(piece.getSymbol()) < hierarchy.get(board[move[0]][move[1]].getSymbol())){
                    goodMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                }
            }
        }
    }
}
