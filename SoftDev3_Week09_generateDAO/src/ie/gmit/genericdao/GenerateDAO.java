package ie.gmit.genericdao;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;

import ie.gmit.book.ColumnName;

public class GenerateDAO {
	public static String idCloumnName = "id";


	public static void generateDBTable(Class targetClass){
		String sql = generateSQLString(targetClass);
		GenericDAO.executeUpdate(sql);
	}
	public static void generateDAO(Class targetClass){
		StringBuilder sb = new StringBuilder();
		String className = targetClass.getSimpleName();
		String tableName = className.toLowerCase();
		sb.append("import java.sql.Date;\n");
		sb.append("import java.sql.PreparedStatement;\n");
		sb.append("import java.sql.ResultSet;\n");
		sb.append("import java.sql.SQLException;\n");

		sb.append("public class "+className+"DAO extends GenericDAO<"+className+">{\n");

		sb.append("\t@Override\n");
		sb.append("\tpublic String getTableName(){\n");
		sb.append("\t\treturn \""+tableName+"\";\n");
		sb.append("\t}\n");

		// column names no id
		sb.append("\t@Override\n");
		sb.append("\tString getColumnNamesNoId() {\n");
		sb.append("\t\treturn\"");
		boolean first= true;
	
		for (Field field : targetClass.getDeclaredFields()){
			String colName = field.getName().toLowerCase();
			
			if(field.getAnnotation(ColumnName.class)!=null){
				System.out.println("found");
				colName = field.getAnnotation(ColumnName.class).name();
			}
			
			
			if (field.getAnnotation(PrimaryKey.class) == null){
			//if (!idCloumnName.equals(colName)){
				if (!first){
					sb.append(", ");
				}
				first = false;
				
				sb.append(colName);
			}
		}
		sb.append("\";\n");
		sb.append("\t}\n");


		sb.append("}");
		System.out.println(sb.toString());
	}
	public static void generateAll(Class targetClass){
		generateDBTable(targetClass);
		generateDAO(targetClass);
	}
	protected static String generateSQLString(Class targetClass){
		String tableName = targetClass.getSimpleName().toLowerCase();
		StringBuilder sqlBuilder =  new StringBuilder("create table "+tableName+" (");
		boolean first= true;
		for (Field field : targetClass.getDeclaredFields()){
			if (!first){
				sqlBuilder.append(" ,");
			}
			first = false;
			String colName = field.getName().toLowerCase();
			sqlBuilder.append(colName);
			Type type =field.getType();
			if (type == long.class){
				sqlBuilder.append(" BIGINT(20)");
			}else{
				if (type == String.class){
					sqlBuilder.append(" VARCHAR(32)");
				}else{
					if (type == int.class){
						sqlBuilder.append(" INT()");
					}else{
						if (type == Date.class){
							sqlBuilder.append(" DATE");
						}
					}
				}
			}
		}
		sqlBuilder.append(");");
		return sqlBuilder.toString();

	}


}
