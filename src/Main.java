import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.");
        System.out.println("2.Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.");
        System.out.println("3.Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
        System.out.println("4.Калькулятор умеет работать только с целыми числами.");
        System.out.println("5.При вводе пользователем неподходящих чисел или не соответствующей одной из вышеописанных арифметических операций,калькулятор выдаст ошибку");
        System.out.println("Введите пожалуйста ваше арифметическое выражение:");
        String calc_line = sc.nextLine();

        String result = calc(calc_line);
        System.out.println(result);

    }


    public static char operand(String in) {
        char op = 'e';
        int flag = 0;

        for (int i = 0; i < in.length(); i++) {
            if (flag >= 2) {
                throw new IllegalStateException("Введены некорректные значения");
            }
            if (in.charAt(i) == '+') {
                op = '+';
                flag++;
            }
            else if (in.charAt(i) == '-'){
                op = '-';
                flag++;
            }
            else if (in.charAt(i) == '*'){
                op = '*';
                flag++;
            }
            else if (in.charAt(i) == '/'){
                op = '/';
                flag++;
            }
        }
        if (flag == 0) {
            throw new IllegalStateException("Введены некорректные значения");
        }

        return op;
    }

    public static String calc(String input) {
        Converter converter = new Converter();

        char op = operand(input);
        int num1 = tuple2numbers(input)[0];
        int num2 = tuple2numbers(input)[1];

        if (!(num1 >= 1 && num1 <= 10) || !(num2 >= 1 && num2 <= 10)) {
            throw new IllegalStateException("Введены некорректные значения");
        }
        int isRoman = tuple2numbers(input)[2];
//        check_errors(num1, num2, op)
        int result = calculate(num1, num2, op);
        if (result == 0) {
            throw new IllegalStateException("Введены некорректные значения");
        } else if (isRoman == 1) {
            if (result <= 0) {
                throw new IllegalStateException("Введены некорректные значения");
            }
            return converter.intToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    public static int[] tuple2numbers(String in) {
        Converter converter = new Converter();

        int[] arr = new int[3];

        try {
            if (Character.isDigit(in.split("[+\\-/*]")[0].trim().charAt(0))) {
                arr[0] = Integer.parseInt(in.split("[+\\-/*]")[0].trim());
                arr[1] = Integer.parseInt(in.split("[+\\-/*]")[1].trim());
            } else {
                arr[0] = converter.romanToInt(in.split("[+\\-/*]")[0].trim());
                arr[1] = converter.romanToInt(in.split("[+\\-/*]")[1].trim());
                arr[2] = 1;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Введены некорректные значения");
        }

        return arr;
    }

    public static int calculate(int num1, int num2, char str){

        return switch (str) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalStateException("Введены некорректные значения");
        };
    }
}
