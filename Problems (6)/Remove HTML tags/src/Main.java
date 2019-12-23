import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithHTMLTags = scanner.nextLine();

        String stringWithoutHTMLTags = stringWithHTMLTags.replaceAll("<[/a-zA-Z0-9 \"=\\-]+>", "");
        System.out.println(stringWithoutHTMLTags);
    }
}