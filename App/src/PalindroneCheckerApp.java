import java.util.*;

interface PalindromeStrategy {
    boolean isPalindrome(String input);
}

class ReverseStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String input) {
        return input.equals(new StringBuilder(input).reverse().toString());
    }
}

class TwoPointerStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String input) {
        char[] chars = input.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            if (chars[i++] != chars[j--]) return false;
        }
        return true;
    }
}

class StackStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) stack.push(c);
        for (char c : input.toCharArray()) {
            if (c != stack.pop()) return false;
        }
        return true;
    }
}

class DequeStrategy implements PalindromeStrategy {
    public boolean isPalindrome(String input) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray()) deque.add(c);
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }
}


public class PalindromeCheckerApp {
    public static void main(String[] args) {
        System.out.println("Palindrome Checker App v1.0");

        String input = "radar";
        String normalized = input.toLowerCase().replaceAll("\\s+", "");

        Map<String, PalindromeStrategy> strategies = new LinkedHashMap<>();
        strategies.put("Reverse", new ReverseStrategy());
        strategies.put("Two-Pointer", new TwoPointerStrategy());
        strategies.put("Stack", new StackStrategy());
        strategies.put("Deque", new DequeStrategy());

        for (Map.Entry<String, PalindromeStrategy> entry : strategies.entrySet()) {
            long start = System.nanoTime();
            boolean result = entry.getValue().isPalindrome(normalized);
            long end = System.nanoTime();

            System.out.println(entry.getKey() + ": " + result + " (" + (end - start) + " ns)");
        }
    }
}