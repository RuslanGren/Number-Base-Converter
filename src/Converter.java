import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
            System.out.print("Conversion result: ");
            if (input.contains(".")) {
                String[] twoParts = input.split("\\.");
                BigInteger numInteger = toBigInteger(twoParts[0]);
                BigDecimal numDecimal = toBigDecimal(twoParts[1]);
                System.out.println(toNumberSystemInteger(numInteger) + "." + toNumberSystemDecimal(numDecimal));
            } else {
                BigInteger num = toBigInteger(input);
                System.out.println(toNumberSystemInteger(num));
            }
            System.out.println();
        }
    }

    private static void changeBase() throws IllegalArgumentException {
        System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
        String[] input = scanner.nextLine().split(" ");
        if (input[0].equals("/exit")) {
            flag = false;
            return;
        }
        if (input.length != 2) {
            throw new IllegalArgumentException("Wrong length");
        }
        base[0] = Integer.parseInt(input[0]);
        base[1] = Integer.parseInt(input[1]);
        if (base[0] < 2 || base[0] > 36) {
            throw new IllegalArgumentException("Wrong first parameter");
        }
        if (base[1] < 2 || base[1] > 36) {
            throw new IllegalArgumentException("Wrong second parameter");
        }
    }

    private static String toNumberSystemInteger(BigInteger num) {
        StringBuilder output = new StringBuilder();
        BigInteger numberSystem = BigInteger.valueOf(base[1]);
        while (numberSystem.compareTo(num)  <= 0) {
            output.insert(0, array.charAt(num.remainder(numberSystem).intValue()));
            num = num.divide(numberSystem);
        }
        return output.insert(0, array.charAt(num.intValue())).toString();
    }

    private static String toNumberSystemDecimal(BigDecimal num) {
        StringBuilder output = new StringBuilder();
        BigDecimal numberSystem = BigDecimal.valueOf(base[1]);
        int i = 0;
        while (i != 5) {
            num = num.multiply(numberSystem);
            int wholePart = num.divide(BigDecimal.ONE, RoundingMode.UNNECESSARY).intValue();
            output.append(array.charAt(wholePart));
            num = num.subtract(BigDecimal.valueOf(wholePart));
            i++;
        }
        return output.toString();
    }

    private static BigInteger toBigInteger(String num) {
        BigInteger output = BigInteger.ZERO;
        for (int i = 0; i < num.length(); i++) {
            output = output.add(BigInteger.valueOf((long) (array.indexOf(num.charAt(i)) * (Math.pow(base[0], num.length()-i-1)))));
        }
        return output;
    }

    private static BigDecimal toBigDecimal(String num) {
        BigDecimal output = BigDecimal.ZERO;
        for (int i = -1; i >= -num.length(); i--) {
            output = output.add(BigDecimal.valueOf((array.indexOf(num.charAt(-i-1)) * (Math.pow(base[0], i)))));
        }
        return output;
    }
}
