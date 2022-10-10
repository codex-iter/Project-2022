package Todolist;
import Todolist.Status;
import Todolist.Task;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
	 @Test
	    public void titleOfATaskIsRetrieved() {

	        Task task = new Task();
	        task.setTitle("Homework");

	        final String initialTitle = task.getTitle();


	        assertEquals(initialTitle, "Homework");
	    }

	    @Test
	    public void titleOfATaskCanBeChanged() {

	        Task task = new Task();
	        task.setTitle("Homework");

	        task.setTitle("HomeworkChange");

	        final String changedTitle = task.getTitle();


	        assertEquals(changedTitle, "Homework" + "Change");
	    }

	    @Test
	    public void projectNameCanBeSet() {

	        Task task = new Task();
	        task.setProjectName("Study");

	        final String initialProjectName = task.getProjectName();


	        assertEquals(initialProjectName, "Study");
	    }

	    @Test
	    public void titleOfAProjectCanBeChanged() {

	        Task task = new Task();
	        task.setProjectName("Study");

	        task.setProjectName("StudyChange");

	        final String changedProjectName = task.getProjectName();


	        assertEquals(changedProjectName, "Study" + "Change");
	    }

	    @Test
	    public void dueDateCanBeSet() throws ParseException {

	        Task task = new Task();

	        Date sdf  = new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" );
	        task.setDueDate(sdf);

	        Date setDate = task.getDueDate();
	        Date testedDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" );

	        assertEquals(setDate, testedDate);
	    }

	    @Test
	    public void taskStatusCanBeSet() throws ParseException {

	        Task task = new Task();

	        task.setStatus(Status.DONE);
	        Status retrievedStatus = task.getStatus();

	        assertEquals(retrievedStatus, Status.DONE);
	    }

	    @Test
	    public void taskStatusCanBeChanged() throws ParseException {

	        Task task = new Task();

	        task.setStatus(Status.DONE);
	        task.setStatus(Status.PENDING);
	        Status retrievedStatus = task.getStatus();

	        assertEquals(retrievedStatus, Status.PENDING);
	    }


}
