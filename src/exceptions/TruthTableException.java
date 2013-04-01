package exceptions;

/**
 * This class is responsible for the truth table exceptions.
 * 
 * @author Jim Stanev
 *
 */
public class TruthTableException extends CustomException{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message the message of the exception
	 */
	public TruthTableException(String message) {
		super(message);
	}
}
