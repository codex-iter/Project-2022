package doc;
import java.util.*;
import java.lang;

public class Hospital {
	String hospital;
	long contactNumber;
	String city;
	Scanner in = new Scanner(System.in);
	Hospital(String hospital, long contactNumber, String city){
		this.hospital = hospital;
		this.contactNumber = contactNumber;
		this.city = city;
	}
	void setHospital() {
		System.out.println("Enter Hospital Name");
		hospital = in.next();
		System.out.println("Enter Contact Number");
		contactNumber = in.nextLong();
		System.out.println("Enter City");
		city = in.next();
	}
	String getHospital() {
		return hospital;
	}
	Long getcontactNumber() {
		return contactNumber;
	}
	String getcity() {
		return city;
	}
}
