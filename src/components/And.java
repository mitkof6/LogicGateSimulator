package components;

/**
 * And class.
 * 
 * @author Jim Stanev
 *
 */
public class And extends DualPort{

	@Override
	public void compute() {
		out = a&&b;
		
	}

}
