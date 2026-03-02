import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class PalindromeCheckerApp {

    // private stack (encapsulation)
    private Deque<Character> stack;

    // constructor
    public PalindromeChecker() {
        stack = new ArrayDeque<>();
    }

    // private helper: push
    private void push(char ch) {
        stack.push(ch);
    }

    // private helper: pop
    private char pop() {
        return stack.pop();
    }

    // private helper: reverse string using stack
    private String reverseString(String text) {
        stack.clear();

        for (char ch : text.toCharArray()) {
            push(ch);
        }

        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(pop());
        }

        return reversed.toString();
    }

    // public service method
    public boolean checkPalindrome(String text) {
        String cleaned = text.replaceAll("\\s+", "").toLowerCase();
        String reversed = reverseString(cleaned);
        return cleaned.equals(reversed);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PalindromeChecker checker = new PalindromeChecker();

        System.out.print("Enter text or number: ");
        String input = sc.nextLine();

        if (checker.checkPalindrome(input)) {
            System.out.println("Palindrome ✅");
        } else {
            System.out.println("Not a Palindrome ❌");
        }

        sc.close();
    }
}