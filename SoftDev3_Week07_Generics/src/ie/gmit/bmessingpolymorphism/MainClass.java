package ie.gmit.bmessingpolymorphism;

import java.util.Random;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("in b");
		Mammal m;
		Random r = new Random();
		int rNumber = r.nextInt(2);
		System.out.println(rNumber);
		if (rNumber == 1){
			m = new Dog();
		}else{
			m = new Cat();
		}
		
		
		m.scare();

	}

}
