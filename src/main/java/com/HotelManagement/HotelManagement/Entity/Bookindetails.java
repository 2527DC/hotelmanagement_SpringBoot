package com.HotelManagement.HotelManagement.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="bookindetails")
public class Bookindetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "checkin", columnDefinition = "DATE")
    private LocalDate checkin;

    @Column(name="checkout", columnDefinition = "DATE")
    private LocalDate checkout;

    @Column(name="roomNo")
    private int roomNo;

    // Default constructor
    public Bookindetails() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
}
