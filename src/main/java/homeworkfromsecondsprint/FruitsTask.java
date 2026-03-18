package homeworkfromsecondsprint;

import java.util.Arrays;
import java.util.List;

public class FruitsTask {

    List<String> fruits = Arrays.asList("Яблоко", "Банан", "Апельсин", "Манго", "Киви");

    public void showFruits() {

        int count = 1;
        for (String fruit : fruits) {
            System.out.println(count + ". " + fruit);
            count++;
        }
    }
}
