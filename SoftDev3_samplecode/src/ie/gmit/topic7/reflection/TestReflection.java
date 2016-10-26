package ie.gmit.topic7.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import ie.gmit.domain.Book;
import ie.gmit.domain.Entity;

public class TestReflection {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Book b  = new Book();
		Entity entity = new Book(1,"sgfs","zsdf",new Date());
		Class tClass = Book.class;
		
		System.out.println("books name :"+Book.class.getName());
		System.out.println("books short name :"+Book.class.getSimpleName());
		System.out.println("bs class name :"+b.getClass().getName());
		System.out.println("the table name I would use is: "+tClass.getSimpleName().toLowerCase());
		
		// fields 
		int num = tClass.getDeclaredFields().length;
		
		String[] columnNames = new String[num];
		int count= 0;
		for (Field field : tClass.getDeclaredFields()){
			System.out.println("field : "+field.getName() + " type: "+field.getType().getName());
			columnNames[count] = field.getName().toLowerCase();
			count++;
		}
		System.out.println(columnNames);
		for (String col: columnNames){
			System.out.print(col +" ");
		}
		
		for (Field field : tClass.getDeclaredFields()){
			String fieldName= field.getName();
			String capfieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Class fieldType = field.getType();
			try {
				Method getMethod = entity.getClass().getMethod("get"+capfieldName);
				if (fieldType.equals(String.class)){
					System.out.println("is String will call "+getMethod.getName());
					System.out.println("value "+ getMethod.invoke(entity));
				}
				if (fieldType.equals(Date.class)){
					System.out.println("is Date will call "+getMethod.getName());
				}
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
		}
		

	}

}
