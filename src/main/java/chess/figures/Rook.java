package chess.figures;

import chess.Moves;

import java.util.ArrayList;

public class Rook extends Piece implements Moves{

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "R" : "r";
    }

    @Override
    public void setAllAvailableMoves() {
        allAvailableMoves = returnAllValidMoves(directions, getRow(), getCol());



//        for(int x = col+1; x<8; x++){
//            if(board[row][x]==null) allAvailableMoves.add(new int[]{row, x});
//            else{
//                if(!board[row][x].getColor().equals(getColor())) allAvailableMoves.add(new int[]{row, x});
//                break;
//            }
//        }
//
//        for(int x = col-1; x>=0; x--){
//            if(board[row][x]==null) allAvailableMoves.add(new int[]{row, x});
//            else{
//                if(!board[row][x].getColor().equals(getColor())) allAvailableMoves.add(new int[]{row, x});
//                break;
//            }
//        }
//
//        for(int y = row+1; y<8; y++){
//            if(board[y][col]==null) allAvailableMoves.add(new int[]{y, col});
//            else{
//                if(!board[y][col].getColor().equals(getColor())) allAvailableMoves.add(new int[]{y, col});
//                break;
//            }
//        }
//
//        for(int y = row-1; y>=0; y--){
//            if(board[y][col]==null) allAvailableMoves.add(new int[]{y, col});
//            else{
//                if(!board[y][col].getColor().equals(getColor())) allAvailableMoves.add(new int[]{y, col});
//                break;
//            }
//        }
    }

}
