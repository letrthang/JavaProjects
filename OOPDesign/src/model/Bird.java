/**
 * 
 */
package model;

/**
 * @author Thang Le
 *
 */
public class Bird extends FlyAnimal {

	public Bird() {
		setSing(true);
		setWalk(true);
	}

	public void sing() {
		System.out.println("I am singing");
	}

	public void walk() {
		System.out.println("I am walking");

	}
}
