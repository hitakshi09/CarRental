package com.carrental.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.carrental.entities.User;
import com.carrental.repositories.UserRepository;
import com.carrental.utils.UserDetail;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.getUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User" + username + "was not found");
		}

		System.out.println("Found User:" + user);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (user.getRole() != null) {
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase());
			grantList.add(authority);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserDetails userDetails = (UserDetails) new UserDetail(username, encoder.encode(user.getPassword()), grantList,
				user.getId());
		System.out.println(userDetails.toString());
		return userDetails;
	}

}
