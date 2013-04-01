package components;

/**
 * Nand class.
 * 
 * @author Jim Stanev
 *
 */
public class Nand extends DualPort{

	@Override
	public void compute() {
		out = !(a&&b);
		
	}

}
