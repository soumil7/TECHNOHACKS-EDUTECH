import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        // Define quiz questions and answers
        String[] questions = {
                "What is the capital of France?",
                "Which planet is known as the Red Planet?",
                "What is the largest mammal in the world?",
        };

        String[][] options = {
                { "A. Berlin", "B. Paris", "C. Madrid", "D. Rome" },
                { "A. Venus", "B. Mars", "C. Jupiter", "D. Saturn" },
                { "A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Gorilla" }
        };

        char[] correctAnswers = { 'B', 'B', 'B' };

        // Display and process each question
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            // Display answer choices
            for (String option : options[i]) {
                System.out.println(option);
            }

            // Get user input
            System.out.print("Your answer (Enter A, B, C, or D): ");
            char userAnswer = scanner.next().toUpperCase().charAt(0);

            // Check the answer
            if (userAnswer == correctAnswers[i]) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswers[i] + ".\n");
            }
        }

        // Display the final score
        System.out.println("Your final score: " + score + "/" + questions.length);
    }
}
