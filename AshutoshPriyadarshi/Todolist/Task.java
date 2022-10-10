package Todolist;

import java.util.Date;
import java.io.Serializable;

public class Task implements Serializable{
	 private String title;
	    private Date dueDate = new Date();
	    private Date createdDate = new Date();
	    private Status status = Status.PENDING;
	    private String projectName;
	    private static final long serialVersionUID = 8367141910137788612L;

	    /**
	     * Return the title of the task.
	     * @return the title of the task as a String.
	     */

	    public String getTitle() {
	        return title;
	    }

	    /**
	     * Sets the title of the task.
	     * @param title the title of the task.
	     */

	    public void setTitle(String title) { this.title = title;}

	    /**
	     * Return the project of the task.
	     * @return the project of the task as a String.
	     */


	    public String getProjectName(){
	        return projectName;
	    }

	    /**
	     * Sets the project of the task.
	     * @param setProjectName the project of the task.
	     */


	    public void setProjectName(String setProjectName) {
	        projectName = setProjectName;
	    }

	    /**
	     * Return the due date of the task.
	     * @return the due date of the task as a Date.
	     */

	    public Date getDueDate() {
	        return dueDate;
	    }

	    /**
	     * Sets the date of the task.
	     * @param dueDate the due date of the task.
	     */

	    public void setDueDate(Date dueDate) { this.dueDate = dueDate;}

	    /**
	     * Return the status of the task.
	     * @return the status of the task.
	     */

	    public Status getStatus() {
	        return status;
	    }

	    /**
	     * Sets the date of the task.
	     * @param setStatus the due date of the task.
	     */

	    public void setStatus(Status setStatus) {
	        this.status = setStatus;
	   }

	    /**
	     * Return the created date of the task.
	     * @return the created date of the task as a Date.
	     */

	    public Date getCreatedDate() { return createdDate;}

	    /**
	     * Sets the created date of the task.
	     * @param createdDate the created date of the task.
	     */

	    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate;}


}
