package com.sanatandigitizers.plustworoomsadmin.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Address {
    @SerializedName("street")
	private String street;
	@SerializedName("city")
	private String city;
	@SerializedName("state")
	private String state;
	@SerializedName("pincode")
	private int pincode;
	@SerializedName("country")
	private String country;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("longitude")
	private String longitude;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStreet() {
		return street;
	}

	public Address setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}

	public String getState() {
		return state;
	}

	public Address setState(String state) {
		this.state = state;
		return this;
	}

	public int getPincode() {
		return pincode;
	}

	public Address setPincode(int pincode) {
		this.pincode = pincode;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public Address setCountry(String country) {
		this.country = country;
		return this;
	}
	public String toString(){
		return street+", "+city+", "+state;
	}



}
