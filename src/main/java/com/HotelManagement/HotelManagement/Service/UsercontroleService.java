package com.HotelManagement.HotelManagement.Service;


import com.HotelManagement.HotelManagement.Entity.Bookindetails;
import com.HotelManagement.HotelManagement.Entity.CustomerDetails;
import com.HotelManagement.HotelManagement.Entity.Rooms;
import com.HotelManagement.HotelManagement.Entity.User;
import com.HotelManagement.HotelManagement.Repository.BookingRepo;
import com.HotelManagement.HotelManagement.Repository.CustomerRepo;
import com.HotelManagement.HotelManagement.Repository.Repository;
import com.HotelManagement.HotelManagement.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsercontroleService {

    @Autowired
    private RoomRepo roomrepo;

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private Repository repo;




    public User update( String  username ,User user ){

        User found= repo.findByUsername(username).orElseThrow();
        found.setPassword(user.getPassword());
        found.setUsername(user.getPassword());
        found.setEmail(user.getEmail());
        return  repo.save(found);
    }

    public String getUserRole(String username){

        User found= repo.findByUsername(username).orElseThrow();
        return  found.getRole().stream().map(s->s.getRolename()).collect(Collectors.joining(","));

    }

    public List<Rooms>  getRooms(){
        return roomrepo.findAll();
    }

    public CustomerDetails booking(CustomerDetails customerDetails, String checkin, String checkout, int roomNo) {
        Bookindetails b= new Bookindetails();
        b.setCheckin(LocalDate.parse(checkin));
        b.setCheckout(LocalDate.parse(checkout));
        b.setRoomNo(roomNo);

        bookingRepo.save(b);
        customerDetails.setBookindetails(b);
        return customerRepo.save(customerDetails);
    }

    public boolean checkRoom( int roomNo,String date) {

        List<Bookindetails> d= bookingRepo.findByRoomNo(roomNo);

        boolean bo = true;
        if (d != null) {
            LocalDate l = LocalDate.parse(date);

            for (Bookindetails b : d) {

                if (!l.isBefore(b.getCheckin()) && !l.isAfter(b.getCheckout())) {
                    return false;
                }
            }
        }
        return true; 
    }
}
