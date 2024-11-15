
import java.util.ArrayList;
import java.util.Scanner;

class todo {

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<Boolean> taskStatus = new ArrayList<>(); // Stores the completion status of tasks
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            int userInput = scanner.nextInt();
            scanner.nextLine(); 

            switch (userInput) {
                // Add a new task
                case 1:
                    System.out.print("Enter the task description: ");
                    String newTask = scanner.nextLine();
                    tasks.add(newTask);
                    taskStatus.add(false); // Initially mark the task as incomplete
                    System.out.println("Task added successfully.");
                    break;

                // View all tasks
                case 2:
                    System.out.println("Your Tasks:");
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            String status = taskStatus.get(i) ? "Completed" : "Pending";
                            System.out.println((i + 1) + ". " + tasks.get(i) + " [" + status + "]");
                        }
                    }
                    break;

                // Mark a task as completed
                case 3:
                    System.out.print("Enter the task number to mark as completed: ");
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        taskStatus.set(taskNumber - 1, true); // Mark task as completed
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                // Delete a task
                case 4:
                    System.out.print("Enter the task number to delete: ");
                    int deleteTaskNumber = scanner.nextInt();
                    if (deleteTaskNumber > 0 && deleteTaskNumber <= tasks.size()) {
                        tasks.remove(deleteTaskNumber - 1);
                        taskStatus.remove(deleteTaskNumber - 1);
                        System.out.println("Task deleted successfully.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                // Exit the program
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
    }
}
