import java.math.BigInteger;
import java.util.Scanner;

public class Converter {
    private static final String array = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int[] base = new int[2];
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean flag = true;

    public static void start() {
        while (flag) {
            try {
                changeBase();
                convert();
            } catch (Exception e) {
                System.out.println("Error;" + e);
            }
        }
    }

    private static void convert() {
        while (flag) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", base[0], base[1]);
            String input = scanner.nextLine();
            if (input.equals("/back")) {
                break;
            }
            BigInteger num = decimalNumberSystem(input);
            System.out.println("Conversion result: " + toNumberSystem(num));
            System.out.println();
        }
    }

    private static void changeBase() throws IllegalAccessException {
        System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
        String[] input = scanner.nextLine().split(" ");
        if (input[0].equals("/exit")) {
            flag = false;
            return;
        }
        if (input.length != 2) {
            throw new IllegalAccessException("Wrong length");
        }
        base[0] = Integer.parseInt(input[0]);
        base[1] = Integer.parseInt(input[1]);
        if (base[0] < 2 || base[0] > 36) {
            throw new IllegalAccessException("Wrong first parameter");
        }
        if (base[1] < 2 || base[1] > 36) {
            throw new IllegalAccessException("Wrong second parameter");
        }
    }

    private static String toNumberSystem(BigInteger num) {
        StringBuilder output = new StringBuilder();
        BigInteger numberSystem = BigInteger.valueOf(base[1]);
        while (numberSystem.compareTo(num)  <= 0) {
            output.insert(0, array.charAt(num.remainder(numberSystem).intValue()));
            num = num.divide(numberSystem);
        }
        return output.insert(0, array.charAt(num.intValue())).toString();
    }

    private static BigInteger decimalNumberSystem(String num) {
        BigInteger output = BigInteger.ZERO;
        for (int i = 0; i < num.length(); i++) {
            output = output.add(BigInteger.valueOf((long) (array.indexOf(num.charAt(i)) * (Math.pow(base[0], num.length()-i-1)))));
        }
        return output;
    }
}
