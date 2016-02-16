package ie.gmit.AmessingGenerics;
import javax.swing.JFrame;

public class MainClass {
	// Basic Example of Generics
	
	public static void main(String[] args) {
		GenericsExample<String> geString = new GenericsExample<String>();
		GenericsExample<JFrame> geJFrame = new GenericsExample<JFrame>();
		
		System.out.println(geString.countToStringChars("1234567"));
		//System.out.println(geJFrame.countToStringChars("1234567"));
		System.out.println(geJFrame.countToStringChars(new JFrame()));

	}

}
