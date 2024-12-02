package app.virtualmachine;

public class Constants {
    static final int RAM_SIZE = 24576;

    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        for (int i = binary.length() - 1; i > 0; i--) {
            decimal += Math.pow(2, binary.length() - 1 - i) * Character.digit(binary.charAt(i), 10);
        }
        return decimal;
    }
    public static String decimalToBinary(int n) {
        String binary = "";
        for (int i = 15; i >= 0; i--) {
            int k = n >> i;
            if ((k & 1) > 0)
                binary += "1";
            else
                binary += "0";
        }
        return binary;
    }
    public static String negateBinary(String binary) {
        String result = "";
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') result += "1";
            else result += "0";
        }
        return result;
    }
    public static int negateDecimal(int decimal) {
        return binaryToDecimal(negateBinary(decimalToBinary(decimal)));
    }
}
