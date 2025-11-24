package com.shadow.OptiBankBuddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shadow.OptiBankBuddy.entity.Transaction;
@Repository
public interface TransactionDao extends JpaRepository<Transaction, String> {

}
