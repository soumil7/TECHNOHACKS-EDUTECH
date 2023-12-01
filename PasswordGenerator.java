import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user-defined criteria
        System.out.print("Enter the length of the password: ");
        int passwordLength = scanner.nextInt();

        System.out.print(
                "Enter the complexity (1 for numeric, 2 for alphanumeric, 3 for alphanumeric with special characters): ");
        int complexity = scanner.nextInt();

        // Generate and display the random password
        String password = generatePassword(passwordLength, complexity);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    private static String generatePassword(int length, int complexity) {
        String numericChars = "0123456789";
        String alphaChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'<>,.?/";

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String charsToUse = "";
        switch (complexity) {
            case 1:
                charsToUse = numericChars;
                break;
            case 2:
                charsToUse = numericChars + alphaChars;
                break;
            case 3:
                charsToUse = numericChars + alphaChars + specialChars;
                break;
            default:
                throw new IllegalArgumentException("Invalid complexity level");
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charsToUse.length());
            char randomChar = charsToUse.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
