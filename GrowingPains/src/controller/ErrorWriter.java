package controller;
//GROWING PAINS - Mark Lambert - C00192497
//ErrorWriter class
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;

/**
 * ErrorWriter class, creates a class that Formats any exceptions and writes them to a file
 */
public class ErrorWriter {
	private Formatter output;
	
	/**
	 * Opens a file "error.txt" when called
	 */
	public void openFile() {
		try {
			output = new Formatter("error.txt");
		}catch(SecurityException s) {
			System.err.println("You do not have write access to this file");
		}catch(FileNotFoundException f) {
			System.err.println("Error opening or creating file");
		}
	}
	
	/**
	 * Method to write an error to a file
	 * @param type The type of exception that occured
	 * @param message A message describing in more detail the error
	 */
	public void logError(String type, String message) {
		try {
            output.format("%s: %s%n", type, message);
            //Calling .flush on the formatter forces it to print the error straight to the txt file without any buffer between
            //calls (doesn't have to wait for a list of errors to build up/certain amount of time/ close application etc)
            output.flush();
		}catch (FormatterClosedException f) {
			System.err.println("Error writing to file");
		}
	}
	
	/**
	 * Closes the file on call
	 */
	public void closeFile (){
		if (output != null )
		output. close () ;
		}
		
} 

