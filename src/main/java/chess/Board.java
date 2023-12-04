package chess;

import chess.figures.*;

public class Board {

    public static Piece[][] board = new Piece[8][8];
    public static final String white = "white";
    public static final String black = "black";
    public static Piece whiteKing;
    public static Piece blackKing;

    public static void printBoard() {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    String str = board[i][j].getColor().equals(white) ? board[i][j].getSymbol() : board[i][j].getSymbol().toLowerCase();
                    System.out.print(str + ' ');
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
