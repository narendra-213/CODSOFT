import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void findNum(int chances) {
        Random rand = new Random();
        int num = rand.nextInt(100) + 1;
        // System.out.println(num);
        while (chances != 0) {
            System.out.println("You have " + chances + " chances left");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Guess the number: ");
            int guessedNum = scanner.nextInt();
            if (guessedNum == num) {
                System.out.println("Hurraiah, You guessed it correctly");
                return;
            } else if (guessedNum > num) {
                System.out.println("You are too high");
                chances--;
            } else {
                System.out.println("You are too low");
                chances--;
            }
            if (chances <= 2 && chances != 0) {
                System.out.println("You are about to lose the game. Come on, you can do it!");
            }
        }
        System.out.println("Oops! Game over, your chances are completed");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the number guessing game");
        System.out.println("You have to guess the number between 1 and 100. All the best");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pick the level 'easy' or 'hard'. For easy level have 10 chances and hard level has 5 chances: ");
        String level = scanner.nextLine().toLowerCase();
        int chances;
        if (level.equals("hard")) {
            chances = 5;
        } else if (level.equals("easy")) {
            chances = 10;
        } else {
            System.out.println("Enter level correctly!!!!!!");
            return;
        }
        findNum(chances);
    }
}