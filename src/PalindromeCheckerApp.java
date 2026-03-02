import java.util.Scanner;

public class PalindromeCheckerApp {

    // Method to check palindrome ignoring spaces and case
    public static boolean isPalindrome(String str) {
        // Normalize: remove spaces and convert to lowercase
        String normalized = str.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = normalized.length() - 1;

        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check palindrome: ");
        String input = scanner.nextLine();

        if (isPalindrome(input)) {
            System.out.println("\"" + input + "\" is a palindrome (case-insensitive, space ignored).");
        } else {
            System.out.println("\"" + input + "\" is not a palindrome.");
        }

        scanner.close();
    }
}