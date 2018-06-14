package org.tungnt.springcms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="article_id")
	private ArticleEnt article;
	
	@Override
	public String toString() {
		return String.format("comment: id= %d, content= %s", this.id, this.content);
	}
}
