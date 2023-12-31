package chess.figures;

import chess.Board;
import chess.Moves;

public class Pawn extends Piece {

    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public void setAllAvailableMoves() {
        int dir = getColor().equals(Board.white) ? 1 : -1;

        allAvailableMoves.clear();
        allBackedUpPieces.clear();

        int forwardRow = getRow() + dir;

        if (forwardRow >= 0 && forwardRow < 8) {
            if (board[forwardRow][getCol()] == null) {
                allAvailableMoves.add(new int[]{forwardRow, getCol()});
            }

            for (int dY : new int[]{1, -1}) {
                int y = getCol() + dY;

                if (y >= 0 && y < 8 && board[forwardRow][y] != null) {
                    if (!board[forwardRow][y].getColor().equals(getColor())) {
                        allAvailableMoves.add(new int[]{forwardRow, y});
                    } else if(getColor().equals(currentPlayer.getColor()) && (!currentsBackedUpPieces.contains(board[forwardRow][y]))){
                        currentsBackedUpPieces.add(board[row][col]);
                    } else if((!getColor().equals(currentPlayer.getColor())) && (!opponentsBackedUpPieces.contains(board[forwardRow][y]))){
                        opponentsBackedUpPieces.add(board[row][col]);
                    }
                }
            }

            if (!hasMoved && board[forwardRow][getCol()] == null && board[getRow() + 2 * dir][getCol()] == null) {
                allAvailableMoves.add(new int[]{getRow() + 2 * dir, getCol()});
            }
        }
        Moves.removeMovesWhichOpensCheck(board[getRow()][getCol()]);
    }
}