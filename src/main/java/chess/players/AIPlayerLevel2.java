package chess.players;

import chess.figures.Piece;

import java.util.ArrayList;
import java.util.Map;

public class AIPlayerLevel2 extends Player {

    private Map<String, Integer> hierarchy;
    private ArrayList<int[]> goodMoves;
    private ArrayList<int[]> excellentMoves;

    public AIPlayerLevel2(String color, String name) {
        super(color, name);
        this.goodMoves = new ArrayList<>();
        this.excellentMoves = new ArrayList<>();
        this.hierarchy = Map.of("K", 6, "Q", 5, "R", 4, "B", 3, "N", 2, "P", 1);
    }

    @Override
    public int[] makeMove() {
        setMoves();

        if(excellentMoves.size()!=0){
            return excellentMoves.get(random.nextInt(excellentMoves.size()));
        } else if(goodMoves.size() != 0){
            return goodMoves.get(random.nextInt(goodMoves.size()));
        }

        ArrayList<Piece> pieces = new ArrayList<Piece>(allFiguresWithAvailableMoves.keySet());
        Piece piece = pieces.get(random.nextInt(pieces.size()));
        ArrayList<int[]> moves = allFiguresWithAvailableMoves.get(piece);
        int[] move = moves.get(random.nextInt(moves.size()));
        return new int[]{piece.getRow(), piece.getCol(), move[0], move[1]};
    }

    private void setMoves(){
        goodMoves.clear();
        excellentMoves.clear();

        for(Piece piece : allFiguresWithAvailableMoves.keySet()){

            for(int[] move : allFiguresWithAvailableMoves.get(piece)){

                if(!backedUpPieces.contains(board[move[0]][move[1]])){
                    excellentMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                }

                if(board[move[0]][move[1]] != null && hierarchy.get(piece.getSymbol()) < hierarchy.get(board[move[0]][move[1]].getSymbol())){
                    goodMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                }
            }
        }
    }
}
