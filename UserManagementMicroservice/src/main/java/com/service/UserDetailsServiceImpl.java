package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Login;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = userRepository.findByUsername(username);
		if (login == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
		return UserDetailsImpl.getUser(login);
	}

	public Login addUser(Login login) {
		return userRepository.save(login);
	}

	public boolean checkUsername(String username) {
		// TODO Auto-generated method stub
		Optional<Login> olduser = userRepository.findById(username);
		if (olduser.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
}
