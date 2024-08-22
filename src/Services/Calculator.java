package Services;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static double calculator(String expression) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else {
                numbers.add(Double.parseDouble(currentNumber.toString()));
                currentNumber.setLength(0);
                operators.add(c);
            }
        }
        numbers.add(Double.parseDouble(currentNumber.toString()));

        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            if (operator == '*' || operator == '/') {
                double result = performOperation(numbers.remove(i), numbers.remove(i), operator);
                numbers.add(i, result);
                operators.remove(i);
                i--;
            }
        }

        double result = numbers.getFirst();
        for (int i = 0; i < operators.size(); i++) {
            char operator = operators.get(i);
            result = performOperation(result, numbers.get(i + 1), operator);
        }

        return result;
    }

    private static double performOperation(double num1, double num2, char operator) {
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) throw new ArithmeticException("Divisão por zero não é permitida.");
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("Operador inválido: " + operator);
        };
    }
}