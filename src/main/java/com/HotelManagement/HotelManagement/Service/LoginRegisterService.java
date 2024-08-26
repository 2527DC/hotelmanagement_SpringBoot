package com.HotelManagement.HotelManagement.Service;

import com.HotelManagement.HotelManagement.Entity.Role;
import com.HotelManagement.HotelManagement.Entity.User;
import com.HotelManagement.HotelManagement.Repository.Repository;
import com.HotelManagement.HotelManagement.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginRegisterService {

    
    private Role role=new Role();




    @Autowired
    private Repository repo;


    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder pas;

    public User registerUser(User user) {


        try{

            user.setPassword(pas.encode(user.getPassword()));

            Role r=   role;
            r.setRole(user.getRequestRole());
            roleRepo.save(r);
            user.getRole().add(r);

            return repo.save(user);

        }catch(Exception  e ){

            System.out.println(e);
        }

        return user;
    }

    public User addRole(String Username,String requestrole){
        User user = repo.findByUsername(Username).orElseThrow(()-> new UsernameNotFoundException("User  not found "));
        Role r= role;
        r.setRole(requestrole);
        roleRepo.save(r);
        user.getRole().add(r);

        return repo.save(user);
    }

}
