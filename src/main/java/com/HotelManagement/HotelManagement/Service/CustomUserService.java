package com.HotelManagement.HotelManagement.Service;


import com.HotelManagement.HotelManagement.Entity.MyUserDetail;
import com.HotelManagement.HotelManagement.Entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.HotelManagement.HotelManagement.Repository.Repository;

@Service
public class CustomUserService implements UserDetailsService {
	
 private final Repository repo;


 @Autowired
 public CustomUserService(Repository repo) {
	 this.repo=repo;
 }

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found in database"));

		Hibernate.initialize(user.getRole());
return new MyUserDetail(user);

	}


}
