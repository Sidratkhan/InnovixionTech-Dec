import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private Scanner scanner;

    public TaskManager() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter due date (yyyy-MM-dd HH:mm): ");
        String dueDateString = scanner.nextLine();
        LocalDateTime dueDate = LocalDateTime.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
        String priorityString = scanner.nextLine();
        Priority priority = Priority.valueOf(priorityString.toUpperCase());

        Task task = new Task(title, dueDate, priority);
        tasks.add(task);

        System.out.println("Task added successfully!");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    taskManager.addTask();
                    break;
                case 2:
                    taskManager.viewTasks();
                    break;
                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Task {
    private String title;
    private LocalDateTime dueDate;
    private Priority priority;

    public Task(String title, LocalDateTime dueDate, Priority priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                '}';
    }
}

enum Priority {
    HIGH, MEDIUM, LOW
}
