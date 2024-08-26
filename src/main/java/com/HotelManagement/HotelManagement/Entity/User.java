	package com.HotelManagement.HotelManagement.Entity;
	
	import java.util.*;

	
	import jakarta.persistence.*;

	
	@Entity
	@Table(name = "users")
	public class User  {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private  Long id;
	
		@Column(name = "username" ,unique = true)
		private String  username;
	
		@Column(name = "password")
		private String  password;
		
		@Column(name = "email",unique = true)
		private String  email;
	
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name = "role_id")
		private Set<Role> role ;

		@Transient
		private List<Rooms> rooms;
		public List<Rooms> getRooms() {
			return rooms;
		}

		public void setRooms(List<Rooms> rooms) {
			this.rooms = rooms;
		}




	    @Transient
	    private String requestRole;

		public String getRequestRole() {
			return requestRole;
		}

		public void setRequestRole(String requestRole) {
			this.requestRole = requestRole;
		}

		public String getPassword() {
	
			return password;
		}
	
	
		public String getUsername() {

			return  username;
		}
	
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public void setUsername(String username) {
			this.username = username;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public Set<Role> getRole() {


			return role;
		}
	
		public void setRole(Set<Role> role) {
			this.role = role;
		}
		public User() {
			this.role = new HashSet<>();
		}
	}
