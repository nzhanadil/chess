package chess.figures;

import chess.Moves;

import java.util.Arrays;

public class King extends Piece implements Moves {

    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "K" : "k";
    }

    @Override
    public void setAllAvailableMoves() {
        //allAvailableMoves = shortMoves(directions, getRow(), getCol());

        allAvailableMoves.clear();
        allBackedUpPieces.clear();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int x = getRow() + dRow;
            int y = getCol() + dCol;

            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[x][y] == null) {
                    allAvailableMoves.add(new int[]{x, y});
                } else {
                    if (!board[x][y].getColor().equals(getColor())) {
                        allAvailableMoves.add(new int[]{x, y});
                    } else {
                        allBackedUpPieces.add(board[x][y]);
                    }
                }
            }
        }

        for(Piece[] row : board){
            for(Piece piece : row){
                if(piece!=null && (!piece.getColor().equals(getColor())) && !(piece instanceof King)){

                    piece.setAllAvailableMoves();

                    for(int[] pieceMove : piece.getAllAvailableMoves()){
                        allAvailableMoves.removeIf(kingMove -> Arrays.equals(pieceMove, kingMove));
                    }

                    for(Piece pieceBackup : piece.getAllBackedUpPieces()){
                        allAvailableMoves.removeIf(kingMove -> pieceBackup.equals(board[kingMove[0]][kingMove[1]]));
                    }
                }

            }
        }

    }
}
