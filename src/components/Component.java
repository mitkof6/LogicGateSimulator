package components;

/**
 * This class is the abstract class for all components. It shares some common properties.
 * 
 * @author Jim Stanev
 *
 */
public abstract class Component {

	protected boolean out;
	protected boolean a;
	
	/**
	 * Empty constructor.
	 */
	public Component(){
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param a the state of pin a
	 */
	public Component(boolean a){
		this.a = a;
	}
	
	/**
	 * The A pin a setter.
	 * 
	 * @param a the state of pin A
	 */
	public void setA(boolean a){
		this.a = a;
	}
	
	/**
	 * The Out pin getter.
	 * 
	 * @return the state of pin out
	 */
	public  boolean getOut(){
		return this.out;
	}
	
	/**
	 * Abstract method for components action.
	 */
	public abstract void compute();

}
