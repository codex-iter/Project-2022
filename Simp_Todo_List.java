package Todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simp_Todo_List 
{
	public static List<String> mylist=new ArrayList<String>();
	
	public static void main(String [] args)
	{
		int my=1;
		while(my!=0)
		{
			my= menu();
			switch(my)
			{
			case 1:
				add();
				break;
			case 2:
				remove();
				break;
			case 3:
				showList();
				break;
			case 4:
				clear();
				break;
			case 5:
				return;
			default: 
	            System.out.println("Enter a valid option");
			}
		}
	}
	public static int menu()
	{
		Scanner a= new Scanner(System.in);
		int choice; 
		System.out.println("Main Menu"+"\n");
		System.out.println("1: Add item to list");
		System.out.println("2: Remove item from list");
		System.out.println("3: Display to-do list");
		System.out.println("4: Delete all task from list");
		System.out.println("5: Exit the program"+"\n");
		System.out.print("Enter choice: ");
		choice = a.nextInt();
		a.close();
		return choice;

	}
	public static void add()
	{
		Scanner b= new Scanner(System.in);
		System.out.println("Add Item to the list"+"\n");
		System.out.println("Enter your Task:");
		String item=b.nextLine();
		b.close();
		mylist.add(item);
		System.out.println("Your Task "+item+" Added to the list");
		
	}
	public static void remove()
	{
		Scanner c= new Scanner (System.in);
		System.out.println("Remove Itm from the List"+"\n");
		System.out.println("The serial number of the Task you want to remove: " );
		int no=c.nextInt();
		c.close();
		if((no-1)<0||no>mylist.size())
		{
			System.out.println("Wrong index number! Please enter in range of 1 to "+mylist.size());
		}
		else
		{
			System.out.println("Task no "+no+" "+mylist.get(no-1)+" Removed from the list");
			mylist.remove(no-1);
		}
	}
	public static void showList()
	{
		System.out.println("----------------------");       
        System.out.println("To-Do List");
        System.out.println("----------------------");
        int number = 0;
        for (String item : mylist) {
            System.out.println(++number + " " + item);
        }
        System.out.println("----------------------");
	}
	public static void clear()
	{
		mylist.clear();
	}
}
