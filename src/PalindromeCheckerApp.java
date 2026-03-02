import java.util.*;

// ===============================
// UC12: Strategy Pattern Example
// ===============================

public class PalindromeCheckerApp {

    // 1️⃣ Strategy Interface
    interface PalindromeStrategy {
        boolean checkPalindrome(String text);
    }

    // 2️⃣ Stack Strategy Implementation
    static class StackStrategy implements PalindromeStrategy {

        @Override
        public boolean checkPalindrome(String text) {
            String cleaned = text.replaceAll("\\s+", "").toLowerCase();
            Stack<Character> stack = new Stack<>();

            for (char ch : cleaned.toCharArray()) {
                stack.push(ch);
            }

            StringBuilder reversed = new StringBuilder();
            while (!stack.isEmpty()) {
                reversed.append(stack.pop());
            }

            return cleaned.equals(reversed.toString());
        }
    }

    // 3️⃣ Deque Strategy Implementation
    static class DequeStrategy implements PalindromeStrategy {

        @Override
        public boolean checkPalindrome(String text) {
            String cleaned = text.replaceAll("\\s+", "").toLowerCase();
            Deque<Character> deque = new ArrayDeque<>();

            for (char ch : cleaned.toCharArray()) {
                deque.addLast(ch);
            }

            while (deque.size() > 1) {
                if (!deque.removeFirst().equals(deque.removeLast())) {
                    return false;
                }
            }

            return true;
        }
    }

    // 4️⃣ Context Class
    static class PalindromeService {

        private PalindromeStrategy strategy;

        public PalindromeService(PalindromeStrategy strategy) {
            this.strategy = strategy;
        }

        public void setStrategy(PalindromeStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean execute(String text) {
            return strategy.checkPalindrome(text);
        }
    }

    // 5️⃣ Main Method (Client)
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Select Palindrome Algorithm");
        System.out.println("1 - Stack Strategy");
        System.out.println("2 - Deque Strategy");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        PalindromeStrategy strategy;

        if (choice == 1) {
            strategy = new StackStrategy();
        } else {
            strategy = new DequeStrategy();
        }

        PalindromeService service = new PalindromeService(strategy);

        System.out.print("Enter text: ");
        String input = sc.nextLine();

        if (service.execute(input)) {
            System.out.println("Palindrome ✅");
        } else {
            System.out.println("Not a Palindrome ❌");
        }

        sc.close();
    }
}