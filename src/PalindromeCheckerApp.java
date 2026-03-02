import java.util.*;
public class PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the Palindrome Checker App");
        System.out.println("Version: 1.0");
        while (!exit) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. UC2: Hardcoded Palindrome Result");
            System.out.println("2. UC3: String Reverse Method");
            System.out.println("3. UC4: Character Array (Two-Pointer)");
            System.out.println("4. UC5: Stack-Based Check");
            System.out.println("5. UC6: Queue + Stack Comparison");
            System.out.println("6. UC7: Deque-Based Optimized Check");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:

                    String hardcoded = "madam";
                    System.out.println("Checking hardcoded word: " + hardcoded);
                    if (hardcoded.equals("madam")) {
                        System.out.println(hardcoded + " is a palindrome.");
                    }
                    break;

                case 2:
                    System.out.print("Enter string: ");
                    String input3 = scanner.nextLine();
                    String reversed3 = "";
                    for (int i = input3.length() - 1; i >= 0; i--) {
                        reversed3 += input3.charAt(i);
                    }
                    System.out.println(input3 + (input3.equalsIgnoreCase(reversed3) ? " is a palindrome." : " is not a palindrome."));
                    break;

                case 3:

                    System.out.print("Enter string: ");
                    String input4 = scanner.nextLine();
                    char[] charArray = input4.toCharArray();
                    boolean isPalindrome4 = true;
                    int start = 0, end = charArray.length - 1;
                    while (start < end) {
                        if (charArray[start] != charArray[end]) {
                            isPalindrome4 = false;
                            break;
                        }
                        start++;
                        end--;
                    }
                    System.out.println(input4 + (isPalindrome4 ? " is a palindrome." : " is not a palindrome."));
                    break;

                case 4:

                    System.out.print("Enter string: ");
                    String input5 = scanner.nextLine();
                    Stack<Character> stack5 = new Stack<>();
                    for (char c : input5.toCharArray()) stack5.push(c);
                    String reversed5 = "";
                    while (!stack5.isEmpty()) reversed5 += stack5.pop();
                    System.out.println(input5 + (input5.equalsIgnoreCase(reversed5) ? " is a palindrome." : " is not a palindrome."));
                    break;

                case 5:

                    System.out.print("Enter string: ");
                    String input6 = scanner.nextLine();
                    Queue<Character> queue6 = new LinkedList<>();
                    Stack<Character> stack6 = new Stack<>();
                    for (char c : input6.toCharArray()) {
                        queue6.add(c);
                        stack6.push(c);
                    }
                    boolean isPalindrome6 = true;
                    while (!queue6.isEmpty()) {
                        if (!queue6.remove().equals(stack6.pop())) {
                            isPalindrome6 = false;
                            break;
                        }
                    }
                    System.out.println(input6 + (isPalindrome6 ? " is a palindrome." : " is not a palindrome."));
                    break;

                case 6:
                    System.out.print("Enter string: ");
                    String input7 = scanner.nextLine();
                    Deque<Character> deque7 = new ArrayDeque<>();
                    for (char c : input7.toCharArray()) deque7.addLast(c);
                    boolean isPalindrome7 = true;
                    while (deque7.size() > 1) {
                        if (!deque7.removeFirst().equals(deque7.removeLast())) {
                            isPalindrome7 = false;
                            break;
                        }
                    }
                    System.out.println(input7 + (isPalindrome7 ? " is a palindrome." : " is not a palindrome."));
                    break;

                case 0:
                    exit = true;
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
