package com.portfolio.admin.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDetails extends User {

	private static final long serialVersionUID = 1L;

	public AccountDetails(String email, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	private String aboutMeId;

}
