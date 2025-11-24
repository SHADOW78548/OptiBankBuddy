package com.shadow.OptiBankBuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shadow.OptiBankBuddy.entity.UserInfo;
@Repository
public interface UserDao extends JpaRepository<UserInfo, Long> {
	Boolean existsByEmail(String email);
	
	Boolean existsByAccountNumber(String accountNumber);
	
	UserInfo findByAccountNumber(String accountNumber);
	UserInfo findUserByEmail(String email);
	

}
