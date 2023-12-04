package chess;

import chess.figures.Piece;

import static chess.Board.board;

public interface Moves {

    default void setAllAvailableMovesForLongMovingPieces(int[][] directions, int row, int col) {
        Piece currentPiece = board[row][col];

        currentPiece.getAllAvailableMoves().clear();
        currentPiece.getAllBackedUpPieces().clear();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int x = row + dRow;
            int y = col + dCol;

            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[x][y] == null) {
                    currentPiece.getAllAvailableMoves().add(new int[]{x, y});
                } else {
                    if (!board[x][y].getColor().equals(currentPiece.getColor())) {
                        currentPiece.getAllAvailableMoves().add(new int[]{x, y});
                    } else {
                        currentPiece.getAllBackedUpPieces().add(board[x][y]);
                    }
                    break;
                }
                x += dRow;
                y += dCol;
            }
        }
    }

    default void setAllAvailableMovesForShortMovingPieces(int[][] directions, int row, int col) {
        Piece currentPiece = board[row][col];

        currentPiece.getAllAvailableMoves().clear();
        currentPiece.getAllBackedUpPieces().clear();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int x = row + dRow;
            int y = col + dCol;

            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[x][y] == null) {
                    currentPiece.getAllAvailableMoves().add(new int[]{x, y});
                } else {
                    if (!board[x][y].getColor().equals(currentPiece.getColor())) {
                        currentPiece.getAllAvailableMoves().add(new int[]{x, y});
                    } else {
                        currentPiece.getAllBackedUpPieces().add(board[x][y]);
                    }
                }
            }
        }
    }

}