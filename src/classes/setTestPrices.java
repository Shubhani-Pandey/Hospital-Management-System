package classes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tables.model.Test_Prices;

public class setTestPrices {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction t = ss.beginTransaction();
		
		Test_Prices obj= (Test_Prices)context.getBean("tests");
		
		ss.save(obj);
		t.commit();
		/*
		 * ss.save(new Test_Prices("Bone Scan",5920)); t.commit(); ss.save(new
		 * Test_Prices("Blood Sugar Test",98)); t.commit(); ss.save(new
		 * Test_Prices("CT Scan",4630)); t.commit(); ss.save(new
		 * Test_Prices("DNA Test",17796)); t.commit(); ss.save(new
		 * Test_Prices("ECG",254)); t.commit(); ss.save(new
		 * Test_Prices("Glucose Tolerance Test",378)); t.commit(); ss.save(new
		 * Test_Prices("Insulin Test",810)); t.commit();
		 */
		
		System.out.println("Inserting done");
		
		ss.close();
		sf.close();
		

	}

}
