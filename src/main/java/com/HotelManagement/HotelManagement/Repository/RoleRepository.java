package com.HotelManagement.HotelManagement.Repository;

import com.HotelManagement.HotelManagement.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
 public interface RoleRepository extends JpaRepository<Role, Long> {


 Role findByRole(String role);
}