import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile(".*\\w" + part + "\\w.*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        boolean matches = matcher.find();
        System.out.println(matches ? "YES" : "NO");
    }
}