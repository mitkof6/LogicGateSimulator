package components;

/**
 * Xnor class.
 * 
 * @author Jim Stanev
 *
 */
public class Xnor extends DualPort{

	@Override
	public void compute() {
		out = !(((!a)&&b)||(a&&(!b)));
		
	}
}
