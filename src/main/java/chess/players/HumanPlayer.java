package chess.players;

import java.util.Scanner;

public class HumanPlayer extends Player {

    Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String color, String name) {
        super(color, name);
    }

    @Override
    public int[] makeMove() {
        System.out.println(currentPlayer.getName().concat("'s turn.\nPlease enter your move(EX: a2 a3)"));
        String[] move = scanner.nextLine().split(" ");

        int fromRow = move[0].charAt(1) - '0' - 1;
        int fromCol = move[0].charAt(0) - 'a';
        int toRow = move[1].charAt(1) - '0' - 1;
        int toCol = move[1].charAt(0) - 'a';

        return new int[]{fromRow, fromCol, toRow, toCol};
    }
}
