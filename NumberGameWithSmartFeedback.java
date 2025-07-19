import java.util.Scanner;
import java.util.Random;
import java.awt.Toolkit;
import java.time.Instant;
import java.time.Duration;

public class NumberGameWithSmartFeedback {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Smart Number Guessing Game!");

        while (playAgain) {
            int maxAttempts = selectDifficulty(scanner);
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean hasWon = false;

            System.out.println("\nI have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            Instant startTime = Instant.now(); // Start timer

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    Toolkit.getDefaultToolkit().beep(); // beep for correct
                    System.out.println("Correct! You guessed it in " + attempts + " attempt(s).");

                    Instant endTime = Instant.now();
                    long timeTaken = Duration.between(startTime, endTime).getSeconds();
                    System.out.println(" Time taken: " + timeTaken + " seconds");

                    totalScore++;
                    hasWon = true;
                    break;
                } else {
                    Toolkit.getDefaultToolkit().beep(); // beep for wrong
                    int difference = Math.abs(userGuess - numberToGuess);

                    if (userGuess > numberToGuess) {
                        if (difference <= 5) {
                            System.out.println("Very Close! But a bit HIGH.");
                        } else if (difference <= 10) {
                            System.out.println("Difference: Bit HIGH.");
                        } else {
                            System.out.println(" Difference:Too HIGH.");
                        }
                    } else {
                        if (difference <= 5) {
                            System.out.println("Very Close! But a bit LOW.");
                        } else if (difference <= 10) {
                            System.out.println("Difference:Bit LOW.");
                        } else {
                            System.out.println(" Difference:Too LOW.");
                        }
                    }
                }
            }

            if (!hasWon) {
                Toolkit.getDefaultToolkit().beep(); // beep for game over
                System.out.println("You've used all attempts. The correct number was: " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); // consume newline
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\nGame Over. You won " + totalScore + " round(s). Thanks for playing!");
        scanner.close();
    }

    // Difficulty selection method
    public static int selectDifficulty(Scanner scanner) {
        System.out.println("\nChoose Difficulty Level:");
        System.out.println("1. Easy (10 attempts)");
        System.out.println("2. Medium (7 attempts)");
        System.out.println("3. Hard (5 attempts)");

        int choice;
        while (true) {
            System.out.print("Enter 1, 2, or 3: ");
            choice = scanner.nextInt();

            if (choice == 1) return 10;
            else if (choice == 2) return 7;
            else if (choice == 3) return 5;
            else System.out.println("Invalid choice. Please try again.");
        }
    }
}
