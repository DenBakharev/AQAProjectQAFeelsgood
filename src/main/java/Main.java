import homeworkfromsecondsprint.BudgetTask;
import homeworkfromsecondsprint.FruitsTask;
import homeworkfromsecondsprint.PersonTask;

public class Main {
    public static void main(String[] args) {

        PersonTask personNikolay = new PersonTask("Nikolay", "Baskov", 25);
        personNikolay.introduce();

        FruitsTask fruitsTask = new FruitsTask();
        fruitsTask.showFruits();

        BudgetTask budgetTask = new BudgetTask();
        budgetTask.checkBudget();
        budgetTask.sumBudget();
    }
}