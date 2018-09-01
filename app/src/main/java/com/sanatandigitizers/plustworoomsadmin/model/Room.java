package com.sanatandigitizers.plustworoomsadmin.model;

import com.google.gson.annotations.SerializedName;
import com.sanatandigitizers.plustworoomsadmin.codec.RoomCategory;

import java.util.List;


public class Room {



	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("active")
	private boolean active;
	@SerializedName("hotel")
	private Hotel hotel;
	@SerializedName("category")
	private RoomCategory category;
	@SerializedName("price")
	private double price;
	@SerializedName("dicountInPercentage")
	private boolean dicountInPercentage;
	@SerializedName("discountValue")
	private double discountValue;
	@SerializedName("noOfPersons")
	private int noOfPersons;
	@SerializedName("coupleAllowed")
	private boolean coupleAllowed;
	@SerializedName("familyAllowed")
	private boolean familyAllowed;
	@SerializedName("ac")
	private boolean ac;
	@SerializedName("wifi")
	private boolean wifi;
	@SerializedName("tv")
	private boolean tv;
	@SerializedName("cleanToilet")
	private boolean cleanToilet;
	@SerializedName("service24_7")
	private boolean service24_7;
	@SerializedName("images")
	private List<RoomImage> images;
	@SerializedName("bookings")
	private List<Booking>bookings;

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<RoomImage> getImages() {
		return images;
	}

	public void setImages(List<RoomImage> images) {
		this.images = images;
	}

	public String getIdString() {
		return String.format("%05d", id);
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

	public void setName(String room) {
		this.name = room;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public RoomCategory getCategory() {
		return category;
	}

	public void setCategory(RoomCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public boolean isDicountInPercentage() {
		return dicountInPercentage;
	}

	public void setDicountInPercentage(boolean dicountInPercentage) {
		this.dicountInPercentage = dicountInPercentage;
	}

	public int getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public boolean isCoupleAllowed() {
		return coupleAllowed;
	}

	public void setCoupleAllowed(boolean coupleAllowed) {
		this.coupleAllowed = coupleAllowed;
	}

	public boolean isFamilyAllowed() {
		return familyAllowed;
	}

	public void setFamilyAllowed(boolean familyAllowed) {
		this.familyAllowed = familyAllowed;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isCleanToilet() {
		return cleanToilet;
	}

	public void setCleanToilet(boolean cleanToilet) {
		this.cleanToilet = cleanToilet;
	}

	public boolean isService24_7() {
		return service24_7;
	}

	public void setService24_7(boolean service24_7) {
		this.service24_7 = service24_7;
	}

}