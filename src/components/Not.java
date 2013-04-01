package components;

/**
 * Not class.
 * 
 * @author Jim Stanev
 *
 */
public class Not extends SinglePort{

	@Override
	public void compute() {
		out = !a;
		
	}

}
