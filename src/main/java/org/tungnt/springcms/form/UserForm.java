package org.tungnt.springcms.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserForm {

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

}
