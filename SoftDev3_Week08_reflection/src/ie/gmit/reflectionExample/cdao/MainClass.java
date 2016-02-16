package ie.gmit.reflectionExample.cdao;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ie.gmit.book.Book;

public class MainClass {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		String myMethodString = "setLong";
		Method myMethod = PreparedStatement.class.getMethod(myMethodString, int.class,long.class );
		
		System.out.println(myMethod.getName());
		
		
		String myMethodString2 = "getLong";
		Method myMethod2 = ResultSet.class.getMethod(myMethodString2, String.class );
		
		System.out.println(myMethod2.getName());

	}

}
