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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "articles")
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEnt {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "sub_title")
	private String subTitle;

	@Column(name = "content")
	private String content;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	@Default
	private List<CommentEntity> commentEntitys = new ArrayList<>();

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@Default
	private Date createdDate = new Date(System.currentTimeMillis());

	@Column(name = "is_deleted")
	@Default
	private Boolean isDeleted=false;

	public boolean addComment(CommentEntity comment) {
		comment.setArticle(this);
		return getCommentEntitys().add(comment);
	}

	@Override
	public String toString() {
		return String.format("article : id= %d, title= %s, subtitle= %s, content= %s", this.id, this.title,
				this.subTitle, this.content);
	}
}
