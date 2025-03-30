package controller;

public class EmptyOrderException extends Exception {

	public EmptyOrderException() {
		super("");
	}
	
	public EmptyOrderException(String message) {
		super(message);
	}
}
