package run;

import java.util.List;

import model.Bedrijf;
import model.Project;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class TestClass {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//refactor
		
		Transaction tx = session.beginTransaction();
		Bedrijf bedrijf = new Bedrijf("oracle");
        session.save(bedrijf);
        session.save(new Project("Java",bedrijf));
        session.save(new Project("SQL",bedrijf));
        
        Bedrijf bedrijf2 = new Bedrijf("IBM");
        session.save(bedrijf2);
        session.save(new Project("Sun",bedrijf2));
        session.save(new Project("Web",bedrijf2));
      
        
        tx.commit();
 
        Query q = session.createQuery("From Project ");
                 
        List<Project> projectLijst = q.list();
        System.out.println("Aantal projecten: " + projectLijst.size());
        for (Project next : projectLijst) {
            System.out.println( next );
        }

	}

}
