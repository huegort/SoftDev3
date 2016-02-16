package ie.gmit.bmessingpolymorphism;

public abstract class Mammal {
	abstract void makeNoise();
	public void scare(){
		makeNoise();
		makeNoise();
		makeNoise();
	}
}
