package org.tungnt.springcms.util;

public class Constants {
	public static String HEADER_STRING = "Authorization";
	public static String TOKEN_PREFIX = "Bearer";
	public static String SECRET_KEY = "PzE%:_&Qn}sc[dZZ&xyZ,8CG8,=F,V&ew+.-9(z^#qGF>qS[}exCNx]zD8{\\;d2H";
	public static String ISSUER = "tungnt";
	public static String ADMIN = "ADMIN";
	public static String USER = "USER";
	public static String ROLE_PREFIX = "ROLE_";
	public enum ROLE{
		ADMIN, USER
	}
	public static final long USER_EXPIRATION_TIME = 604800000L; // 7 days
	public static final long USER_REFRESH_TOKEN_TIME = 302400000L; // 3.5 days
	public static final long ADMIN_EXPIRATION_TIME = 900000L; // 15 mins
	public static final long ADMIN_REFRESH_TOKEN_TIME = 450000L; // 7.5 mins
}
