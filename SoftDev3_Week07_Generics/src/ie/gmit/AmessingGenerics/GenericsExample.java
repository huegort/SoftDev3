package ie.gmit.AmessingGenerics;

public class GenericsExample <T>  {
	public int countToStringChars(T testOBJ){
		System.out.println("in count with "+testOBJ.getClass().getName());
		String testString = testOBJ.toString();
		return testString.length();
	}

}
