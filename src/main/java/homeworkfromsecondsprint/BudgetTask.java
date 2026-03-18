package homeworkfromsecondsprint;

import java.util.Arrays;
import java.util.List;

public class BudgetTask {

    List<Double> prices = Arrays.asList(159.59, 56.89, 35.87, 56.42, 122.2);
    double budget = 326.45;

    public void checkBudget() {
        double doubleCount = 0;
        //        1. Посчитать общую сумму всех товаров
        for (Double price : prices) {
            doubleCount += price;
        }
        //        2. Вывести, хватает ли бюджета
        //        3. Если хватает - вывести остаток
        if (budget > doubleCount) {
            System.out.println("Бюджета хватает." + " Остаток " + (budget - doubleCount));
        } else {
            //    4. Если не хватает - вывести, сколько не хватает
            System.out.println("Не хватает." + (doubleCount - budget));
        }
    }

    public void sumBudget() {
        double doubleSum = 0;
        for (int i = 0; i < prices.size(); i++) {
            doubleSum += prices.get(i);
            if (doubleSum > budget) {
                System.out.println("Куплено: " + i + " товара на сумму " + doubleSum +
                        " Остаток бюджета: " + (doubleSum - budget) +
                        " Не куплено: " + (prices.size() - i) + " товара");
                break;
            }
        }
        if (budget > doubleSum) {
            System.out.println("Хватает на все");
        }
    }
}
