import java.util.Random;

public class DiceRollSimulator {

    public static void main(String[] args) {
        // Simulate rolling a pair of dice
        int die1 = rollDie();
        int die2 = rollDie();

        // Display the results
        System.out.println("Result of rolling two dice:");
        System.out.println("Die 1: " + die1);
        System.out.println("Die 2: " + die2);
        System.out.println("Total: " + (die1 + die2));
    }

    private static int rollDie() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Generate a random number between 1 and 6
    }
}
