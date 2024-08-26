package com.HotelManagement.HotelManagement.Controller;
import com.HotelManagement.HotelManagement.Entity.Rooms;
import com.HotelManagement.HotelManagement.Service.AdminControleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminControleService Service;

    public AdminController(AdminControleService service) {
        Service = service;
    }



    @PostMapping("/{roomNO}/update")
    public ResponseEntity<String > update(@PathVariable("roomNO") int roomNo ,@RequestBody Rooms room){

        Rooms r= Service.update(roomNo ,room);

        return r!=null?ResponseEntity.ok("ROOM UPDATED SUCCESSFULLY"):
                ResponseEntity.badRequest().body("FAILED TO UPDATE THE ROOM ");
    }

    @GetMapping("/detete/{roomNO}")
    public  ResponseEntity<String> delete(@PathVariable("roomNO") int roomNo){

        return  Service.delete(roomNo) ?ResponseEntity.ok("ROOM DELETE SUCCESSFULLY"):
                ResponseEntity.badRequest().body("FAILED TO DELETE THE ROOM ");
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

}
