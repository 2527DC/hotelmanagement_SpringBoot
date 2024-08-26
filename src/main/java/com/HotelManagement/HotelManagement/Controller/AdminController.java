package com.HotelManagement.HotelManagement.Controller;
import com.HotelManagement.HotelManagement.Entity.Rooms;
import com.HotelManagement.HotelManagement.Service.AdminControleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
