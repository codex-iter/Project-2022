package doc;

public class main {
	Doctor doctor;
	public static Doctor createDoctorDetails() {
		Doctor ob = new Doctor(null, null, null, null);
		ob.set_hospital();
		ob.setDocter();
		return ob;
	}
	public static void main(String[]args) {
	main ob1 = new main();
	Doctor s = ob1.createDoctorDetails();
	System.out.println("");
	System.out.println("Doctor id:"+s.doctorid);
	System.out.println("");
	System.out.println("Doctor Name:"+s.doctorName);
	System.out.println("");
	System.out.println("Specialization:"+s.specialization);
	System.out.println("");
	System.out.println("Hospital Name:"+s.hospital.hospital);
	System.out.println("");
	System.out.println("Contact Number:"+s.hospital.contactNumber);
	System.out.println("");
	System.out.println("City:"+s.hospital.city);
	}
}
