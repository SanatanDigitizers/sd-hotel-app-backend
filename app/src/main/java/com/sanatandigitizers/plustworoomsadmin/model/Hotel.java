package com.sanatandigitizers.plustworoomsadmin.model;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import java.util.List;
@Parcel
public class Hotel {
	@SerializedName("ratings")
	private double ratings;
	@SerializedName("imageLink")
	private String imageLink;
	@SerializedName("id")
	private int id;
	@SerializedName("password")
	private String password;
	@SerializedName("name")
	private String name;
	@SerializedName("contactPerson")
	private String contactPerson;
	@SerializedName("email")
	private String email;
	@SerializedName("countryCode")
	private String countryCode;
	@SerializedName("phone1")
	private long phone1;
	@SerializedName("phone2")
	private long phone2;
	@SerializedName("address")
	private Address address;
	@SerializedName("description")
	private String description;
	@SerializedName("images")
	private List<HotelImage> images;
	@SerializedName("rooms")
	private List<Room>rooms;
	@SerializedName("terms")
	private String terms;
	@SerializedName("locality")
	private String locality;

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public List<HotelImage> getImages() {
		return images;
	}

	public void setImages(List<HotelImage> images) {
		this.images = images;
	}



	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public String getIdString() {
		return String.format("%04d", id);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getPhone1() {
		return phone1;
	}
	public void setPhone1(long phone1) {
		this.phone1 = phone1;
	}

	public long getPhone2() {
		return phone2;
	}
	public void setPhone2(long phone2) {
		this.phone2 = phone2;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public String toString() {
		return name;
	}

}
