import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\b[A-Za-z]{" + size + "}\\b");
        System.out.println(pattern.matcher(line).find() ? "YES" : "NO");
    }
}