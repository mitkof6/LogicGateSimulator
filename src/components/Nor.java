package components;

/**
 * Nor class.
 * 
 * @author Jim Stanev
 *
 */
public class Nor extends DualPort{
	
	@Override
	public void compute() {
		out = !(a||b);
		
	}

}
