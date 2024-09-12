import java.util.Scanner;

public class Calcv01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();

        try {
            String result = calculate(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String input) throws Exception {
        input = input.trim();

        if (input.contains("+")) {
            String[] parts = input.split("\\+");
            if (parts.length != 2) throw new Exception("Неверный формат для сложения.");
            String str1 = extractString(parts[0]);
            String str2 = extractString(parts[1]);
            return handleResult(str1 + str2);
        }

        else if (input.contains("-")) {
            String[] parts = input.split("-");
            if (parts.length != 2) throw new Exception("Неверный формат для вычитания.");
            String str1 = extractString(parts[0]);
            String str2 = extractString(parts[1]);
            return handleResult(str1.replace(str2, ""));
        }

        else if (input.contains("*")) {
            String[] parts = input.split("\\*");
            if (parts.length != 2) throw new Exception("Неверный формат для умножения.");
            String str = extractString(parts[0]);
            int multiplier = extractNumber(parts[1]);
            if (multiplier < 1 || multiplier > 10) throw new Exception("Число должно быть от 1 до 10.");
            return handleResult(str.repeat(multiplier));
        }

        else if (input.contains("/")) {
            String[] parts = input.split("/");
            if (parts.length != 2) throw new Exception("Неверный формат для деления.");
            String str = extractString(parts[0]);
            int divisor = extractNumber(parts[1]);
            if (divisor < 1 || divisor > 10) throw new Exception("Число должно быть от 1 до 10.");
            if (str.length() < divisor) throw new Exception("Строка короче делителя.");
            return handleResult(str.substring(0, str.length() / divisor));
        }

        throw new Exception("Операция не поддерживается.");
    }

    private static String extractString(String part) throws Exception {
        part = part.trim();
        if (!part.startsWith("\"") || !part.endsWith("\"")) {
            throw new Exception("Внимание! Строки должны быть в двойных кавычках.");
        }
        String str = part.substring(1, part.length() - 1);
        if (str.length() > 10) {
            throw new Exception("Длина строки должна быть меньше или равной 10 символам.");
        }
        return str;
    }
    private static int extractNumber(String part) throws Exception {
        try {
            return Integer.parseInt(part.trim());
        } catch (NumberFormatException e) {
            throw new Exception("Ожидалось целое число.");
        }
    }

    private static String handleResult(String result) {
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }
}