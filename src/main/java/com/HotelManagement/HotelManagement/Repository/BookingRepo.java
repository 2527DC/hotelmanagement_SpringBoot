package com.HotelManagement.HotelManagement.Repository;

import com.HotelManagement.HotelManagement.Entity.Bookindetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Bookindetails,Integer> {


    List<Bookindetails> findByRoomNo(int roomNo);
}
