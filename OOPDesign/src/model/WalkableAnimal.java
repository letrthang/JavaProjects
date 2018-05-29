package model;

public abstract class WalkableAnimal extends Animal {

	public WalkableAnimal() {
		setWalk(true);
	}

	void walk() {
		System.out.println("I am walking");
	}

}
