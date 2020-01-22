import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
        String c = scanner.next();
        String d = scanner.next();
        BigInteger result = new BigInteger(a).negate()
                .multiply(new BigInteger(b))
                .add(new BigInteger(c))
                .subtract(new BigInteger(d));
        System.out.println(result);
    }
}