import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        int counter = 0, count = 0;
        boolean isVowels = false;
        for (char ch : word.toCharArray()) {
            if (ch == 'a'
                    || ch == 'e'
                    || ch == 'i'
                    || ch == 'o'
                    || ch == 'u'
                    || ch == 'y') {
                if (isVowels) {
                    counter++;
                } else {
                    counter = 1;
                }
                isVowels = true;
            } else {
                if (!isVowels) {
                    counter++;
                } else {
                    counter = 1;
                }
                isVowels = false;
            }
            if (counter == 3) {
                counter = 1;
                ++count;
            }
        }
        System.out.println(count);
    }
}