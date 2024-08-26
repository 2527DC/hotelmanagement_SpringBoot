package com.HotelManagement.HotelManagement.Controller;


import com.HotelManagement.HotelManagement.Entity.CustomerDetails;
import com.HotelManagement.HotelManagement.Entity.Rooms;
import com.HotelManagement.HotelManagement.Entity.User;
import com.HotelManagement.HotelManagement.Service.UsercontroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UsercontroleService  service;


    @PostMapping("/update/{username}")
    public ResponseEntity<String>  update( @PathVariable String username , @RequestBody User user){

        User found= service.update(username ,user);
        if (found!=null){

            return  ResponseEntity.ok("Updated Successfully");
        }
        else{
            return ResponseEntity.badRequest().body("Updation  failed ");
        }
    }
    @GetMapping("/getroles/{username}")

    public String getUserRoles(@PathVariable String username){
        return service.getUserRole(username);
    }

    @GetMapping("/getRoom")
    public List<Rooms> getRooms(){
        return  service.getRooms();
    }

    @PostMapping("/booking")
    public ResponseEntity<String> booking(@RequestBody CustomerDetails customerDetails ,
                 @RequestPart String checkin,@RequestParam String checkout ,@RequestParam int roomNO     ){


         CustomerDetails s=service.booking(customerDetails,checkin,checkout,roomNO);


           return s!=null?ResponseEntity.ok("Booking Successfully"):ResponseEntity.badRequest().body("Booking Failed");
    }



}
