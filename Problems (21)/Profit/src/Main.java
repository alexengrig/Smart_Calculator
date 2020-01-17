import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int p = scanner.nextInt();
        int k = scanner.nextInt();
        double sum = m;
        int years = 0;
        while (sum < k) {
            sum += (sum * p) / 100;
            ++years;
        }
        System.out.println(years);
    }
}