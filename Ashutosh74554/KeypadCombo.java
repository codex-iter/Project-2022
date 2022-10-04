package dsQstns;
import java.util.Scanner;
public class KeypadCombo {
	public static String[] keypad= {".","abc","def","ghi","kjl","mno","pqrs","tuv","wxyz"};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a number: ");
		String s=sc.next();
		combo(s,0,"");
	}
	static void combo(String s, int idx, String combination) {
		if(idx==s.length()) {
			System.out.print(combination+", ");
			return;
		}
		char c=s.charAt(idx);
		String mapping=keypad[c-'0'];
		for(int i=0;i<mapping.length();i++) {
			combo(s,idx+1,combination+mapping.charAt(i));
		}
	}
}