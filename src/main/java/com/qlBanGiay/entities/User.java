package com.qlBanGiay.entities;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	@NotEmpty(message = "User name is required")
	private String userName;
	
	@Column(name = "password")
	@NotEmpty(message = "Password is required")
	private String passWord;
	
	@Column(name = "enabled", columnDefinition = "default 1")
	private Boolean enabled;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "gender")
	private Boolean gender;
	
	@Column(name = "birthday", columnDefinition = "date")
	private Date birthday;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	@NotEmpty(message = "Email is required")
	@Email
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<UserRole> userRoles;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	

	public User(Long id, String userName, String passWord, Boolean enabled, String fullName, Boolean gender,
			Date birthday, String address, String email, String telephone, Set<UserRole> userRoles) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.enabled = enabled;
		this.fullName = fullName;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.userRoles = userRoles;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}
