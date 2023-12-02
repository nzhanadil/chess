package chess;

import chess.figures.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
    public static Piece[][] board = new Piece[8][8];
    static int currentPlayer = 0; // 0 for white, 1 for black
    public static final String white = "white";
    public static final String black = "black";
    static boolean gameOver = false;

    public static void changePlayer(){
        currentPlayer = currentPlayer==0 ? 1: 0;
    }

    public static void printBoard(){
        System.out.println("  A B C D E F G H");
        for(int i = 0; i<8;i++){
            System.out.print(i+1+" ");
            for(int j = 0; j<8; j++){
                if(board[i][j]!=null){
                    System.out.print(board[i][j].getSymbol()+' ');
                }else{
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

//    public static void createBoard(){
//        for(int i = 0; i<8; i++){
//            board[1][i] = new Pawn(white, 1, i);
//            board[6][i] = new Pawn(black, 6, i);
//        }
//
//        board[0][0] = new Rook(white, 0, 0);
//        board[0][1] = new Knight(white, 0, 1);
//        board[0][2] = new Bishop(white, 0, 2);
//        board[0][3] = new Queen(white, 0, 3);
//        board[0][4] = new King(white, 0, 4);
//        board[0][5] = new Bishop(white, 0, 5);
//        board[0][6] = new Knight(white, 0, 6);
//        board[0][7] = new Rook(white, 0, 7);
//
//        board[7][0] = new Rook(black, 7, 0);
//        board[7][1] = new Knight(black, 7, 1);
//        board[7][2] = new Bishop(black, 7, 2);
//        board[7][3] = new Queen(black, 7, 3);
//        board[7][4] = new King(black, 7, 4);
//        board[7][5] = new Bishop(black, 7, 5);
//        board[7][6] = new Knight(black, 7, 6);
//        board[7][7] = new Rook(black, 7, 7);
//    }

    public static void moveFigure(int fromRow, int fromCol, int toRow, int toCol){
        board[toRow][toCol] = board[fromRow][toCol];
        board[fromRow][fromCol].setLocation(toRow, toCol);
        board[fromRow][fromCol] = null;
    }

    public static void makeMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player "+(currentPlayer+1)+"'s turn.\nPlease enter your move(EX: a2 a3)");
        String[] move = scanner.nextLine().split(" ");

        int fromRow = move[0].charAt(1)-'0'-1;
        int fromCol = move[0].charAt(0)-'a';
        int toRow = move[1].charAt(1)-'0'-1;
        int toCol = move[1].charAt(0)-'a';

        if(!isValidInput(fromRow, fromCol, toRow, toCol)) return;
        if(!board[fromRow][fromCol].isValidMove(toRow, toCol)){
            System.out.println("This is not valid move, please try again");
            return;
        }
        moveFigure(fromRow, fromCol, toRow, toCol);
        changePlayer();
        printBoard();
    }

    public static boolean isValidInput(int fromRow, int fromCol, int toRow, int toCol){
        if(fromRow<0 || fromRow>7 || toRow<0 || toRow > 7 || fromCol<0 || fromCol>7 || toCol<0 || toCol > 7 ){
            System.out.println("This is invalid input, please try again");
            return false;
        }
        if(fromRow==toRow && fromCol==toCol){
            System.out.println("This is not valid move, please try again");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        createBoard();
        printBoard();
//        while (!gameOver){
//            makeMove();
//        }
//        moveFigure(6,6,5,6);
//        moveFigure(0,0,4,5);
//        printBoard();
//        board[5][6].setAllAvailableMoves();

        board[7][4].setAllAvailableMoves();

        List<int[]> moves = board[7][4].getAllAvailableMoves();

        for(int[] move : moves){
            System.out.println(Arrays.toString(move));
        }



    }

    public static void createBoard(){
//        for(int i = 0; i<8; i++){
//            board[1][i] = new Pawn(white, 1, i);
//            board[6][i] = new Pawn(black, 6, i);
//        }

//        board[0][0] = new Rook(white, 0, 0);
//        board[0][1] = new Knight(white, 0, 1);
//        board[0][2] = new Bishop(white, 0, 2);
//        board[0][3] = new Queen(white, 0, 3);
//        board[0][4] = new King(white, 0, 4);
//        board[0][5] = new Bishop(white, 0, 5);
//        board[0][6] = new Knight(white, 0, 6);
//        board[0][7] = new Rook(white, 0, 7);

        board[7][0] = new Rook(black, 7, 0);
        board[7][1] = new Knight(black, 7, 1);
        board[7][2] = new Bishop(black, 7, 2);

        board[7][3] = new Queen(white, 7, 3);
        board[7][4] = new King(black, 7, 4);
        board[7][5] = new Bishop(white, 7, 5);

        board[7][6] = new Knight(black, 7, 6);
        board[7][7] = new Rook(black, 7, 7);
    }


}
