package doc;

import java.util.Scanner;

public class Doctor{
	String doctorid;
	String doctorName;
	String specialization;
	Hospital hospital;
	Scanner in = new Scanner(System.in);
	Doctor(String doctorid, String doctorName, String specialization, Hospital hospital){
		this.doctorid = doctorid;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.hospital = new Hospital(specialization, 0, specialization);
	}
	void setDocter() {
		System.out.println("Enter Doctor Id");
		doctorid = in.next();
		System.out.println("Enter Doctor Name");
		doctorName = in.next();
		System.out.println("Enter Specialization");
		specialization = in.next();
	}
	String getDoctorid() {
		return doctorid;
	}
	String getdoctorname() {
		return doctorName;
	}
	String getSpecialization() {
		return specialization;
	}
	void set_hospital(){
		hospital.setHospital();
	}
	void gethospital() {
		hospital.getHospital();
		hospital.getcontactNumber();
		hospital.getcity();
	}
}

