package chess;

import chess.figures.Piece;

import static chess.Board.board;

public class Moves {

    private static void addValidMovesAndBackedUpPieces(Piece piece, int row, int col) {
        if (board[row][col] == null) {
            piece.getAllAvailableMoves().add(new int[]{row, col});
        } else {
            if (!board[row][col].getColor().equals(piece.getColor())) {
                piece.getAllAvailableMoves().add(new int[]{row, col});
            } else {
                piece.getAllBackedUpPieces().add(board[row][col]);
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
    }

    private static void exploreMultipleSteps(Piece piece, int dRow, int dCol, int row, int col) {
        while (isValidPosition(row, col)) {
            addValidMovesAndBackedUpPieces(piece, row, col);
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