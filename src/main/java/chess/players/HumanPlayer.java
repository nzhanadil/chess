package chess.players;

import java.util.Scanner;

public class HumanPlayer extends Player {

    static Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String color, String name, String type) {
        super(color, name, type);
    }

    @Override
    public void makeMove() {
        int[] move = getMoveFromUser();
    }

    public int[] getMoveFromUser() {
        System.out.println(getName().concat("'s turn.\nPlease enter your move(EX: a2 a3)"));
        String[] move = scanner.nextLine().split(" ");

        int fromRow = move[0].charAt(1) - '0' - 1;
        int fromCol = move[0].charAt(0) - 'a';
        int toRow = move[1].charAt(1) - '0' - 1;
        int toCol = move[1].charAt(0) - 'a';

        return new int[]{fromRow, fromCol, toRow, toCol};
    }
}
