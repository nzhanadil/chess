package chess.figures;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "B" : "b";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        return (Math.abs(toCol-getCol()) == Math.abs(toRow-getRow()));
    }

    @Override
    public void setAllAvailableMoves() {
        int row = getRow();
        int col = getCol();
        allAvailableMoves = new ArrayList<>();

        for(int x = col+1, y = row+1; x<8 && y<8; x++, y++){
            if(board[y][x]==null) allAvailableMoves.add(new int[]{y, x});
            else{
                if(!board[y][x].getColor().equals(getColor())) allAvailableMoves.add(new int[]{y, x});
                break;
            }
        }

        for(int x = col-1, y = row-1; x>=0 && y>=0; x--, y--){
            if(board[y][x]==null) allAvailableMoves.add(new int[]{y, x});
            else{
                if(!board[y][x].getColor().equals(getColor())) allAvailableMoves.add(new int[]{y, x});
                break;
            }
        }

        for(int x = col+1, y = row-1; x<8 && y>=0; x++, y--){
            if(board[y][x]==null) allAvailableMoves.add(new int[]{y, x});
            else{
                if(!board[y][x].getColor().equals(getColor())) allAvailableMoves.add(new int[]{y, x});
                break;
            }
        }

        for(int x = col-1, y = row+1; x>=0 && y<8; x--, y++){
            if(board[y][x]==null) allAvailableMoves.add(new int[]{y, x});
            else{
                if(!board[y][x].getColor().equals(getColor())) allAvailableMoves.add(new int[]{y, x});
                break;
            }
        }



        int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            int x = col + dx;
            int y = row + dy;

            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (board[y][x] == null) {
                    allAvailableMoves.add(new int[]{y, x});
                } else {
                    if (!board[y][x].getColor().equals(getColor())) {
                        allAvailableMoves.add(new int[]{y, x});
                    }
                    break;
                }
                x += dx;
                y += dy;
            }
        }

    }
}
