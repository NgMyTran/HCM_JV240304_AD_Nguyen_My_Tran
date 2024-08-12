package Exam_Advance_2;

import java.util.Scanner;
import java.util.Stack;

public class MainExam2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập vào số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();

        if (isbn.length() != 10 || !isbn.matches("\\d+")) {
            System.out.println("Độ dài dãy số ISBN không hợp lệ. Vui lòng nhập lại.");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        for (char c : isbn.toCharArray()) {
            stack.push(Character.getNumericValue(c));
        }

        int sum = 0;
        for (int i = 1; !stack.isEmpty(); i++) {
            sum += i * stack.pop();
        }

        if (sum % 11 == 0) {
            System.out.println("Số ISBN hợp lệ.");
        } else {
            System.out.println("Số ISBN không hợp lệ.");
        }
        scanner.close();
    }
}
