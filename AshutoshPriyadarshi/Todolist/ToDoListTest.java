package Todolist;
import Todolist.Task;
import Todolist.ToDoList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
	 @Test
	    public void newTaskCanBeAdded() {
	        ToDoList toDoList = new ToDoList();
	        Task task = new Task();
	        task.setTitle("Homework");
	        task.setProjectName("School");

	        final int initialSize = toDoList.getToDoList().size();

	        toDoList.addToDo(task);

	        assertEquals(initialSize + 1, toDoList.getToDoList().size());
	    }

	    @Test
	    public void removeTaskThrowsIndexOutOfBounds() {
	        ToDoList list = new ToDoList();

	        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.getToDoList().size()));
	    }

	    @Test
	    public void FirstTaskCanBeRemoved() {
	        ToDoList toDoList = new ToDoList();

	        Task task = new Task();
	        task.setTitle("Homework");
	        task.setProjectName("School");
	        toDoList.addToDo(task);

	        final int initialSize = toDoList.getToDoList().size();

	        toDoList.remove(0);

	        assertEquals(initialSize - 1, toDoList.getToDoList().size());
	    }

	    @Test
	    public void FirstTaskCanBeRetrievedFromList() {
	        ToDoList toDoList = new ToDoList();

	        Task task = new Task();
	        task.setTitle("Homework");
	        task.setProjectName("School");
	        toDoList.addToDo(task);

	        final Task retrievedFirstTask = toDoList.getTaskInToDo(0);

	        assertEquals(retrievedFirstTask, task);
	    }

	    @Test
	    public void ListCanBeRetrieved() {
	        List<Task> list = new ArrayList<Task>();
	        ToDoList toDoList = new ToDoList();

	        toDoList.setToDoList(list);

	        final List<Task> retrievedList = toDoList.getToDoList();

	        assertEquals(retrievedList, list);
	    }

}
