import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String one = scanner.nextLine();
        String[] chunks = one.split("\\s+");
        HashMap<String, Integer> map = new HashMap<>();
        for (String chunk : chunks) {
            if (map.containsKey(chunk)) {
                Integer value = map.get(chunk);
                map.put(chunk, value + 1);
            } else {
                map.put(chunk, 1);
            }
        }
        String two = scanner.nextLine();
        String[] words = two.split("\\s");
        for (String word : words) {
            if (!map.containsKey(word) || map.get(word) == 0) {
                System.out.println("You are busted");
                return;
            } else {
                Integer value = map.get(word);
                map.put(word, value - 1);
            }
        }
        System.out.println("You get money");
    }
}