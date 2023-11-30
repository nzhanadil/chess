package chess;

import java.util.ArrayList;
import java.util.List;
import static chess.Board.board;

public interface Moves {

    default List<int[]> longMoves(int[][] directions, int row, int col){
        List<int[]> allAvailableMoves = new ArrayList<>();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int x = row + dRow;
            int y = col + dCol;

            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[x][y] == null) {
                    allAvailableMoves.add(new int[]{x, y});
                } else {
                    if (!board[x][y].getColor().equals(board[row][col].getColor())) {
                        allAvailableMoves.add(new int[]{x, y});
                    }
                    break;
                }
                x += dRow;
                y += dCol;
            }
        }
        return  allAvailableMoves;
    }

    default List<int[]> shortMoves(int[][] directions, int row, int col){
        List<int[]> allAvailableMoves = new ArrayList<>();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];
            int x = row + dRow;
            int y = col + dCol;

            if(x >= 0 && x < 8 && y >= 0 && y < 8){
                if (board[x][y] == null) {
                    allAvailableMoves.add(new int[]{x, y});
                } else {
                    if (!board[x][y].getColor().equals(board[row][col].getColor())) {
                        allAvailableMoves.add(new int[]{x, y});
                    }
                }
            }
        }
        return  allAvailableMoves;
    }

}
