package org.tungnt.springcms.config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tungnt.springcms.entity.UserEntity;
import org.tungnt.springcms.util.Constants;
import org.tungnt.springcms.util.JwtHandler;

import io.jsonwebtoken.SignatureException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// check if jwt is valid or not
		String header = request.getHeader(Constants.HEADER_STRING);

		if (header == null) {
			filterChain.doFilter(request, response);
			return;
		}

		else if (!header.startsWith(Constants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}

		// get user
		else {

			String token = header.substring(Constants.TOKEN_PREFIX.length());

			try {

				Map<String, String> decodedJWT = JwtHandler.verifyToken(token);

				String username = decodedJWT.get("username");

				String role = decodedJWT.get("role");

				// Optional<Long> newExpirationTimeDuration = Optional.empty();
				//
				// long currentTimeMillis = System.currentTimeMillis();
				//
				// Date expirationTime = decodedJWT.getExpiresAt();
				//
				// long expirationDuration = expirationTime.getTime() - currentTimeMillis;
				//
				// System.out.println("expiration time: " +String.valueOf(expirationTime));
				// System.out.println("expiration duration: "+
				// String.valueOf(expirationDuration));
				if (role != null) {

					// List<GrantedAuthority> authorities = new ArrayList<>();

					// authorities.add(new SimpleGrantedAuthority(Constants.ROLE_PREFIX+role));

					UserDetails userDetails = UserEntity.builder().username(username).role(role).build();

					// if (role.equals(Constants.ADMIN)) {
					// userDetails = new Agent(agentId);
					// if(expirationDuration < Constants.ADMIN_REFRESH_TOKEN_TIME) {
					// newExpirationTimeDuration = Optional.of(Constants.ADMIN_EXPIRATION_TIME);
					// }
					// } else {
					// userDetails = new User();
					// if(expirationDuration < Constants.USER_REFRESH_TOKEN_TIME) {
					// newExpirationTimeDuration = Optional.of(Constants.USER_EXPIRATION_TIME);
					// }
					// }

					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());

					// authentication.setDetails(new WebAuthenticationDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);

					System.out.println("authentication: " + authentication);
				} else {
					filterChain.doFilter(request, response);
					return;
				}

				// if ( newExpirationTimeDuration.isPresent()) {
				// String newToken = JwtHandler.createToken(agentId, role);
				// response.setHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX+newToken);
				// }

			} catch (SignatureException e) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		filterChain.doFilter(request, response);

	}

}
