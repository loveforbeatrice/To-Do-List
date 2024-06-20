package Presentation;
import Business.ToDoList;

public class ToDoListApp {
    public static void main(String[] args) {
        ToDoList to = new ToDoList();
        to.jobListRecognizer();
        to.startToDoList();

    }
}