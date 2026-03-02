import java.util.*;

public class PalindromeCheckerApp {

    // ================= UC8: Linked List =================
    static class Node {
        char data;
        Node next;
        Node(char data) { this.data = data; }
    }

    public static Node createLinkedList(String str) {
        if (str == null || str.length() == 0) return null;
        Node head = new Node(str.charAt(0));
        Node current = head;
        for (int i = 1; i < str.length(); i++) {
            current.next = new Node(str.charAt(i));
            current = current.next;
        }
        return head;
    }

    public static Node reverse(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static boolean isPalindromeLinkedList(Node head) {
        if (head == null || head.next == null) return true;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next;
        Node secondHalf = reverse(slow);
        Node firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    // ================= UC9: Recursion =================
    public static boolean isPalindromeRecursive(String str, int start, int end) {
        if (start >= end) return true;
        if (str.charAt(start) != str.charAt(end)) return false;
        return isPalindromeRecursive(str, start + 1, end - 1);
    }

    // ================= UC10: Ignore Case & Spaces =================
    public static boolean isPalindromeNormalized(String str) {
        String normalized = str.replaceAll("\\s+", "").toLowerCase();
        int start = 0, end = normalized.length() - 1;
        while (start < end) {
            if (normalized.charAt(start) != normalized.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    // ================= UC11: OOP Service =================
    static class PalindromeService {
        public boolean checkPalindrome(String str) {
            int start = 0, end = str.length() - 1;
            while (start < end) {
                if (str.charAt(start) != str.charAt(end))
                    return false;
                start++;
                end--;
            }
            return true;
        }
    }

    // ================= UC12: Strategy Pattern =================
    interface PalindromeStrategy {
        boolean check(String str);
    }

    static class StackStrategy implements PalindromeStrategy {
        public boolean check(String str) {
            Stack<Character> stack = new Stack<>();
            for (char c : str.toCharArray()) stack.push(c);
            for (char c : str.toCharArray())
                if (c != stack.pop()) return false;
            return true;
        }
    }

    static class DequeStrategy implements PalindromeStrategy {
        public boolean check(String str) {
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : str.toCharArray()) deque.addLast(c);
            while (deque.size() > 1)
                if (!deque.removeFirst().equals(deque.removeLast()))
                    return false;
            return true;
        }
    }

    static class PalindromeContext {
        private PalindromeStrategy strategy;
        public PalindromeContext(PalindromeStrategy strategy) {
            this.strategy = strategy;
        }
        public boolean execute(String str) {
            return strategy.check(str);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Palindrome Checker App");
        System.out.println("Version: 2.0");

        while (!exit) {

            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. UC2: Hardcoded");
            System.out.println("2. UC3: Reverse String");
            System.out.println("3. UC4: Two Pointer");
            System.out.println("4. UC5: Stack");
            System.out.println("5. UC6: Queue + Stack");
            System.out.println("6. UC7: Deque");
            System.out.println("7. UC8: Linked List");
            System.out.println("8. UC9: Recursion");
            System.out.println("9. UC10: Ignore Case & Spaces");
            System.out.println("10. UC11: OOP Service");
            System.out.println("11. UC12: Strategy Pattern");
            System.out.println("12. UC13: Performance Comparison");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    String hardcoded = "madam";
                    System.out.println(hardcoded + " is a palindrome.");
                    break;

                case 2:
                    System.out.print("Enter string: ");
                    String s2 = scanner.nextLine();
                    String rev = new StringBuilder(s2).reverse().toString();
                    System.out.println(s2 + (s2.equalsIgnoreCase(rev) ?
                            " is a palindrome." : " is not a palindrome."));
                    break;

                case 3:
                    System.out.print("Enter string: ");
                    String s3 = scanner.nextLine();
                    int start = 0, end = s3.length() - 1;
                    boolean result3 = true;
                    while (start < end) {
                        if (s3.charAt(start++) != s3.charAt(end--)) {
                            result3 = false; break;
                        }
                    }
                    System.out.println(s3 + (result3 ?
                            " is a palindrome." : " is not a palindrome."));
                    break;

                case 4:
                    System.out.print("Enter string: ");
                    String s4 = scanner.nextLine();
                    Stack<Character> stack = new Stack<>();
                    for (char c : s4.toCharArray()) stack.push(c);
                    boolean result4 = true;
                    for (char c : s4.toCharArray())
                        if (c != stack.pop()) { result4 = false; break; }
                    System.out.println(s4 + (result4 ?
                            " is a palindrome." : " is not a palindrome."));
                    break;

                case 5:
                    System.out.print("Enter string: ");
                    String s5 = scanner.nextLine();
                    Queue<Character> queue = new LinkedList<>();
                    Stack<Character> stack5 = new Stack<>();
                    for (char c : s5.toCharArray()) {
                        queue.add(c); stack5.push(c);
                    }
                    boolean result5 = true;
                    while (!queue.isEmpty())
                        if (!queue.remove().equals(stack5.pop())) {
                            result5 = false; break;
                        }
                    System.out.println(s5 + (result5 ?
                            " is a palindrome." : " is not a palindrome."));
                    break;

                case 6:
                    System.out.print("Enter string: ");
                    String s6 = scanner.nextLine();
                    Deque<Character> deque = new ArrayDeque<>();
                    for (char c : s6.toCharArray()) deque.addLast(c);
                    boolean result6 = true;
                    while (deque.size() > 1)
                        if (!deque.removeFirst().equals(deque.removeLast())) {
                            result6 = false; break;
                        }
                    System.out.println(s6 + (result6 ?
                            " is a palindrome." : " is not a palindrome."));
                    break;

                case 7:
                    System.out.print("Enter string: ");
                    String s7 = scanner.nextLine();
                    System.out.println(s7 +
                            (isPalindromeLinkedList(createLinkedList(s7)) ?
                                    " is a palindrome." : " is not a palindrome."));
                    break;

                case 8:
                    System.out.print("Enter string: ");
                    String s8 = scanner.nextLine();
                    System.out.println(s8 +
                            (isPalindromeRecursive(s8, 0, s8.length() - 1) ?
                                    " is a palindrome." : " is not a palindrome."));
                    break;

                case 9:
                    System.out.print("Enter string: ");
                    String s9 = scanner.nextLine();
                    System.out.println(s9 +
                            (isPalindromeNormalized(s9) ?
                                    " is a palindrome." : " is not a palindrome."));
                    break;

                case 10:
                    System.out.print("Enter string: ");
                    String s10 = scanner.nextLine();
                    PalindromeService service = new PalindromeService();
                    System.out.println(s10 +
                            (service.checkPalindrome(s10) ?
                                    " is a palindrome." : " is not a palindrome."));
                    break;

                case 11:
                    System.out.print("Enter string: ");
                    String s11 = scanner.nextLine();
                    System.out.println("Choose Strategy: 1.Stack 2.Deque");
                    int sc = scanner.nextInt();
                    scanner.nextLine();
                    PalindromeStrategy strategy =
                            (sc == 1) ? new StackStrategy() : new DequeStrategy();
                    PalindromeContext context = new PalindromeContext(strategy);
                    System.out.println(s11 +
                            (context.execute(s11) ?
                                    " is a palindrome." : " is not a palindrome."));
                    break;

                case 12:
                    System.out.print("Enter string: ");
                    String s12 = scanner.nextLine();

                    long t1 = System.nanoTime();
                    isPalindromeRecursive(s12, 0, s12.length() - 1);
                    long t2 = System.nanoTime();

                    long t3 = System.nanoTime();
                    new PalindromeService().checkPalindrome(s12);
                    long t4 = System.nanoTime();

                    System.out.println("Recursive Time: " + (t2 - t1) + " ns");
                    System.out.println("OOP Service Time: " + (t4 - t3) + " ns");
                    break;

                case 0:
                    exit = true;
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}1