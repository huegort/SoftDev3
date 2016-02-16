package ie.gmit.book;

import org.hibernate.Session;

import ie.gmit.hibernate.HibernateUtil;

public class TestHibernate {

	public static void main(String[] args) {
		Session session = HibernateUtil.getNewSession();

	}

}
