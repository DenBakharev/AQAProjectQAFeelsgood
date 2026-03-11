package homeworkfromsecondsprint;

public class PersonTask {

    String firstName;
    String lastName;
    int age;

    public PersonTask(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void introduce() {
        System.out.println("Привет, меня зовут " + firstName + " " + lastName + ". Мне " + age + " лет.");
    }
}
