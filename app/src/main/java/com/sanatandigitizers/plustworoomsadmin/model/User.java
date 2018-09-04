package com.sanatandigitizers.plustworoomsadmin.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Parcel
public class User {

	@SerializedName("id")
	private int id;
	@SerializedName("active")
	private boolean active;
	@SerializedName("imageLink")
	private String imageLink;
	@SerializedName("name")
	private String name;
	@SerializedName("countryCode")
	private String countryCode;
	@SerializedName("phone")
	private long phone;
	@SerializedName("email")
	private String email;
	@SerializedName("dateOfBirth")
	private LocalDate dateOfBirth;
	@SerializedName("address")
	private Address address;
	@SerializedName("accountCreationTime")
	private LocalDateTime accountCreationTime;
	@SerializedName("gender")
	private String gender;
	@SerializedName("bookings")
	private List<Booking> bookings;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getAccountCreationTime() {
		return accountCreationTime;
	}

	public void setAccountCreationTime(LocalDateTime accountCreationTime) {
		this.accountCreationTime = accountCreationTime;
	}

}