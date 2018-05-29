package model;

public class Chicken extends WalkableAnimal {
	public Chicken() {
		super();
		setSay(true);
	}

	public void say() {
		System.out.println("chicken say Cluck, cluck");
	}

}
