import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        int rangeStart = 1;
        int rangeEnd = 100;
        int randomNumber = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
        
        int maxAttempts = 5;
        int score = 0;
        
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Welcome to Guess the Number Game!");
            System.out.println("I am thinking of a number between " + rangeStart + " and " + rangeEnd + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");
            System.out.println("Let's begin!\n");
            
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                sc.nextLine(); // Consume newline character
                
                attempts++;
                
                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    score += maxAttempts - attempts + 1;
                    guessedCorrectly = true;
                } else if (guess < randomNumber) {
                    System.out.println("Your guess is low. Guess again.");
                } else {
                    System.out.println("Your guess is high. Guess again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("\nSorry, you have used all your attempts.");
                System.out.println("The number to be guessed was: " + randomNumber);
            }
            
            System.out.println("Your score: " + score);
            
            System.out.print("\nDo you want to play again? (Y/N): ");
            String playAgainInput = sc.nextLine();
            
            if (!playAgainInput.equalsIgnoreCase("Y")) {
                playAgain = false;
            }
            
            System.out.println();
        }
        
        System.out.println("Thank you for playing Guess the Number Game! Goodbye!");
        sc.close();
    }
}
