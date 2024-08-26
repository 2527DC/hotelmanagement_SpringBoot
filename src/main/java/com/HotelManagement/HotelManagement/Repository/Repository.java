package com.HotelManagement.HotelManagement.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import com.HotelManagement.HotelManagement.Entity.User;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<User, Long> {

	
	Optional<User> findByUsername(String username);
}


