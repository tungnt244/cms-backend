package org.tungnt.springcms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="authors")
public class AuthorEnt {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL)
	private List<ArticalEnt> listArticals = new ArrayList<>(); 
	
	public AuthorEnt() {}
	
	public AuthorEnt(String name, Date dateOfBirth) {
		this.name=name;
		this.dateOfBirth=dateOfBirth;
	}
	
	public boolean addArtical(ArticalEnt artical) {
		artical.setAuthor(this);
		return getListArticals().add(artical);
	}
	
	@Override
	public String toString() {
		return String.format("author: id= %d, name= %s, birth= %tA", this.id, this.name, this.dateOfBirth);
	}
}
