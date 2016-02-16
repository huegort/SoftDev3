package ie.gmit.reflectionExample.A;

public class Mammal {
	public void whoAreYou(){
		System.out.println("type name:"+this.getClass().getTypeName());
		System.out.println("name:"+this.getClass().getName());
		System.out.println("simple name:"+this.getClass().getSimpleName());
		System.out.println("canocal name:"+this.getClass().getCanonicalName());
		
		}
}
