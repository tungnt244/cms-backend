package org.tungnt.springcms.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleForm {
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("title")
	private String title;

	@JsonProperty("subTitle")
	private String subTitle;

	@JsonProperty("content")
	private String content;
	
	@JsonProperty("createdDate")
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private Date createdDate;
}
