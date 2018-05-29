package model;

public class Butterfly<T extends Caterpillar> extends FlyAnimal {
	private T origin;

	/**
	 * Butterfly must be created from Caterpillar
	 * 
	 * @param t
	 */
	public Butterfly(T t) {
		origin = t;
	}
}
