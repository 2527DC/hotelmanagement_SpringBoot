package com.HotelManagement.HotelManagement.Service;

import com.HotelManagement.HotelManagement.Entity.Rooms;
import com.HotelManagement.HotelManagement.Repository.RoomRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@Service
public class AdminControleService {

    @Autowired
    private final RoomRepo roomRepo;

    public AdminControleService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public Rooms addRole(Rooms room ,MultipartFile file ){


        if (file.isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" NO file selected ");
        }
        try {
            String directoryPath ="F://fullstack-backend//newone//";
            File upladDirectory= new File(directoryPath);
            if (!upladDirectory .exists()) {
                upladDirectory .mkdirs();
            }

            String path = directoryPath +file.getOriginalFilename();

            file.transferTo(new File(path));
            room.setPicture(path);
            roomRepo.save(room);
        }
        catch (Exception e){

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed."+e.getMessage());
        }
        return roomRepo.save(room);
    }



    public  Rooms update(int roomNo ,Rooms r ){
        try {
            Rooms s = roomRepo.findByRoomNO(roomNo).orElseThrow();
            s.setPicture(r.getPicture());
            s.setBed(r.getBed());
            s.setFeatures(r.getFeatures());
            s.setRoomNO(r.getRoomNO());

            roomRepo.save(s);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public boolean delete(int roomNo){
        Rooms s=roomRepo.findByRoomNO(roomNo).orElseThrow();


        return s!=null?true:false;
    }


    public ResponseEntity<Resource>  getImage(int roomNo){

        Rooms r= roomRepo.findByRoomNO(roomNo).orElseThrow();
        if (r!=null){

            try {
                File file = new File(r.getPicture());

                Resource k= new FileSystemResource(file);

                String s= Files.probeContentType(file.toPath());

       return ResponseEntity.ok().contentType(MediaType.parseMediaType(s)).body(k);
            }
            catch(Exception e){

               ResponseEntity.ok().body(e.getMessage());
            }

        }
        return null;
    }

    public boolean  message(){

        return true;
    }


}
