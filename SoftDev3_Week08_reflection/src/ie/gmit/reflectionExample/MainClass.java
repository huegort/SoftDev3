package ie.gmit.reflectionExample;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

import ie.gmit.book.Book;

public class MainClass {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		System.out.println("book has these fields");
		Book book = new Book(12,"sdf","jk who the heck",23,new Date(),"sdf");
		for(Field field  : Book.class.getDeclaredFields())
		{
		    System.out.print("\t"+field.getName());
		    System.out.print("\t"+field.getType().getName());
		    System.out.println("\t"+field.getModifiers());
		    if (field.getName().equals("id")){
		    	System.out.println("\t\tvalue is : "+field.getLong(book));
		    }
			
		}
		String fieldName = "author";
		
		Field outField = book.getClass().getDeclaredField(fieldName);
		
		System.out.println("field "+ outField.getName()+" is "+ outField.get(book).toString());
		
		System.out.println("book has these methods");
		for(Method method  : Book.class.getDeclaredMethods())
		{
		    System.out.println("\t"+method.getName());
		    for (Parameter parameter : method.getParameters()){
		    	System.out.println("\t\t"+parameter.getType().getName());
		    }
		    if (method.getName().equals("setId")){
		    	method.invoke(book, 23);
		    }
		}
		String myMethodString = "setAuthor";
		Method myMethod = Book.class.getMethod(myMethodString, String.class);
		myMethod.invoke(book, "new value");
		
		System.out.println(book.getId());
		System.out.println(book.getAuthor());
		
	}
	
	

}
