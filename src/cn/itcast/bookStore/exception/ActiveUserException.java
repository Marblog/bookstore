package cn.itcast.bookStore.exception;

public class ActiveUserException extends Exception {
	
	public ActiveUserException() {
		super();
	}
	
	public ActiveUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ActiveUserException(String message) {
		super();
	}
	
	public ActiveUserException(Throwable cause) {
		super();
	}
}
