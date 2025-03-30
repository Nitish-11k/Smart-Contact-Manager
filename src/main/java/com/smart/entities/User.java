package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name field can't be empty")
	@Size(min = 5, max = 20, message = "Minimum 5 and Maximum 20 Characters Required")
	private String name;

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$" , message = "Invalid Email")
	@NotBlank(message = "Email Field is Required!!")
	private String email;
	
	private String password;
	private String role;
	private boolean enable;
	@Column(length = 300)
	@Size(min = 10, max = 200, message = "About must be 10- 200 character")
	private String description;
	private String imageUrl;
	
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
	private List<Contact> contact = new ArrayList<>();
	
	
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", enable=" + enable + ", description=" + description + ", imageUrl=" + imageUrl + ", contact="
				+ contact + "]";
	}
	
	
	
}
