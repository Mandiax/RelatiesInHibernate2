package run;

import java.util.ArrayList;
import java.util.List;

import model.Bedrijf;
import model.Persoon;
import model.Project;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class TestClass2 {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		//refactor
		
		Transaction tx = session.beginTransaction();
		
		Bedrijf bedrijf = new Bedrijf("oracle");
        Project p1 =  new Project("Java",bedrijf);
        Persoon ps1 = new Persoon("Peter");
        Persoon ps2 = new Persoon("Koen");
        Persoon ps3 = new Persoon("Jelle");
        session.save(ps2);
        session.save(ps3);
        session.save(bedrijf);
        
        List<Persoon> plijst1 = new ArrayList<Persoon>();
        plijst1.add(ps1);
        plijst1.add(ps2);
        //session.save(plijst1);
        session.save(ps1);
        
        p1.setProjectmedewerkers(plijst1);
        session.save(p1);
        
        Project p2 =  new Project("SQL",bedrijf);
        
        List<Persoon> plijst2 = new ArrayList<Persoon>();
        plijst2.add(ps3);
        plijst2.add(ps2);
        //session.save(plijst2);
        p2.setProjectmedewerkers(plijst2);
        
        session.save(p2);
        
        
        Bedrijf bedrijf2 = new Bedrijf("IBM");
        session.save(bedrijf2);
        session.save(new Project("Interne Servers",bedrijf2));
        session.save(new Project("Web Servers",bedrijf2));
        session.save(new Project("Kantine",bedrijf2));
        
      
        tx.commit();
    	 
        Query q = session.createQuery("From Project ");
                 
        List<Project> projectLijst = q.list();
        System.out.println("Aantal projecten: " + projectLijst.size());
       // while(projectLijst.hasNext()){
        //	System.out.println("Volgende project: " +projectLijst.next());
       // }
        
        for (Project next : projectLijst) {
            System.out.println("Project: " + next.getNaam() +", van bedrijf: " + next.getBedrijf().getNaam() +", medewerkers: " +next.printProjectmedewerkers());
        }

	}

}
