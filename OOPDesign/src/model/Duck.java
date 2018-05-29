package model;

public class Duck extends WalkableAnimal {
	public Duck() {
		super();
		setSay(true);
		setiSwim(true);
	}

	public void say() {
		System.out.println("Duck say Quack, quack");
	}

	public void swim() {
		Fish.swim();
	}

}
