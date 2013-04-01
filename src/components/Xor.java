package components;

/**
 * Xor class.
 * 
 * @author Jim Stanev
 *
 */
public class Xor extends DualPort{

	@Override
	public void compute() {
		out = (((!a)&&b)||(a&&(!b)));
		
	}

}
