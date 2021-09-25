package com.mycompany.calculyator2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Calc {
 
    public static int eval(String expr) {
        Calc2 c = new Calc2();
        for (String token : tokenize(expr)) {
            if (token.length() == 1 && "+-*/()".contains(token)) {
                c.op(token.charAt(0));
            } else {
                c.num(Integer.parseInt(token));
            }
        }
        return c.eval();
    }

    public static List<String> tokenize(String expr) {
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("[-+\\*/()]|[\\d]+").matcher(expr);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    private static class Calc2 {
        private Stack<Integer> nums = new Stack<Integer>();
        private Stack<Character> ops = new Stack<Character>();

        public void op(char c) {
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (true) {
                    char cc = ops.pop();
                    if (cc == '(') {
                        break;
                    }
                    apply(cc);
                }
            } else {
                while (!ops.empty() && priority(ops.peek()) >= priority(c)) {
                    apply(ops.pop());
                }
                ops.push(c);
            }
        }

        public void num(int v) {
            nums.push(v);
        }

        public int eval() {
            while (!ops.empty()) {
                apply(ops.pop());
            }
            return nums.pop();
        }

        private int priority(char c) {
            if (c == '+') { return 1; }
            if (c == '-') { return 1; }
            if (c == '*') { return 2; }
            if (c == '/') { return 2; }
            return 0;
        }

        private void apply(char c) {
            if (c == '+') {
                int b = nums.pop();
                int a = nums.pop();
                nums.push(a + b);
            } else if (c == '-') {
                int b = nums.pop();
                int a = nums.pop();
                nums.push(a - b);
            } else if (c == '*') {
                int b = nums.pop();
                int a = nums.pop();
                nums.push(a * b);
            } else if (c == '/') {
                int b = nums.pop();
                int a = nums.pop();
                nums.push(a / b);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Введите выражение: ");
        Scanner in = new Scanner(System.in);
	String s = in.nextLine();
        System.out.println("Результат: " + eval(s));
    }
}