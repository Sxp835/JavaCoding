package lab03;

import java.util.GregorianCalendar;
// What imports do you need to include? Put them here.

public class LibraryBook extends Book { 

	private String holder;
	private GregorianCalendar dueDate;
	// A LibraryBook contains a holder (a String) and due date represented by
	// a GregorianCalendar
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		dueDate = null;
	}

	public String getHolder() {
		// FILL IN
		return (holder); // placeholder
	}
	
	public GregorianCalendar getDueDate() {
		// FILL IN
		return (dueDate); // placeholder
	}
	
	public void checkin() {
		holder = null;
		dueDate = null; 
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		this.holder = holder;
		this.dueDate = dueDate;
	}	

	// Do not override the equals method in Book

}