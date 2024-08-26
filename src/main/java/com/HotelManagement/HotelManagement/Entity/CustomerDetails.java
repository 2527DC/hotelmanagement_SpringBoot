package com.HotelManagement.HotelManagement.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customerDetails")
public class CustomerDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private  long phone;

    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinColumn(name = "custom_id")
    private  Bookindetails bookindetails;

    public Bookindetails getBookindetails() {
        return bookindetails;
    }

    public void setBookindetails(Bookindetails bookindetails) {
        this.bookindetails = bookindetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public CustomerDetails() {
    }
}
