package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import app.entity.CustomUser;
import app.repository.CustomUserRepository;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired(required = true)
	private CustomUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUser user = userRepository.findByUsername(username);
		return user;
	}

}
