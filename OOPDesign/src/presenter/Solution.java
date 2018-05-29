/**
 * 
 */
package presenter;

import model.*;
import model.Bird;
import model.Butterfly;
import model.Caterpillar;

/**
 * @author Thang Le
 *
 */
public class Solution {
	public static void main(String[] args) {
		int canFly = 0;
		int canWalk = 0;
		int canSing = 0;
		int canSay = 0;
		int canSwim = 0;
		Bird bird = new Bird();
		bird.walk();
		bird.fly();
		bird.sing();

		Butterfly<Caterpillar> but = new Butterfly<>(null);
		but.fly();

		Animal[] animals = new Animal[] { new Bird(), new Duck(), new Chicken(), new Rooster(), new Parrot(null),
				new Fish(), new Shark(), new Clownfish(), new Dolphin(), new Dog(), new Butterfly<Caterpillar>(null),
				new Cat() };
		//
		for (Animal animal : animals) {
			if (animal.isFly() == true) {
				canFly++;
			}
			if (animal.isWalk() == true) {
				canWalk++;
			}
			if (animal.isSing() == true) {
				canSing++;
			}
			if (animal.isSay() == true) {
				canSay++;
			}
			if (animal.isiSwim() == true) {
				canSwim++;
			}
		}

		//
		System.out.println("can Fly: " + canFly);
		System.out.println("can Walk: " + canWalk);
		System.out.println("can Sing: " + canSing);
		System.out.println("can Say: " + canSay);
		System.out.println("can Swim: " + canSwim);
	}

}
