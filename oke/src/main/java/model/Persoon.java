package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.Project;


@Entity
@Table (name="persoon")
public class Persoon implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3091538678049686107L;

	private Long id;
	private String naam;
	private ArrayList<Project> projecten;
	//@OneToOne
	//private Bedrijf bedrijf;
	
	public Persoon(){
		
	}
	
	public Persoon(String naam){
		this.naam = naam;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "PERSOON_ID", unique = true, nullable = false)
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public void setNaam(String naam){
		this.naam = naam;
	}
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="projectmedewerkers", targetEntity=Project.class)
	public List<Project> getProjecten(){
		return projecten;
	}
	
	public void setProjecten(ArrayList<Project> projecten){
		this.projecten = projecten;
	}

}
