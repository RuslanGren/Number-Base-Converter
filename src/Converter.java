package converter;

import java.util.Scanner;

public class Converter {
    private static final String array = "0123456789abcdef";

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("/to")) {
                System.out.println("Enter source number: ");
                String num = scanner.nextLine();
                System.out.println("Enter source base: ");
                int numberSystem = Integer.parseInt(scanner.nextLine());
                System.out.println("Conversion to decimal result: " + to(num.toLowerCase(), numberSystem));
            } else if (action.equalsIgnoreCase("/from")) {
                System.out.println("Enter a number in decimal system: ");
                int num = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter the target base: ");
                int numberSystem = Integer.parseInt(scanner.nextLine());
                System.out.println("Conversion result: " + from(num, numberSystem));
            } else if (action.equalsIgnoreCase("/exit")) {
                break;
            } else {
                System.out.println("unknown command");
            }
            System.out.println();
        }
    }

    private static String from(int num, int numberSystem) {
        StringBuilder output = new StringBuilder();
        while (num >= numberSystem) {
            output.insert(0, array.charAt(num % numberSystem));
            num /= numberSystem;
        }
        return output.insert(0, array.charAt(num)).toString();
    }

    private static long to(String num, int numberSystem) {
        long output = 0;
        for (int i = 0; i < num.length(); i++) {
            output += array.indexOf(num.charAt(i)) * (Math.pow(numberSystem, num.length()-i-1));
        }
        return output;
    }
}
