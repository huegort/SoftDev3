package ie.gmit.firstgoatdao;

import java.lang.reflect.Method;

public class MethodsForProperty {
	String propertyName;
	int order;
	Method beanWritter;
	Method beanGetter;
	Method resultSetGetter;
	Method preparedStatementSetter;
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Method getBeanWritter() {
		return beanWritter;
	}
	public void setBeanWritter(Method beanWritter) {
		this.beanWritter = beanWritter;
	}
	public Method getBeanGetter() {
		return beanGetter;
	}
	public void setBeanGetter(Method beanGetter) {
		this.beanGetter = beanGetter;
	}
	public Method getResultSetGetter() {
		return resultSetGetter;
	}
	public void setResultSetGetter(Method resultSetGetter) {
		this.resultSetGetter = resultSetGetter;
	}
	public Method getPreparedStatementSetter() {
		return preparedStatementSetter;
	}
	public void setPreparedStatementSetter(Method preparedStatementSetter) {
		this.preparedStatementSetter = preparedStatementSetter;
	}
	@Override
	public String toString() {
		return "MethodsProperties [propertyName=" + propertyName + ", order=" + order + ", beanWritter=" + beanWritter.getName()
				+ ", beanGetter=" + beanGetter.getName() + ", resultSetGetter=" + resultSetGetter.getName() + ", preparedStatementSetter="
				+ preparedStatementSetter.getName() + "]";
	}
	
	
	
	
	

}
