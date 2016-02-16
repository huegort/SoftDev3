package ie.gmit.reflectionExample.bbeans;

import java.util.Date;
import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ie.gmit.book.Book;

public class MainClass {

	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Book book = new Book(12,"zxc","zxc",2323,new Date(),"comedy"); 
		PropertyDescriptor pd1 =  new PropertyDescriptor("author", Book.class);
		
		Method getAuthorMethod = pd1.getWriteMethod();
		getAuthorMethod.invoke(book, "new author");
		
		System.out.println(book.getAuthor());

		PropertyDescriptor pd2 =  new PropertyDescriptor("id", Book.class);
		System.out.println(pd2.getPropertyType().getName());
		
		pd2.getWriteMethod().invoke(book, 123);
		
		System.out.println(book.getId());

		// Reslut sets
		
		
		
		
	}

}
