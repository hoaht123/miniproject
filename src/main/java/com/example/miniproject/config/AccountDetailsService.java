package com.example.miniproject.config;

import com.example.miniproject.model.Account;
import com.example.miniproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountDetailsService implements UserDetailsService {
    @Autowired
    @Qualifier("Account")
    private AccountRepository repository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findUserName(username);
        if(account == null){
            throw new UsernameNotFoundException("User name not exists !");
        }
        Set<SimpleGrantedAuthority> grand = Collections.singleton(new SimpleGrantedAuthority(account.getRole()));
        return new User(account.getUsername(), account.getPassword(), grand);
    }

    public void registerAccount(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
    }

    public Account getOneAccount(String accountName){
        return repository.findUserName(accountName);
    }

    public List<Account> listAll (){
        return repository.findAll();
    }

}
