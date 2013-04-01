package exceptions;

/**
 * This class is responsible for the window dialog exception.
 * 
 * @author Jim Stanev
 *
 */
public class WindowDialogException extends CustomException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * 
	 * @param message the message of the exception
	 */
	public WindowDialogException(String message){
		super(message);
	}
}
