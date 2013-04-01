package components;

/**
 * This class is the abstract class of all single port components.
 * 
 * @author Jim Stanev
 *
 */
public abstract class SinglePort extends Component{
	
	protected int indexA;
	
	/**
	 * Emtry constructor.
	 */
	public SinglePort(){
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param a the state of pin A
	 */
	public SinglePort(boolean a){
		super(a);
	}
	
	/**
	 * The index A setter.
	 * 
	 * @param i the number of the row
	 */
	public void setIndexA(int i){
		this.indexA = i;
	}
	
	/**
	 * The index A getter.
	 * 
	 * @return the row of index A
	 */
	public int getIndexA(){
		return this.indexA;
	}


}
