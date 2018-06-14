package org.tungnt.springcms.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleSummaryForm {
	
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("postTitle")
	private String articleTitle;
	
	@JsonProperty("postSubTitle")
	private String articleSubTitle;
	
	@JsonProperty("createdDate")
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private Date createdDate;
}
