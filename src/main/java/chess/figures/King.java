package chess.figures;

import chess.Board;
import chess.Moves;

import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece {

    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public void setAllAvailableMoves() {
        Moves.setAllAvailableMoves(board[getRow()][getCol()], directions, 1);

        int pawnDir = getColor().equals(Board.black) ? -1 : 1;
        int[] changeInY = {1, -1};

        for (int[] coordinates : new ArrayList<>(getAllAvailableMoves())) {

            // TODO - write logic so that king can not make check for opponents king
            if(board[coordinates[0]][coordinates[1]] instanceof King){
                getAllAvailableMoves().remove(coordinates);
            }
            for (int dY : changeInY) {
                int x = coordinates[0] + pawnDir, y = coordinates[1] + dY;

                if (x >= 0 && x < 8 && y >= 0 && y < 8) {

                    if (board[x][y] != null && !(board[x][y].getColor().equals(getColor())) && (board[x][y] instanceof Pawn)) {
                        getAllAvailableMoves().remove(coordinates);
                    }
                }
            }
        }

        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && (!piece.getColor().equals(getColor())) && !(piece instanceof King)) {

                    piece.setAllAvailableMoves();

                    for (int[] pieceMove : piece.getAllAvailableMoves()) {
                        allAvailableMoves.removeIf(kingMove -> Arrays.equals(pieceMove, kingMove));
                    }

                    for (Piece pieceBackup : piece.getAllBackedUpPieces()) {
                        allAvailableMoves.removeIf(kingMove -> pieceBackup.equals(board[kingMove[0]][kingMove[1]]));
                    }
                }
            }
        }
    }
}