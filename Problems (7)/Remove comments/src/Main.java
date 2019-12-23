import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String codeWithComments = scanner.nextLine();

        String multiLine = "/\\*.*?\\*/";
        String oneLine = "//.*\\n?";
        String empty = "";
        String codeWithoutComments = codeWithComments.replaceAll(multiLine, empty).replaceAll(oneLine, empty);
        System.out.println(codeWithoutComments);
    }
}