package model;

public abstract class FlyAnimal extends Animal {

	public FlyAnimal() {
		setFly(true);
	}

	public void fly() {
		System.out.println("I am flying");
	}
}
