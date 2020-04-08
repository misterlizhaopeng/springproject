package x.b.n;

public class Dog implements Animal {

	@Override
	public void eat() {
		System.out.println("dog eat...");
	}

	@Override
	public void sound() {
		System.out.println("dog sound...");
	}

}
