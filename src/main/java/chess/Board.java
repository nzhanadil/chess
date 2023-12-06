package chess;

import chess.figures.*;
import chess.players.HumanPlayer;
import chess.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {

    public static final int boardRows = 8, boardCols = 8;
    public static Piece whiteKing, blackKing, currentKing;
    public static Player player1, player2, currentPlayer;

    public static Piece[][] board = new Piece[boardRows][boardCols];
    public static final String white = "white";
    public static final String black = "black";
    public static boolean isGameOver = false;

    public static HashMap<Piece, ArrayList<int[]>> allFiguresWithAvailableMoves = new HashMap<>();

    public static void setAllFiguresWIthAvailableMoves(){
        allFiguresWithAvailableMoves.clear();

        for(Piece[] row : board){
            for(Piece piece : row){
                if(piece != null && piece.getColor().equals(currentPlayer.getColor())){
                    piece.setAllAvailableMoves();
                    allFiguresWithAvailableMoves.put(piece, piece.getAllAvailableMoves());
                }
            }
        }
    }



    public static void play(){
        setAllFiguresWIthAvailableMoves();

        int[] move = currentPlayer.makeMove();
        if(!isValidInput(move[0], move[1], move[2], move[3])) return;
        if(allFiguresWithAvailableMoves.size()==0){
            isGameOver = true;
            return;
        }
        moveFigure(move[0], move[1], move[2], move[3]);
        changeCurrentPlayer();
        printBoard();

    }

    public static void main(String[] args) {
        createBoard();
        createPlayers();
        printBoard();
        while (!isGameOver){
            play();
        }
        System.out.println(currentPlayer.getName()+" WON THE GAME!");
    }

    public static boolean isCheck() {
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && !piece.getColor().equals(currentKing.getColor()) && !(piece instanceof King)) {

                    piece.setAllAvailableMoves();

                    for (int[] move : piece.getAllAvailableMoves()) {
                        if (Arrays.equals(move, new int[]{currentKing.getRow(), currentKing.getCol()})) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void createPlayers() {
        player1 = new HumanPlayer(white, "Player 1");
        player2 = new HumanPlayer(black, "Player 2");
        currentPlayer = player1;
        currentKing = whiteKing;
    }

    public static void changeCurrentPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        currentKing = currentPlayer.getColor().equals(white) ? whiteKing : blackKing;
    }

    public static boolean isValidInput(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow < 0 || fromRow > 7 || toRow < 0 || toRow > 7 || fromCol < 0 || fromCol > 7 || toCol < 0 || toCol > 7) {
            System.out.println("This is invalid input, please try again");
            return false;
        }
        if (board[fromRow][fromCol] == null) {
            System.out.println("Please, select a box with Figure on it!");
            return false;
        }
        if (!board[fromRow][fromCol].getColor().equals(currentPlayer.getColor())) {
            System.out.println("Please, select ".concat(currentPlayer.getColor()).concat(" Figure"));
            return false;
        }
        if (fromRow == toRow && fromCol == toCol) {
            System.out.println("This is not valid move, please try again");
            return false;
        }
        if (!allFiguresWithAvailableMoves.containsKey(board[fromRow][fromCol])){
            System.out.println("This figure doesn't have any available moves, plea try again");
        } else {
            for(int[] move : allFiguresWithAvailableMoves.get(board[fromRow][fromCol])){
                if(Arrays.equals(move, new int[]{toRow, toCol})){
                    return true;
                }
            }
        }
        System.out.println("This is not valid move, please try again");
        return false;
    }

    public static void printBoard() {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < boardRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < boardCols; j++) {
                if (board[i][j] != null) {
                    String color = board[i][j].getColor().equals(white) ? "\u001B[33m" : "\u001B[32m";
                    System.out.print(color + board[i][j].getSymbol().concat(" ") + "\u001B[0m");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void createBoard() {
        for (int i = 0; i < 8; i++) {
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

    public static void moveFigure(int fromRow, int fromCol, int toRow, int toCol) {
        board[toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = null;
        board[toRow][toCol].setLocation(toRow, toCol);
    }
}