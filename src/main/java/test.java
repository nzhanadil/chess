import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {

    public static String turn = "white";

    private static Map<Character, String[]> board = new HashMap();
    static char[] keys = {'A','B','C','D','E','F','G','H'};

    // белые фигуры - позитивные числа, темные фигуры - негативные числа
    // пешки -  1, ладья - 2, конь - 3, слон - 4, королева - 5, король - 6
    private static void createTable(){
        for(char key : keys){
            switch (key){
                case 'A':
                    board.put(key, new String[]{" 2", " 3", " 4", " 5", " 6", " 4", " 3", " 2"});
                    break;
                case 'B':
                    board.put(key, new String[]{" 1", " 1", " 1", " 1", " 1", " 1", " 1", " 1"});
                    break;
                case 'G':
                    board.put(key, new String[]{"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"});
                    break;
                case 'H':
                    board.put(key, new String[]{"-2", "-3", "-4", "-5", "-6", "-4", "-3", "-2"});
                    break;
                default:
                    board.put(key, new String[]{" 0", " 0", " 0", " 0", " 0", " 0", " 0", " 0"});
                    break;
            }
        }
    }

    private static void displayBoard(){
        System.out.println("     1   2   3   4   5   6   7   8");
        for(char key : keys){
            System.out.println(key + ": "+Arrays.toString(board.get(key)));
        }
    }

    public static void main(String[] args) {
        createTable();
        displayBoard();
        play();
    }

    public static void play(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println(turn.concat(" player, Please type your move"));
            String move = scanner.nextLine();
            moveFigure(move.toUpperCase());
            turn =  turn.equals("white") ? "black": "white";
        }
    }

    private static void moveFigure(String move){
        char currentRow = move.charAt(0);
        int currentIndex = Integer.parseInt(move.substring(1,2))-1;
        char moveToRow = move.charAt(3);
        int moveToIndex = Integer.parseInt(move.substring(4,5))-1;


        int figure = Math.abs(Integer.parseInt(board.get(currentRow)[currentIndex].replace(" ", "")));
        switch (figure){
            case 1:
                break;
            case 2:
                if(currentRow == moveToRow || currentIndex == moveToIndex){

                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;

        }

        board.get(moveToRow)[moveToIndex] = board.get(currentRow)[currentIndex];
        board.get(currentRow)[currentIndex] = " 0";
        displayBoard();
    }

    private static void rules(String input){

    }
    // rules for each figures
    /*
    1 - straight. First move can be up to 3 rows, after which 1 by one, only to front. Eats by cross
    2 - horizontally or vertically
    3 -
    4 - diagonally
    5 - 2+4
    6 - one by one to any side
     */


}
