package com.sanatandigitizers.plustworoomsadmin.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Invoice {

	@SerializedName("id")
	private int id;
	@SerializedName("booking")
	private Booking booking;
	@SerializedName("date")
	private LocalDate date;
	@SerializedName("price")
	private double price;
	@SerializedName("discountAmount")
	private double discountAmount;
	@SerializedName("gstRate")
	private double gstRate;
	@SerializedName("sac")
	private int sac;
	@SerializedName("clientName")
	private String clientName;
	@SerializedName("clientAddress")
	private String clientAddress;
	@SerializedName("phone")
	private String phone;
	@SerializedName("email")
	private String email;
	@SerializedName("businessTrip")
	private boolean businessTrip;
	@SerializedName("gstin")
	private String gstin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getGstRate() {
		return gstRate;
	}

	public void setGstRate(double gstRate) {
		this.gstRate = gstRate;
	}

	public int getSac() {
		return sac;
	}

	public void setSac(int sac) {
		this.sac = sac;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBusinessTrip() {
		return businessTrip;
	}

	public void setBusinessTrip(boolean businessTrip) {
		this.businessTrip = businessTrip;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
}
