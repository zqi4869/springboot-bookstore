package com.bookstore.service;

import com.bookstore.domain.User;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is used by spring security to authenticate and authorize user
 **/
@Service
public class UserService implements UserDetailsService {
	@Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
        		AuthorityUtils.createAuthorityList(user.getRole()));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
