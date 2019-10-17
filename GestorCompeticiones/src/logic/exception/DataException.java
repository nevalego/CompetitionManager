package logic.exception;

public class DataException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataException() {
		
	}

 	public  DataException(String message) {
 		super(message);
 	}

 	public DataException(String message, Throwable cause) {
 		super(message,cause);
 	}


}
