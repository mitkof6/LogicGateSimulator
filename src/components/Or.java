package components;

/**
 * Or class.
 * 
 * @author Jim Stanev
 *
 */
public class Or extends DualPort{

	@Override
	public void compute() {
		out = a||b;
		
	}

}
