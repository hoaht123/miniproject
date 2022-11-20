package com.example.miniproject.repository;

import com.example.miniproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository("Account")
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account findUserName(@PathParam("username") String username);
}