import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class chess {
    public static String turn = "white";
    private static String board[][] = {
            {" 2", " 3", " 4", " 5", " 6", " 4", " 3", " 2"},
            {" 1", " 1", " 1", " 1", " 1", " 1", " 1", " 1"},
            {" 0", " 0", " 0", " 0", " 0", " 0", " 0", " 0"},
            {" 0", " 0", " 0", " 0", " 0", " 0", " 0", " 0"},
            {" 0", " 0", " 0", " 0", " 0", " 0", " 0", " 0"},
            {" 0", " 0", " 0", " 0", " 0", " 0", " 0", " 0"},
            {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
            {"-2", "-3", "-4", "-5", "-6", "-4", "-3", "-2"}
    };

    private static void displayBoard(){
        System.out.println("     A   B   C   D   E   F   G   H");
        for(int i = 1; i<=8; i++){
            System.out.println(i+": "+ Arrays.toString(board[i-1]));
        }
    }

    private static void play(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println(turn.concat(" player, Please type your move"));
            String move = scanner.nextLine();
            moveFigure(move.toLowerCase());
            turn =  turn.equals("white") ? "black": "white";
        }
    }

    private static void moveFigure(String move){
        int currentRow = move.charAt(0)-97;
        int currentIndex = Integer.parseInt(move.substring(1,2))-1;
        int moveToRow = move.charAt(3)-97;
        int moveToIndex = Integer.parseInt(move.substring(4,5))-1;

        int figure = Integer.parseInt(board[currentIndex][currentRow].replace(" ", ""));
        switch (figure){
            case -1:
                if(currentIndex>moveToIndex) {
                    System.out.println("can not move backwards");
                    return;
                }else if(currentRow!=moveToRow){
                    System.out.println("can not move to sides");
                }
        }

        board[moveToIndex][moveToRow] = board[currentIndex][currentRow];
        board[currentIndex][currentRow] = " 0";
        displayBoard();
    }

    public static void main(String[] args) {
        displayBoard();
        play();
    }

}
