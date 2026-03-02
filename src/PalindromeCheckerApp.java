import java.util.*;

// ============================================
// UC13: Performance Comparison of Algorithms
// ============================================

public class PalindromeCheckerApp{

    // Strategy Interface
    interface PalindromeStrategy {
        boolean checkPalindrome(String text);
        String getName();
    }

    // Stack Strategy
    static class StackStrategy implements PalindromeStrategy {
        public boolean checkPalindrome(String text) {
            String cleaned = text.replaceAll("\\s+", "").toLowerCase();
            Stack<Character> stack = new Stack<>();

            for (char ch : cleaned.toCharArray())
                stack.push(ch);

            StringBuilder reversed = new StringBuilder();
            while (!stack.isEmpty())
                reversed.append(stack.pop());

            return cleaned.equals(reversed.toString());
        }

        public String getName() { return "Stack Strategy"; }
    }

    // Deque Strategy
    static class DequeStrategy implements PalindromeStrategy {
        public boolean checkPalindrome(String text) {
            String cleaned = text.replaceAll("\\s+", "").toLowerCase();
            Deque<Character> deque = new ArrayDeque<>();

            for (char ch : cleaned.toCharArray())
                deque.addLast(ch);

            while (deque.size() > 1) {
                if (!deque.removeFirst().equals(deque.removeLast()))
                    return false;
            }
            return true;
        }

        public String getName() { return "Deque Strategy"; }
    }

    // Reverse String Strategy (baseline)
    static class ReverseStringStrategy implements PalindromeStrategy {
        public boolean checkPalindrome(String text) {
            String cleaned = text.replaceAll("\\s+", "").toLowerCase();
            String reversed = new StringBuilder(cleaned).reverse().toString();
            return cleaned.equals(reversed);
        }

        public String getName() { return "StringBuilder Reverse"; }
    }

    // Performance Test Method
    static void comparePerformance(String input, int iterations) {

        List<PalindromeStrategy> strategies = List.of(
                new StackStrategy(),
                new DequeStrategy(),
                new ReverseStringStrategy()
        );

        System.out.println("\nRunning " + iterations + " iterations...\n");

        for (PalindromeStrategy strategy : strategies) {

            long start = System.nanoTime();

            for (int i = 0; i < iterations; i++)
                strategy.checkPalindrome(input);

            long end = System.nanoTime();
            long duration = end - start;

            System.out.println(strategy.getName() + " Time: " + duration + " ns");
        }
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text for performance testing: ");
        String input = sc.nextLine();

        System.out.print("Enter number of iterations: ");
        int iterations = sc.nextInt();

        comparePerformance(input, iterations);

        sc.close();
    }
}