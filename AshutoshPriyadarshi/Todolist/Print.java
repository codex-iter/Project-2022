package Todolist;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Print {
	private List<Task> listOfToDos;
    private SimpleDateFormat sdf;

    /**
     * Constructor of Print class.
     */

    Print(List<Task> listOfToDos) {
        this.listOfToDos = listOfToDos;
        this.sdf = new SimpleDateFormat("MM-dd-yyyy");
    }

    /**
     * Print out a heading or template in which below tasks will be displayed.
     */

    public void printHeading() {
        System.out.print(String.format("%1$-27s", "TASK"));
        System.out.print(String.format("%1$-27s", "PROJECT"));
        System.out.print(String.format("%1$-27s", "STATUS"));
        System.out.print(String.format("%1$-27s", "DATE CREATED"));
        System.out.println("DUE DATE");
        System.out.println("--------------------------------" +
                "--------------------------------------------" +
                "----------------------------------------");
    }

    /**
     * Prints list of all the tasks in order of time being added.
     */

    public void printEntireList() {
        printHeading();

        listOfToDos.forEach(this::printBody);
    }

    /**
     * Print out only tasks which are have a status
     * provided in parameter
     * @param status Status of a task
     */

    public void printTasksByStatus(Status status) {
        printHeading();

        listOfToDos.stream()
                .filter(task -> task.getStatus() == status)
                .forEach(this::printBody);

    }

    /**
     * Print only tasks which belong to a certain project.
     */

    public void printTasksByProject() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Select a Project below, by typing its name:");

        printIndexAndNameAndProjectOfTask();
        String project = reader.nextLine().toLowerCase().trim();

        printHeading();

        listOfToDos.stream()
                .filter(task -> task.getProjectName().toLowerCase().trim().equals(project))
                .forEach(this::printBody);

    }

    /**
     * When printing all features of task
     * we use this method to print everything in a pretty
     * and structured way.
     */

    public void printBody(Task task) {

        System.out.print(listOfToDos.indexOf(task) + 1 + ". ");
        System.out.print(String.format("%1$-25s", task.getTitle()));
        System.out.print(String.format("%1$-25s", task.getProjectName()));
        System.out.print(String.format("%1$-28s", task.getStatus()));
        System.out.print(String.format("%1$-25s", sdf.format(task.getCreatedDate())));
        System.out.print(String.format("%1$-25s", sdf.format(task.getDueDate())));
        System.out.println("");
    }

    /**
     * Prints out all the tasks in a pretty format,
     * which are sorted in descending order depending on
     * due Date.
     */

    public void printTaskByDueDate() {
        printHeading();
        listOfToDos.sort(Comparator.comparing(Task::getDueDate));
        listOfToDos.stream().forEach(this::printBody);
    }

    /**
     * Prints only index and name of all individual tasks in the list
     */

    public void printOnlyIndexAndNameOfTask() {
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
    }
    /**
     * Prints index, name and project of all individual tasks in the list
     */

    public void printIndexAndNameAndProjectOfTask() {
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.print(String.format("%1$-25s", list.getTitle()));
            System.out.print(String.format("%1$-25s", list.getProjectName()));
            System.out.println();
        }
    }

    /**
     * Prints index, name and dueDate of all individual tasks in the list
     */

    public void printIndexAndNameAndDueDateOfTask() {
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.print(String.format("%1$-25s", list.getTitle()));
            System.out.print(String.format("%1$-25s", sdf.format(list.getDueDate())));
            System.out.println();
        }
    }

    /**
     * Prints Welcome screen upon starting the application
     * Besides that, it prints amount of tasks that are done and amount that has yet to be completed
     */

    public void printWelcome() {

        System.out.println("\n Welcome to TO-DO application");
        System.out.println(" Pending tasks: " + getBackAmount(Status.PENDING) + " | Completed tasks: " + getBackAmount(Status.DONE));
        System.out.println("\n Pick an option:");
    }

    /**
     * The method goes through the list and counts
     * the amount of tasks that have a status
     * which is selected in Parameter. The method is later used
     * in printWelcome method
     * @param status status of a task to which you want to count
     */

    public int getBackAmount(Status status){
        int counter = 0;

        for(Task list: listOfToDos){
            if(list.getStatus()== status){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Prints general options, so user knows what to press
     * in order to work with application
     */

    public void printOptions() {
        System.out.println("---------------------------------------------");
        System.out.println(" (1) Show Task List:");
        System.out.println(" (2) Add New Task");
        System.out.println(" (3) Edit Task (remove, mark as done, update)");
        System.out.println(" (4) Save and Quit");
    }

    /**
     * Printed method used if user does not enter any of appropriate options.
     */

    public void printNotValiableOption() {
        System.out.println("You have not entered a viable option. Let's try this again. \n");
    }

    /**
     * Prints to inform the user of wrong format for date
     */

    public void printWrongDateFormat() {
        System.out.println("Input of date was in wrong format. REQUIRED FORMAT: (MM-dd-yyyy)");
    }

    /**
     * Prints to inform the user that list is saved and application had been closed.
     */

    public void printWhenQuitApplication() {
        System.out.println("You have quit the application, your TO-DO list is saved.");
    }

    /**
     * Prints to inform the user that index he selected is out of reach
     * (Does not exist)
     */

    public void printIndexOutOfReach() {
        System.out.println("Task with selected index does not exist. Select number in front of task again:");
    }

    /**
     * Prints options for updating a task
     */

    public void printUpdateOptions() {
        System.out.println("Press (1) for editing task name");
        System.out.println("Press (2) for editing project name of a specific task");
        System.out.println("Press (3) for editing due date of a task");
    }

    /**
     * Prints options for editing a task
     */

    public void printEditTaskOptions() {
        System.out.println("Press (1) for removing items from the list");
        System.out.println("Press (2) for marking tasks as done");
        System.out.println("Press (3) for updating tasks");
    }

    /**
     * Prints options on how to sort the list they want to print
     */

    public void printSortingOptions() {
        System.out.println("Here you can print list sorted by:");
        System.out.println("(1): Show all");
        System.out.println("(2): All tasks that are PENDING");
        System.out.println("(3): All tasks that are DONE");
        System.out.println("(4): Print task by PROJECT:");
        System.out.println("(5): Due Date");
    }

    /**
     * Prints to inform the user that if he/she presses enter, options will be displayed again
     */

    public void printPressEnterForMenu() {
        System.out.println("\n Press ENTER to see options again");
    }

}
