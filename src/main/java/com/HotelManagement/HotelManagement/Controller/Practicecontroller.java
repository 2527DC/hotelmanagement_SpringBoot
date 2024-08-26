package com.HotelManagement.HotelManagement.Controller;

import com.HotelManagement.HotelManagement.Entity.CustomerDetails;
import com.HotelManagement.HotelManagement.Entity.Rooms;
import com.HotelManagement.HotelManagement.Service.AdminControleService;
import com.HotelManagement.HotelManagement.Service.UsercontroleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pra")
public class Practicecontroller {




    private final AdminControleService Service;

    @Autowired
    private UsercontroleService service;

    @Autowired
    public Practicecontroller(AdminControleService service) {
        Service = service;
    }

    @PostMapping("/booking")
    public ResponseEntity<String> booking(@RequestBody CustomerDetails customerDetails ,
                                          @RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout , @RequestParam("roomNO") int roomNO     ){

        CustomerDetails s=service.booking(customerDetails,checkin,checkout,roomNO);


        return s!=null?ResponseEntity.ok("Booking Successfully"):ResponseEntity.badRequest().body("Booking Failed");
    }
   
    @GetMapping("/checkRoom")
    public ResponseEntity<String > checkRoom(@RequestParam String date,@RequestParam int roomNo){
   return service.checkRoom(roomNo,date)?ResponseEntity.ok("booking is avilable")
           :ResponseEntity.ok("booking is not avilable");
    }



    @PostMapping(value = "/addroom", consumes = "multipart/form-data")
    public ResponseEntity<String> addRoom(
            @RequestPart String roomJson,
            @RequestPart("file") MultipartFile file) {

        // manually making the string type JSON to Rooms  object
        Rooms room;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            room = objectMapper.readValue(roomJson, Rooms.class);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid room data: " + e.getMessage());
        }


        try {
            Rooms savedRoom = Service.addRole(room, file);
            return savedRoom != null ? ResponseEntity.ok("ROOM ADDED SUCCESSFULLY") :
                    ResponseEntity.badRequest().body("FAILED TO ADD THE ROOM ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/getImage/{roomNo}")
    public ResponseEntity<Resource> getImage(@PathVariable("roomNo") int roomNo ){

        return  Service.getImage(roomNo);
    }
    @GetMapping("/message")
   public ResponseEntity<String> message(){

        return Service.message() ?ResponseEntity.ok(" get away fucker"):ResponseEntity.ok("oomg");
    }

    }
   