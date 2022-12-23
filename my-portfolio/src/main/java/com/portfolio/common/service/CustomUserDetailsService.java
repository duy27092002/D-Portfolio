package com.portfolio.common.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.admin.dto.AccountDetails;
import com.portfolio.admin.entity.AboutMe;
import com.portfolio.admin.repository.AboutMeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AboutMeRepository aboutMeRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("START CHECK LOGIN");
		AboutMe getAboutMe = aboutMeRepo.findByEmailAndStatus(email, 1);

		if (getAboutMe == null) {
			throw new UsernameNotFoundException("Account not found!");
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
		
		AccountDetails account = new AccountDetails(getAboutMe.getName(), getAboutMe.getPassword(), true, true, true,
				true, authorities);
		account.setAboutMeId(getAboutMe.getId());
	
		System.out.println(getAboutMe.getEmail());
		System.out.println(getAboutMe.getPassword());
		System.out.println("END CHECK LOGIN");

		return account;
	}

}
