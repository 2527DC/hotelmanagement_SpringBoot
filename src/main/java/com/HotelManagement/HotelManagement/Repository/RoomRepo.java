package com.HotelManagement.HotelManagement.Repository;


import com.HotelManagement.HotelManagement.Entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Rooms,Integer> {

Optional<Rooms>  findByRoomNO(int roomNo);



}
