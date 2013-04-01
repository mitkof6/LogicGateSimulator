package exceptions;

/**
 * This class is responsible for the simulation exceptions.
 * 
 * @author Jim Stanev
 *
 */
public class SimulationException extends CustomException{

	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message the message of the exception
	 */
	public SimulationException(String message) {
		super(message);
		
	}

}
