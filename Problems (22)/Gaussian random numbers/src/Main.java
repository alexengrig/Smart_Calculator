import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long k = scanner.nextLong();
        double n = scanner.nextDouble();
        double m = scanner.nextDouble();
        long seed = k - 1;
        retry:
        while (true) {
            Random random = new Random(++seed);
            for (int i = 0; i < n; i++) {
                double gaussian = random.nextGaussian();
                if (gaussian > m) {
                    continue retry;
                }
            }
            System.out.println(seed);
            break;
        }
    }
}