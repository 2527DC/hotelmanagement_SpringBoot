package com.HotelManagement.HotelManagement.Controller;

import com.HotelManagement.HotelManagement.Repository.Repository;
import com.HotelManagement.HotelManagement.Service.AdminControleService;
import com.HotelManagement.HotelManagement.Service.UsercontroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.HotelManagement.HotelManagement.Entity.User;
import com.HotelManagement.HotelManagement.Service.LoginRegisterService;

@RestController
@RequestMapping("/api")
public class PublicController {

    private final AdminControleService Service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Repository repo;

    @Autowired
    private LoginRegisterService service;
    @Autowired
    private UsercontroleService Userservice;


    @Autowired
    public PublicController(AdminControleService service) {
        Service = service;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> resisterUser( @RequestBody User user) {


        User u=  	service.registerUser(user);
        if (u != null) {
            return ResponseEntity.ok(" User  Registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" User Registration failed");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String >  login(@RequestBody User     user){
        Authentication authentication =authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUsername(),user.getPassword())) ;
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok("login sucessfull");

        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(" login faild");

        }

    }

    @GetMapping("/message")
    public String message() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + authentication.getAuthorities());
        return "I am user";
    }

    @GetMapping("/Admimessage")
    public String Adminmessage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + authentication.getAuthorities());
        return "I am admin";
    }

    @PostMapping("/add/{username}/{role}")
    public ResponseEntity<String> addrole(@PathVariable String username ,@PathVariable String role) {
        User u=  service.addRole(username,role);
        if (u != null) {
            return ResponseEntity.ok(" roleadded successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" role  failed");
        }
    }

    @GetMapping("/getImage/{roomNo}")
    public ResponseEntity<Resource> getImage(@PathVariable("roomNo") int roomNo ){

        return  Service.getImage(roomNo);
    }

    @GetMapping("/checkRoom")
    public ResponseEntity<String > checkRoom(@RequestParam String date,@RequestParam int roomNo){
        return Userservice.checkRoom(roomNo,date)?ResponseEntity.ok("booking is avilable")
                :ResponseEntity.ok("booking is not avilable");
    }

}
