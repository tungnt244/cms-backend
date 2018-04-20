package org.tungnt.springcms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Data
@Table(name="articles")
@NamedQueries({
		@NamedQuery(name="ArticalEnt.getCommentEntitys", 
				query="select distinct a from ArticalEnt a "
						+ "left join fetch a.commentEntitys "
						+ "left join fetch a.author")
})
@JsonInclude(Include.NON_NULL)
public class ArticalEnt {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="author_id", updatable=false)
	private AuthorEnt author;
	
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	private List<CommentEntity> commentEntitys = new ArrayList<>(); 
	
	@Column(name="created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	public boolean addComment(CommentEntity comment) {
		comment.setArticle(this);
		return getCommentEntitys().add(comment);
	}
	@Override
	public String toString() {
		return String.format("artical : id= %d, title= %s, content= %s", this.id, this.title, this.content);
	}
}
