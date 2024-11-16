package progexec;

import java.io.*;
import java.util.Stack;

public class MatchGroupingSymbols {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MatchGroupingSymbols <filename>");
            return;
        }

        String fileName = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Stack<Character> stack = new Stack<>();
            int lineNumber = 0;
            boolean isBalanced = true;

            String line;
            while ((line = reader.readLine()) != null && isBalanced) {
                lineNumber++;
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            System.out.println("Unmatched symbol at line " + lineNumber);
                            isBalanced = false;
                            break;
                        }
                    }
                }
            }

            if (isBalanced && stack.isEmpty()) {
                System.out.println("All grouping symbols are correctly matched.");
            } else if (isBalanced) {
                System.out.println("Unmatched opening symbols remain.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
