import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        String regex = "(\\d\\d?|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d\\d?|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d\\d?|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d\\d?|1\\d{2}|2[0-4]\\d|25[0-5])";
        boolean matches = ip.matches(regex);
        System.out.println(matches ? "YES" : "NO");
    }
}