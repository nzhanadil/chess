package chess;

import chess.figures.Piece;

import java.util.ArrayList;

import static chess.Board.*;

public class Moves {

    public static void removeMovesWhichOpensCheck(Piece piece) {
        int actualRow = piece.getRow();
        int actualCol = piece.getCol();
        boolean hasMoved = piece.isHasMoved();

        if (piece.getColor().equals(currentKing.getColor())) {
            for (int[] move : new ArrayList<>(piece.getAllAvailableMoves())) {

                Piece tempOfMoveTo = board[move[0]][move[1]];
                moveFigure(actualRow, actualCol, move[0], move[1]);

                if (Board.isCheck()) {
                    piece.getAllAvailableMoves().remove(move);
                }

                moveFigure(move[0], move[1], actualRow, actualCol);
                board[move[0]][move[1]] = tempOfMoveTo;
                piece.setHasMoved(hasMoved);
            }
        }
    }

    private static void addValidMovesAndBackedUpPieces(Piece piece, int row, int col) {
        if (board[row][col] == null) {
            piece.getAllAvailableMoves().add(new int[]{row, col});
        } else {
            if (!board[row][col].getColor().equals(piece.getColor())) {
                piece.getAllAvailableMoves().add(new int[]{row, col});
            } else if(!backedUpPieces.contains(board[row][col])){
                backedUpPieces.add(board[row][col]);
            }
        }
    }

    public static void setAllAvailableMoves(Piece piece, int[][] directions, int numberOfSteps) {

        piece.getAllAvailableMoves().clear();
        piece.getAllBackedUpPieces().clear();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int row = piece.getRow() + dRow;
            int col = piece.getCol() + dCol;

            if (numberOfSteps == 1) {
                exploreSingleStep(piece, row, col);
            } else if (numberOfSteps == 0) {
                exploreMultipleSteps(piece, dRow, dCol, row, col);
            }
        }
        removeMovesWhichOpensCheck(piece);
    }

    private static void exploreMultipleSteps(Piece piece, int dRow, int dCol, int row, int col) {
        while (isValidPosition(row, col)) {
            addValidMovesAndBackedUpPieces(piece, row, col);

            if (board[row][col] != null) {
                break;
            }
            row += dRow;
            col += dCol;
        }
    }

    private static void exploreSingleStep(Piece piece, int row, int col) {
        if (isValidPosition(row, col)) {
            addValidMovesAndBackedUpPieces(piece, row, col);
        }
    }

    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < Board.boardRows && col >= 0 && col < Board.boardCols;
    }
}