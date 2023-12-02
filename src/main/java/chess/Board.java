package chess;

import chess.figures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {
    public static Piece[][] board = new Piece[8][8];
    static int currentPlayer = 0; // 0 for white, 1 for black
    public static final String white = "white";
    public static final String black = "black";
    private static boolean isGameOver = false;
    private static boolean isCheck = false;
    private static Piece whiteKing;
    private static Piece blackKing;
    private static List<Piece> ableToBlockCheckPieces = new ArrayList<>();

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

    public static void createBoard(){
        for(int i = 0; i<8; i++){
            board[1][i] = new Pawn(white, 1, i);
            board[6][i] = new Pawn(black, 6, i);
        }

        board[0][0] = new Rook(white, 0, 0);
        board[0][1] = new Knight(white, 0, 1);
        board[0][2] = new Bishop(white, 0, 2);
        board[0][3] = new Queen(white, 0, 3);
        board[0][4] = new King(white, 0, 4);
        board[0][5] = new Bishop(white, 0, 5);
        board[0][6] = new Knight(white, 0, 6);
        board[0][7] = new Rook(white, 0, 7);

        board[7][0] = new Rook(black, 7, 0);
        board[7][1] = new Knight(black, 7, 1);
        board[7][2] = new Bishop(black, 7, 2);
        board[7][3] = new Queen(black, 7, 3);
        board[7][4] = new King(black, 7, 4);
        board[7][5] = new Bishop(black, 7, 5);
        board[7][6] = new Knight(black, 7, 6);
        board[7][7] = new Rook(black, 7, 7);

        whiteKing = board[0][4];
        blackKing = board[7][4];
    }

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

        Piece currentPiece = board[fromRow][fromCol];
        currentPiece.setAllAvailableMoves();

        if(isGameOver){
            System.out.println("GAME IS OVER");
            System.exit(1);
        }

        if(isCheck){
            if(!(currentPiece instanceof King)){
                System.out.println("Your King is under Check, you can move only with your King");
                return;
            }
        }

        if(!currentPiece.isValidMove(toRow, toCol)){
            System.out.println("This is not valid move, please try again");
            return;
        }

        isCheck = isCheck(currentPiece);

        moveFigure(fromRow, fromCol, toRow, toCol);
        changePlayer();
        printBoard();
    }

    public static boolean isValidInput(int fromRow, int fromCol, int toRow, int toCol){
        if(fromRow<0 || fromRow>7 || toRow<0 || toRow > 7 || fromCol<0 || fromCol>7 || toCol<0 || toCol > 7 ){
            System.out.println("This is invalid input, please try again");
            return false;
        }
        if(board[fromRow][fromCol]==null){
            System.out.println("This is not figure, please try again");
            return false;
        }
        if(fromRow==toRow && fromCol==toCol){
            System.out.println("This is not valid move, please try again");
            return false;
        }
        return true;
    }

    public static boolean isCheck(Piece movedPiece){
        Piece king = movedPiece.getColor().equals(white) ? whiteKing : blackKing;

        movedPiece.setAllAvailableMoves();

        king.setAllAvailableMoves();
        if(king.getAllAvailableMoves().size() == 0){
            if(!canAnyOtherFigureCloseCheck(movedPiece)){
                isGameOver = true;
            }
        }
        return movedPiece.isValidMove(king.getRow(), king.getCol());
    }

    public static boolean canAnyOtherFigureCloseCheck(Piece movedPiece){

        boolean result = false;

        for(Piece[] row : board){
            for(Piece piece : row){
                if(piece!=null && (!piece.getColor().equals(movedPiece.getColor()))){
                    piece.setAllAvailableMoves();
                    for(int[] pieceMove : piece.getAllAvailableMoves()){
                        for(int[] movedPieceMove : movedPiece.getAllAvailableMoves()){
                            if(Arrays.equals(pieceMove, movedPieceMove)){
                                ableToBlockCheckPieces.add(piece);
                                result =  true;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        createBoard();
        System.out.println(whiteKing.getAllAvailableMoves().size()==0);
        printBoard();
        while (true){
            makeMove();
        }
    }

}
