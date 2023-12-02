package chess.figures;

import chess.Moves;

public class Rook extends Piece implements Moves {

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "R" : "r";
    }

    @Override
    public void setAllAvailableMoves() {
        //allAvailableMoves = longMoves(directions, getRow(), getCol());
        allAvailableMoves.clear();
        allBackedUpPieces.clear();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int x = getRow() + dRow;
            int y = getCol() + dCol;

            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[x][y] == null) {
                    allAvailableMoves.add(new int[]{x, y});
                } else {
                    if (!board[x][y].getColor().equals(getColor())) {
                        allAvailableMoves.add(new int[]{x, y});
                    } else {
                        allBackedUpPieces.add(board[x][y]);
                    }
                    break;
                }
                x += dRow;
                y += dCol;
            }
        }
    }

}
