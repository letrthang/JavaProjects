package test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Animal;
import model.Bird;
import model.Butterfly;
import model.Cat;
import model.Caterpillar;
import model.Chicken;
import model.Clownfish;
import model.Dog;
import model.Dolphin;
import model.Duck;
import model.Fish;
import model.Parrot;
import model.Rooster;
import model.Shark;

public class AnimalTest {

	int canFly = 0;
	int canWalk = 0;
	int canSing = 0;
	int canSay = 0;
	int canSwim = 0;
	Animal[] animals;

	@Before
	public void beforeEachTest() {

	}

	@Test
	public void test1() {
		animals = new Animal[] { new Bird(), new Duck(), new Chicken(), new Rooster(), new Parrot(null), new Fish(),
				new Shark(), new Clownfish(), new Dolphin(), new Dog(), new Butterfly<Caterpillar>(null), new Cat() };

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

		Assert.assertEquals(3, canFly);
		Assert.assertEquals(7, canWalk);
		Assert.assertEquals(2, canSing);
		Assert.assertEquals(5, canSay);
		Assert.assertEquals(5, canSwim);
	}
}
