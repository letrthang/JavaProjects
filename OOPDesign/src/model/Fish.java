package model;

public class Fish extends Animal {
	public interface Actions {
		// default interface only support from Java 8
		default void eat(Fish food) {
			// no implement here
		}

		default void makeFun() {
			// no implement here
		}
	}

	public Fish() {
		setiSwim(true);
	}

	public static void swim() {
		System.out.println("i am swimming");
	}

}
