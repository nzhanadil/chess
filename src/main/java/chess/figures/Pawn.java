package chess.figures;

import chess.Board;

public class Pawn extends Piece {

    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "P" : "p";
    }

    @Override
    public void setAllAvailableMoves() {
        int dir = getColor().equals(Board.white) ? 1 : -1;
        int row = getRow();
        int col = getCol();

        allAvailableMoves.clear();
        allBackedUpPieces.clear();

        int forwardRow = row + dir;

        if (forwardRow >= 0 && forwardRow < 8) {
            if (board[forwardRow][col] == null) {
                allAvailableMoves.add(new int[]{forwardRow, col});
            }

            int leftCol = col - 1;
            if (leftCol >= 0 && board[forwardRow][leftCol] != null) {
                if (!board[forwardRow][leftCol].getColor().equals(getColor())) {
                    allAvailableMoves.add(new int[]{forwardRow, leftCol});
                } else {
                    allBackedUpPieces.add(board[forwardRow][leftCol]);
                }
            }

            int rightCol = col + 1;
            if (rightCol < 8 && board[forwardRow][rightCol] != null) {
                if (!board[forwardRow][rightCol].getColor().equals(getColor())) {
                    allAvailableMoves.add(new int[]{forwardRow, rightCol});
                } else {
                    allBackedUpPieces.add(board[forwardRow][rightCol]);
                }
            }
            hasMoved = true;

            if (!hasMoved && board[forwardRow][col] == null && board[row + 2 * dir][col] == null) {
                allAvailableMoves.add(new int[]{row + 2 * dir, col});
            }
        }
    }

}
