package com.HotelManagement.HotelManagement.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "roles")
public class  Role {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
    
	@Column(name = "user_role")
	private String  role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public Role( String role) {
		super();

		this.role = role;
	}

	public Role( ) {
		super();


	}

	
}
