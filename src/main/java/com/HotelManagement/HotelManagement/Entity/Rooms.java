package com.HotelManagement.HotelManagement.Entity;

import jakarta.persistence.*;



@Entity
@Table(name = "roomTable")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "room_no" ,unique = true)
    private int roomNO;

    @Column(name = "bed")
    private int bed ;
    @Column(name = "features")
    private String features;

    @Column(name =  "picture")
    private String picture;

    @Column(name = "price" ,nullable = false)
    private int price ;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(int roomNO) {
        this.roomNO = roomNO;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Rooms(int id, int roomNO, int bed, String features, String picture) {
        this.id = id;
        this.roomNO = roomNO;
        this.bed = bed;
        this.features = features;
        this.picture = picture;
    }
    
    public  Rooms(){

    }


}
