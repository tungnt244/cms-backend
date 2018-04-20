package org.tungnt.springcms.form;

import java.util.List;

import org.tungnt.springcms.entity.AuthorEnt;
import org.tungnt.springcms.entity.CommentEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ArticalForm {
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;
	
	public ArticalForm() {}
	
	public ArticalForm(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
