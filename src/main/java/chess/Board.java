package chess;

import chess.figures.*;
import chess.players.AIPlayerLevel1;
import chess.players.AIPlayerLevel2;
import chess.players.HumanPlayer;
import chess.players.Player;

import java.util.*;

public class Board {

    public static final int boardRows = 8;
    public static final int boardCols = 8;
    private static Piece whiteKing;
    private static Piece blackKing;
    public static Piece currentKing;
    private static Player player1;
    private static Player player2;
    public static Player currentPlayer;
    public static final String white = "yellow";
    public static final String black = "green";
    public static boolean isGameOver = false;
    public static Piece[][] board = new Piece[boardRows][boardCols];
    public static HashMap<Piece, ArrayList<int[]>> allFiguresWithAvailableMoves = new HashMap<>();
    public static List<Piece> backedUpPieces = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    private static int numberOfMoves = 0;

    public static void setAllFiguresWIthAvailableMoves() {
        allFiguresWithAvailableMoves.clear();

        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && piece.getColor().equals(currentPlayer.getColor())) {
                    piece.setAllAvailableMoves();
                    if (piece.getAllAvailableMoves().size() != 0) {
                        allFiguresWithAvailableMoves.put(piece, piece.getAllAvailableMoves());
                    }
                }
            }
        }
    }

    public static void setBackedUpPieces() {
        backedUpPieces.clear();

        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && !piece.getColor().equals(currentPlayer.getColor())) {
                    piece.setAllAvailableMoves();
                }
            }
        }
    }

    public static void play() {
        createBoard();
        createPlayers();
        while (!isGameOver) {
            currentPlayerMakeMove();
        }
    }

    public static void currentPlayerMakeMove() {
        printBoard();
        setBackedUpPieces();
        setAllFiguresWIthAvailableMoves();

        if (numberOfMoves > 1000) {
            isGameOver = true;
            System.out.println("DRAW!");
            return;
        }

        if (allFiguresWithAvailableMoves.size() == 0) {
            isGameOver = true;
            Player winner = currentPlayer == player1 ? player2 : player1;
            System.out.println(winner.getName() + " WON THE GAME!");
            return;
        }

        int[] move = currentPlayer.makeMove();
        if (!isValidInput(move[0], move[1], move[2], move[3])) return;

        moveFigure(move[0], move[1], move[2], move[3]);
        makeQueensFromPawns();
        changeCurrentPlayer();
        numberOfMoves++;
    }

    public static void makeQueensFromPawns() {
        for (int row : new int[]{0, 7}) {
            for (Piece piece : board[row]) {

                if (piece instanceof Pawn) {
                    board[row][piece.getCol()] = new Queen(piece.getColor(), row, piece.getCol());
                }
            }
        }
    }

    public static boolean isCheck() {
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && !piece.getColor().equals(currentKing.getColor())) {
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
        System.out.println("Please select one of the Following\n1 - Human against AI\n2 - AI against AI\n3 - Human against Human");
        String option = scanner.next();

        switch (option) {
            case "2":
                player1 = new AIPlayerLevel2(white, "Player 1");
                player2 = new AIPlayerLevel2(black, "Player 2");
                break;
            case "3":
                System.out.println("please enter Player 1's name");
                scanner.nextLine();
                player1 = new HumanPlayer(white, scanner.nextLine());
                System.out.println("please enter Player 2's name");
                player2 = new HumanPlayer(black, scanner.nextLine());
                break;
            default:
                System.out.println("please enter your name");
                scanner.nextLine();
                player1 = new HumanPlayer(white, scanner.nextLine());
                player2 = new AIPlayerLevel1(black, "Player 2");
        }
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
        if (!allFiguresWithAvailableMoves.containsKey(board[fromRow][fromCol])) {
            System.out.println("This figure doesn't have any available moves, plea try again");
        } else {
            for (int[] move : allFiguresWithAvailableMoves.get(board[fromRow][fromCol])) {
                if (Arrays.equals(move, new int[]{toRow, toCol})) {
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