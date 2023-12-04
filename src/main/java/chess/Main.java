package chess;

import chess.figures.King;
import chess.figures.Knight;
import chess.figures.Pawn;
import chess.figures.Piece;

import java.util.*;

public class Main extends Board {

    private static Player whitePlayer;
    private static Player blackPlayer;
    private static Player currentPlayer;
    private static Player oppositePlayer;
    private static Piece currentPiece;
    private static Piece currentKing;
    private static boolean isGameOver = false;
    private static List<int[]> coordinatesToPreventCheck = new ArrayList<>();
    private static Map<Piece, List<int[]>> piecesWhichCanPreventCheck = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    private static void createPlayers() {
        whitePlayer = new Player(Board.white, "Player 1");
        blackPlayer = new Player(Board.black, "Player 2");
        currentPlayer = whitePlayer;
        oppositePlayer = blackPlayer;
    }

    private static void changeCurrentPlayer() {
        oppositePlayer = currentPlayer;
        currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    private static boolean isValidInput(int fromRow, int fromCol, int toRow, int toCol) {
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
        return true;
    }

    public static int[] getMoveFromPlayer() {
        System.out.println(currentPlayer.getName().concat("'s turn.\nPlease enter your move(EX: a2 a3)"));
        String[] move = scanner.nextLine().split(" ");

        int fromRow = move[0].charAt(1) - '0' - 1;
        int fromCol = move[0].charAt(0) - 'a';
        int toRow = move[1].charAt(1) - '0' - 1;
        int toCol = move[1].charAt(0) - 'a';

        return new int[]{fromRow, fromCol, toRow, toCol};
    }

    public static boolean isCheck() {
        currentKing = currentPiece.getColor().equals(white) ? blackKing : whiteKing;
        currentPiece.setAllAvailableMoves();
        return currentPiece.isValidMove(currentKing.getRow(), currentKing.getCol());
    }

    public static void play() {

        int[] move = getMoveFromPlayer();

        if (!isValidInput(move[0], move[1], move[2], move[3])) return;

        currentPiece = board[move[0]][move[1]];
        currentPiece.setAllAvailableMoves();

        if (!currentPiece.isValidMove(move[2], move[3])) {
            System.out.println("This is not valid move, please try again");
            return;
        }

        if (currentPlayer.isUnderCheck) {
            System.out.println(currentPlayer + " you are under check!");
            if (currentPiece instanceof King) {
                currentPlayer.isUnderCheck = false;
            } else if (piecesWhichCanPreventCheck.containsKey(currentPiece)) {
                boolean isRightMove = false;
                for (int[] acceptableMoves : piecesWhichCanPreventCheck.get(currentPiece)) {
                    if (Arrays.equals(acceptableMoves, new int[]{move[2], move[3]})) {
                        isRightMove = true;
                        currentPlayer.isUnderCheck = false;
                        break;
                    }
                }
                if (!isRightMove) {
                    System.out.println("This is not valid move, please try again");
                    return;
                }
            } else {
                System.out.println("This is not valid figure, please try again");
                return;
            }
        }

        moveFigure(move[0], move[1], move[2], move[3]);

        if (isCheck()) {
            oppositePlayer.isUnderCheck = true;
            currentKing.setAllAvailableMoves();
            if (currentKing.getAllAvailableMoves().size() == 0) {
                setCoordinatesToPreventCheck();
                if (!canAnyPiecePreventCheck()) {
                    isGameOver = true;
                } else {

                }
            }
        }
        changeCurrentPlayer();
        printBoard();
    }

    public static void setCoordinatesToPreventCheck() {
        coordinatesToPreventCheck.clear();
        piecesWhichCanPreventCheck.clear();

        if (currentPiece instanceof Knight || currentPiece instanceof Pawn) {
            coordinatesToPreventCheck.add(new int[]{currentPiece.getRow(), currentPiece.getCol()});
        } else {
            int dRow = Integer.compare(currentKing.getRow() - currentPiece.getRow(), 0);
            int dCol = Integer.compare(currentKing.getRow() - currentPiece.getRow(), 0);

            int x = currentKing.getRow() + dRow;
            int y = currentKing.getCol() + dCol;

            while (x >= 0 && x < 8 && y >= 0 && y < 8 && x <= currentPiece.getRow() && y <= currentPiece.getCol()) {
                coordinatesToPreventCheck.add(new int[]{x, y});
                x += dRow;
                y += dCol;
            }
        }
    }

    public static boolean canAnyPiecePreventCheck() {
        boolean result = false;
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null && piece.getColor().equals(currentKing.getColor()) && piece != currentKing) {
                    piece.setAllAvailableMoves();
                    List<int[]> tempArr = new ArrayList<>();
                    for (int[] pieceMove : piece.getAllAvailableMoves()) {
                        for (int[] coordinateToPreventCheck : coordinatesToPreventCheck) {
                            if (Arrays.equals(pieceMove, coordinateToPreventCheck)) {
                                tempArr.add(pieceMove);
                                result = true;
                            }
                        }
                    }
                    piecesWhichCanPreventCheck.put(piece, tempArr);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        createBoard();
        printBoard();
        createPlayers();
        while (!isGameOver) {
            play();
        }
        System.out.println(oppositePlayer.getName() + " won the game!");
    }
}