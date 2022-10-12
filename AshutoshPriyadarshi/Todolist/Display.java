package Todolist;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Display {
	private Scanner reader;
    private ToDoList toDoList;
    private FileHandeler fileSave;
    private Print printer;
    private SimpleDateFormat sdf;

    /**
     * Constructor of Display class.
     * Creates a new Display, which loads previously saved file.
     */

    public Display() throws IOException, ClassNotFoundException {
        reader = new Scanner(System.in);
        toDoList = new ToDoList();
        fileSave = new FileHandeler();
        sdf = new SimpleDateFormat("MM-dd-yyyy");

        FileHandeler fileHandler = new FileHandeler();
        toDoList.setToDoList(fileHandler.loadFromFile());
        printer = new Print(toDoList.getToDoList());
    }

    /**
     * Method asks for user input and
     * returns it.
     */

    public String userInput() {
        return reader.nextLine();
    }

    /**
     * Method that welcomes the user into the application
     * and gives them possible options.
     */

    public void start() throws IOException, ClassNotFoundException {
        printer.printWelcome();
        response();
    }

    /**
     * Method with the main routine that will keep program running
     * until user wants to quit.
     */

    public void response() {
        while (true) {
            printer.printOptions();

            switch (userInput()) {
                case "1":
                    orderListOptions();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    editTask();
                    break;
                case "4":
                    quitAndSave();
                    return;
                default:
                    printer.printNotValiableOption();
            }
            printer.printPressEnterForMenu();
            userInput();
        }

    }

    /**
     * Method adds the Task into the list of tasks.
     * It records the date task was created and correct
     * user if he/she enter dueDate in wrong format.
     */

    public void addTask() {
        System.out.println("Add New Task \n");
        System.out.println("Name of Task:");

        Task task = new Task();
        task.setTitle(userInput());
        System.out.println("Name of Project");
        task.setProjectName(userInput());
        System.out.println("Due date? (MM-dd-yyyy)");

        while (true) {
            try {
                task.setDueDate(sdf.parse(userInput()));
                break;
            } catch (ParseException e) {
                printer.printWrongDateFormat();
            }
        }

        Date date = new Date();
        String strDate = sdf.format(date);
        try {
            task.setCreatedDate(sdf.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        toDoList.addToDo(task);
        System.out.println("Task: " + task.getTitle() + " was added");
    }

    /**
     * Method quits the application and saves the file.
     */

    public void quitAndSave() {
        printer.printWhenQuitApplication();
        try {
            fileSave.saveToFile(toDoList.getToDoList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method changes status of task from PENDING to DONE.
     */

    public void getTaskNumberAndChangeItToDone() {

        System.out.println("What task do you want to mark as done? \n ");
        printer.printOnlyIndexAndNameOfTask();
        System.out.println("\n Enter the number in front of the task that you want to mark DONE (0 -> return to Menu)");
        Task searched;

        while (true) {
            try {
                int getTaskByNumber = Integer.parseInt(userInput());

                if (getTaskByNumber != 0) {
                    searched = toDoList.getTaskInToDo(getTaskByNumber - 1);
                    break;
                } else {
                    response();
                }

            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        searched.setStatus(Status.DONE);
        System.out.println("Task is now set to DONE");
    }

    /**
     * Method removes a task requested from a user from the list.
     */

    public void removeTask() {
        System.out.println("What of the below project do you want to delete? \n");
        printer.printOnlyIndexAndNameOfTask();
        System.out.println("\n Enter the number in front of the task you would like to delete (0 -> return to Menu)");

        while (true) {
            try {
                int removeProjectByNumber = Integer.parseInt(userInput());
                if (removeProjectByNumber != 0) {
                    toDoList.remove(removeProjectByNumber - 1);
                    break;
                } else {
                    response();
                }
            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        System.out.println("Task is removed");

    }

    /**
     * Method had functionality of editing name, editing project and a editing due date.
     */


    public void update() {
        //has a functionality of editing name, project and date of the project
        // get their name
        printer.printUpdateOptions();
        switch (userInput()) {
            case "0":
                response();
                break;
            case "1":
                editName();
                break;
            case "2":
                editProject();
                break;
            case "3":
                editDate();
                break;
            default:
                printer.printNotValiableOption();
                update();
        }
    }

    /**
     * User can select a Task and change its name
     */

    public void editName() {
        System.out.println("Here you can edit a name for one of your tasks:\n ");
        printer.printOnlyIndexAndNameOfTask();
        System.out.println("\n Enter the number in front of the task which name you want to switch (0 -> Return to Menu)");
        Task searched;

        while (true) {
            try {
                int getTitleByNumber = Integer.parseInt(userInput());

                if (getTitleByNumber != 0) {
                    searched = toDoList.getTaskInToDo(getTitleByNumber - 1);
                    break;
                } else {
                    response();
                }
            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        System.out.println("What do you want to switch the name of " + searched.getTitle() + " to?");
        String newName = userInput();
        searched.setTitle(newName);
        System.out.println("Name edited to " + newName);
    }

    /**
     * User can select a Project a specific task is assigned to and change its name
     */

    public void editProject() {
        System.out.println("Here you can edit a name of a project that your task is assigned to\n ");
        printer.printIndexAndNameAndProjectOfTask();
        System.out.println("\n Enter the number in front of the task you want to switch the project to (0 -> Return to Menu) ");
        Task searched;

        while (true) {
            try {
                int getProjectByNumber = Integer.parseInt(userInput());

                if(getProjectByNumber != 0){
                    searched = toDoList.getTaskInToDo(getProjectByNumber - 1);
                    break;
                }
                else{
                    response();
                }

            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        System.out.println("What project should " + searched.getTitle() + " belong to?");
        String newProject = userInput();
        searched.setProjectName(newProject);
        System.out.println(searched.getTitle() + " now belongs to project: " + newProject);
    }

    /**
     * User can select a Task and change its due date.
     */

    public void editDate() {
        System.out.println("Here you can edit due date of one of below tasks: \n ");
        printer.printIndexAndNameAndDueDateOfTask();
        System.out.println("\n Enter the number in front of the task you want to switch due date (0 -> Return to Menu)");

        try {
            int getProjectByNumber = Integer.parseInt(userInput());

            if(getProjectByNumber != 0){
            Task searched = toDoList.getTaskInToDo(getProjectByNumber - 1);
            System.out.println("Enter new due date of task " + searched.getTitle() + " below (MM-dd-yyyy)");

            while (true) {
                try {
                    searched.setDueDate(sdf.parse(userInput()));
                    break;
                } catch (ParseException e) {
                    printer.printWrongDateFormat();
                }
            }
            System.out.println(searched.getTitle() + " Due Date is set to " + sdf.format(searched.getDueDate()));}
            else{
                response();
            }
        } catch (NumberFormatException e) {
            printer.printIndexOutOfReach();
            editDate();
        }
    }

    /**
     * Method contains all functionality that goes into editing a task...
     * Removing, Marking as Done, Updating
     */


    public void editTask() {
        //method with all edit subclasses
        printer.printEditTaskOptions();

        switch (userInput()) {
            case "0":
                response();
                break;
            case "1":
                removeTask();
                break;
            case "2":
                getTaskNumberAndChangeItToDone();
                break;
            case "3":
                update();
                break;
            default:
                printer.printNotValiableOption();
                editTask();
        }
    }

    /**
     * Method is responsible for functionality on what what happens
     * depending on how the user wants to sort the list.
     */

    public void orderListOptions() {
        printer.printSortingOptions();

        switch (userInput()) {
            case "0":
                response();
                break;
            case "1":
                printer.printEntireList();
                break;
            case "2":
                printer.printTasksByStatus(Status.PENDING);
                break;
            case "3":
                printer.printTasksByStatus(Status.DONE);
                break;
            case "4":
                printer.printTasksByProject();
                break;
            case "5":
                printer.printTaskByDueDate();
                break;
            default:
                printer.printNotValiableOption();
                orderListOptions();
        }
    }


}
