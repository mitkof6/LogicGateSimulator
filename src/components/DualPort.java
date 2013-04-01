package components;

/**
 * This class is the abstract class for all dual port components.
 * 
 * @author Jim Stanev
 *
 */
public abstract class DualPort extends Component{

	protected boolean b;
	protected int indexA,indexB;
	
	/**
	 * Empty constructor.
	 */
	public DualPort(){
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param a the state of pin A
	 * @param b the state of pin B
	 */
	public DualPort(boolean a, boolean b){
		super(a);
		this.b = b;
	}
	
	/**
	 * The index A setter.
	 * @param a the row of pin A
	 */
	public void setIndexA(int a){
		this.indexA = a;
	}
	
	/**
	 * The index A getter.
	 * 
	 * @return the row of pin A
	 */
	public int getIndexA(){
		return this.indexA;
	}
	
	/**
	 * The b pin state setter.
	 * 
	 * @param b the state of pin B
	 */
	public void setB(boolean b){
		this.b = b;
	}
	
	/**
	 * The index B setter.
	 * 
	 * @param b the row of index B
	 */
	public void setIndexB(int b){
		this.indexB = b;
	}
	
	/**
	 * the index B getter.
	 * 
	 * @return the row of pin B
	 */
	public int getIndexB(){
		return this.indexB;
	}
	
	

}
