package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="bedrijf")
public class Bedrijf implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4260016986625987112L;
	private Long id;
	private String naam;
	
	private List<Project> projecten = new ArrayList<Project>();
	
	//personeelsbestand opvraagbaar maken voor bedrijf of via projecten? Ook methodes los maken!
	//@OneToMany (mappedBy=bedrijf,cascade=CascadeType.PERSIST)
	//private ArrayList<Persoon> personeel = new ArrayList<Persoon>();
	
	public Bedrijf(){
		
	}
	
	public Bedrijf(String naam){
		this.naam = naam;
	}
	
	@Id
	@GeneratedValue
	@Column(name="BEDRIJF_ID", unique = true, nullable = false)
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	@Column(name= "Naam")
	public String getNaam(){
		return naam;
	}
	
	public void setNaam(String naam){
		this.naam = naam;
	}
	
	
	@OneToMany
	//@JoinTable(name="BedrijfsProjecten", joinColumns = {@JoinColumn(name="BEDRIJF_ID")}, inverseJoinColumns = @JoinColumn(name="PROJECT_ID"))
	@JoinColumn(name="bedrijf_fk")
	public List<Project> getProjecten(){
		return projecten;
	}
	
	public void setProjecten(List<Project> projecten){
		this.projecten = projecten;
	}
	
	//public ArrayList<Persoon> getPersoneel(){
	//	return personeel;
	//}
	
	//public void setPersoneel(ArrayList<Persoon> personeel){
	//	this.personeel = personeel;
	//}

}
