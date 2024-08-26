package com.HotelManagement.HotelManagement.Repository;

import com.HotelManagement.HotelManagement.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerDetails,Integer > {
}
