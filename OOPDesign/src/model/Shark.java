package model;

public class Shark extends Fish implements Fish.Actions {
	@Override
	public void eat(Fish food) {
		System.out.println("i eat other fish");
	}
	

}
