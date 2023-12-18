package chess.players;

import chess.figures.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class AIPlayerLevel2 extends Player {

    private Map<String, Integer> hierarchy;
    private ArrayList<int[]> backupMoves, excellentMoves, goodMoves, fairMoves, badMoves;

    public AIPlayerLevel2(String color, String name) {
        super(color, name);
        this.backupMoves = new ArrayList<>();
        this.excellentMoves = new ArrayList<>();
        this.goodMoves = new ArrayList<>();
        this.fairMoves = new ArrayList<>();
        this.badMoves = new ArrayList<>();
        this.hierarchy = Map.of("K", 6, "Q", 5, "R", 4, "B", 3, "N", 2, "P", 1);
    }

    @Override
    public int[] makeMove() {
        setMoves();

        if (backupMoves.size() != 0) {
            return getBestMove(backupMoves, "backup");
        } else if (excellentMoves.size() != 0) {
            return getBestMove(excellentMoves, "best");
        } else if (goodMoves.size() != 0) {
            return getBestMove(goodMoves, "good");
        } else if (fairMoves.size() != 0) {
            return fairMoves.get(random.nextInt(fairMoves.size()));
        }
        return getBestMove(badMoves, "bad");
    }

    private int[] getBestMove(ArrayList<int[]> moves, String type) {
        int[] bestMove = moves.get(0);

        for (int[] move : moves) {

            String bestFrom = board[bestMove[0]][bestMove[1]].getSymbol();
            String bestTo = "";

            String moveFrom = board[move[0]][move[1]].getSymbol();
            String moveTo = "";

            if(!type.equals("bad") && !type.equals("backup")){
                bestTo = board[bestMove[2]][bestMove[3]].getSymbol();
                moveTo = board[move[2]][move[3]].getSymbol();
            }

            switch (type) {
                case "best":
                    if (hierarchy.get(bestTo) < hierarchy.get(moveTo)) {
                        bestMove = move;
                    }
                    break;
                case "good":
                    if (hierarchy.get(bestTo) - hierarchy.get(bestFrom) < hierarchy.get(moveTo) - hierarchy.get(moveFrom)) {
                        bestMove = move;
                    }
                    break;
                case "bad":
                    if (hierarchy.get(bestFrom) > hierarchy.get(moveFrom)) {
                        bestMove = move;
                    }
                    break;
                case "backup":
                    if (hierarchy.get(bestFrom) < hierarchy.get(moveFrom)) {
                        bestMove = move;
                    }
                    break;
            }
        }

        return bestMove;
    }

    private void setMoves() {
        backupMoves.clear();
        goodMoves.clear();
        excellentMoves.clear();
        fairMoves.clear();
        badMoves.clear();

        for (Piece piece : allFiguresWithAvailableMoves.keySet()) {

            for (int[] move : allFiguresWithAvailableMoves.get(piece)) {
                // TODO - Delete Duplicates
                if(currentsFiguresWhichCanBeEaten.containsKey(piece) && currentsBackedUpPieces.contains(piece)){
                    int lowest = 0;

                    for(Piece opponentsPiece : currentsFiguresWhichCanBeEaten.get(piece)){
                        if(lowest > hierarchy.get(opponentsPiece.getSymbol())){
                            lowest = hierarchy.get(opponentsPiece.getSymbol());
                        }
                    }

                    if(lowest < hierarchy.get(piece.getSymbol())){
                        boolean found = false;
                        OUTER:
                        for (Piece[] row : board) {
                            for (Piece p : row) {

                                if (p != null && !p.getColor().equals(piece.getColor())) {
                                    for (int[] m : p.getAllAvailableMoves()) {

                                        if (Arrays.equals(m, move)) {
                                            found = true;
                                            break OUTER;
                                        }
                                    }
                                }
                            }
                        }

                        if (!found) {
                            backupMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                        }
                    }
                } else if(currentsFiguresWhichCanBeEaten.containsKey(piece)){
                    boolean found = false;
                    OUTER:
                    for (Piece[] row : board) {
                        for (Piece p : row) {

                            if (p != null && !p.getColor().equals(piece.getColor())) {
                                for (int[] m : p.getAllAvailableMoves()) {

                                    if (Arrays.equals(m, move)) {
                                        found = true;
                                        break OUTER;
                                    }
                                }
                            }
                        }
                    }

                    if (!found) {
                        backupMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                    }
                }

                if (board[move[0]][move[1]] != null && !opponentsBackedUpPieces.contains(board[move[0]][move[1]])) {
                    excellentMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                } else if (board[move[0]][move[1]] != null && hierarchy.get(piece.getSymbol()) < hierarchy.get(board[move[0]][move[1]].getSymbol())) {
                    goodMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                } else if (board[move[0]][move[1]] == null) {
                    boolean found = false;

                    OUTER:
                    for (Piece[] row : board) {
                        for (Piece p : row) {

                            if (p != null && !p.getColor().equals(piece.getColor())) {
                                for (int[] m : p.getAllAvailableMoves()) {

                                    if (Arrays.equals(m, move)) {
                                        found = true;
                                        break OUTER;
                                    }
                                }
                            }
                        }
                    }

                    if (!found) {
                        fairMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                    } else {
                        badMoves.add(new int[]{piece.getRow(), piece.getCol(), move[0], move[1]});
                    }
                }
            }
        }
    }
}
