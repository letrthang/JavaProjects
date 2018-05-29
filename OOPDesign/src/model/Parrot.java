/**
 * 
 */
package model;

/**
 * @author Thang Le
 *
 */
public class Parrot extends Bird {

	private Animal houseMate;

	public Parrot(Animal houseMate) {
		this.houseMate = houseMate;
	}

	@Override
	public void sing() {
		if (houseMate instanceof Dog) {
			System.out.println("Woof, woof");
		} else if (houseMate instanceof Cat) {
			System.out.println("Meow");
		} else if (houseMate instanceof Rooster) {
			System.out.println("Cock-a-doodle-doo");
		}
	}
}
