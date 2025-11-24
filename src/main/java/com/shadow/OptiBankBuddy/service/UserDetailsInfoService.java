package com.shadow.OptiBankBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shadow.OptiBankBuddy.dao.UserDao;
import com.shadow.OptiBankBuddy.entity.UserInfo;


@Service
@Primary
public class UserDetailsInfoService implements UserDetailsService{

	@Autowired
	UserDao dao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo user=dao.findUserByEmail(email);
		
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found !!");
		}
		return User.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}

}