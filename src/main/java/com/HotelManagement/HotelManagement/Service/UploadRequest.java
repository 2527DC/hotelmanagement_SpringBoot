package com.HotelManagement.HotelManagement.Service;

import com.HotelManagement.HotelManagement.Entity.Rooms;
import org.springframework.web.multipart.MultipartFile;

public class UploadRequest {

    private Rooms room ;
    private MultipartFile file;

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
