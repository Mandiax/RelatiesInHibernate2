package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Persoon;

import javax.persistence.*;


@Entity
@Table(name="project")
public class Project implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1250060866739847354L;

	private Long id;
	private String naam;
	private Bedrijf bedrijf;
	
	private List<Persoon> projectmedewerkers = new ArrayList<Persoon>();
	
	public Project(){	
	}
	
	public Project(String naam, Bedrijf bedrijf){
		this.naam = naam;
		this.bedrijf = bedrijf;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "PROJECT_ID", unique = true, nullable = false)
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	@Column(name= "ProjectNaam")
	public String getNaam(){
		return naam;
	}
	
	public void setNaam(String naam){
		this.naam = naam;
	}
	
	@ManyToOne
	//@JoinTable(name="bedrijfsproject", joinColumns ={@JoinColumn(name = "PROJECT_ID")}, inverseJoinColumns = {@JoinColumn(name = "BEDRIJF_ID")})
	@JoinColumn(name="bedrijf_fk", insertable=false, updatable=false)
	public Bedrijf getBedrijf(){
		return bedrijf;
	}
	
	public void setBedrijf(Bedrijf bedrijf){
		this.bedrijf = bedrijf;
	}
	
	
	@ManyToMany(targetEntity=Persoon.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="PROJECT_PERSOON", joinColumns={@JoinColumn(name="PROJECT_ID")},
        inverseJoinColumns={@JoinColumn(name="PERSOON_ID")}
    )
	public List<Persoon> getProjectmedewerkers(){
		return projectmedewerkers;
	}
	
	public void setProjectmedewerkers(List<Persoon> projectmedewerkers){
		this.projectmedewerkers = projectmedewerkers;
	}
	
	public String printProjectmedewerkers(){
		String namen = "";
		for(Persoon p:projectmedewerkers){
			namen = namen +" " +p.getNaam();
		}
		
		//for(int i=0;0<projectmedewerkers.size();i++){
		//	namen = namen + " " +projectmedewerkers.get(i).getNaam();
		//}
		return namen;
	}
	
	@Override
	public String toString(){
		return "Project [id=" +id +", naam=" +getNaam() +", bedrijf=" +bedrijf.getNaam() +", bedrijfs id: " +bedrijf.getId() +"]";
	}
	
}
