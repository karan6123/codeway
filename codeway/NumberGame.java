import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minNum = 1;
        int maxNum = 100;
        int maxAttempts = 5;
        int totalRounds = 0;
        int totalWins = 0;

        boolean playAgain = true;

        while (playAgain) {
            System.out.println("=== New Round ===");
            if (playRound(minNum, maxNum, maxAttempts, random, scanner)) {
                totalWins++;
            }
            totalRounds++;

            System.out.print("Do you want to play again? (yes/no): ");
            String choice = scanner.next();
            playAgain = choice.equalsIgnoreCase("yes");
        }

        System.out.println("\nTotal Rounds: " + totalRounds);
        System.out.println("Total Wins: " + totalWins);
        System.out.printf("Win Rate: %.2f%%\n", (double) totalWins / totalRounds * 100);

        scanner.close();
    }

    public static boolean playRound(int minNum, int maxNum, int maxAttempts, Random random, Scanner scanner) {
        int targetNumber = random.nextInt(maxNum - minNum + 1) + minNum;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.printf("Guess the number between %d and %d: ", minNum, maxNum);
            int guess = scanner.nextInt();

            if (guess == targetNumber) {
                System.out.println("Congratulations! You've guessed the correct number.");
                return true;
            } else if (guess < targetNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            attempts++;
        }

        System.out.printf("Sorry, you've run out of attempts. The correct number was %d.\n", targetNumber);
        return false;
    }
}
