package Todolist;

import java.util.ArrayList;
import java.util.List;


public class ToDoList {
	 private List<Task> toDoList = new ArrayList<>();

	    /**
	     * Inserts a task in the list of task
	     * @param task Task you want to insert in a list.
	     */

	    public void addToDo(Task task) { ToDoList.add(task);}

		private static void add(Task task) {
			// TODO Auto-generated method stub
			
		}

		/**
	     * Remove a task in the list of tasks
	     * @param index Index of task you want to delete from a list.
	     */

	    public void remove(int index) {
	        toDoList.remove(index);
	    }

	    /**
	     * Sets list of Tasks.
	     * @param newTasks list of Tasks.
	     */

	    public void setToDoList(List<Task> newTasks) {
	        toDoList = newTasks;
	    }


	    /**
	     * Return the list of tasks.
	     * @return list of Tasks.
	     */

	    public List<Task> getToDoList() {
	        return toDoList;
	    }

	    /**
	     * Return the task in list of tasks.
	     * @param index index of task you want to return.
	     * @return task in list of task based on specific index.
	     */

	    public Task getTaskInToDo(int index) {
	        return toDoList.get(index);
	    }

}
