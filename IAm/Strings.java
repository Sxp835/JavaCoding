import java.util.Scanner;
//=============================================================================
public class Strings {
//-----------------------------------------------------------------------------
private static Scanner keyboard = new Scanner(System.in);
//-----------------------------------------------------------------------------
	public static void main(String[] args) {

	String sentence;
	String total;
	String sub = "";

	total = "The qualities are ";
	
	System.out.println("Please enter sentences, . to end.");

	do {
		sentence = keyboard.nextLine();
		if(sentence.startsWith("I am")){
			sub=sentence.substring(5);
		
		total+= sub;
		total+= ',';
		}
	} while(!sentence.equals("."));

	System.out.println(total);


	}
//-----------------------------------------------------------------------------
}
//=============================================================================
