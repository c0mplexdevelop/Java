package cim.calc;

public class Operations {

    void print(String str) {
        System.out.println(str);
    }

    int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    int subtract(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    int multiply(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }

    int divide(int firstNumber, int secondNumber) {
        return firstNumber / secondNumber;
    }

    int calculate(String expression) {
        String[] numbers = expression.split("[\\+\\-\\*\\/]", 2);
        int firstNumber = Integer.parseInt(numbers[0]);
        int secondNumber = Integer.parseInt(numbers[1]);
        String operator = expression.replaceAll("[0-9]", "");
        print(operator);
        int result = 0;
        switch (operator) {
            case "+":
                result = add(firstNumber, secondNumber);
                break;
            case "-":
                result = subtract(firstNumber, secondNumber);
                break;
            case "*":
                result = multiply(firstNumber, secondNumber);
                break;
            case "/":
                result = divide(firstNumber, secondNumber);
                break;
        }

        return result;
    }
}
