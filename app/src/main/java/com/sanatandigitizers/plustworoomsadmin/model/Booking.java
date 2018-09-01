package com.sanatandigitizers.plustworoomsadmin.model;

import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;
import com.sanatandigitizers.plustworoomsadmin.codec.BookingStatus;


public class Booking {
	
    @SerializedName("id")
	private int id;
	@SerializedName("room")
	private Room room;
	@SerializedName("user")
	private User user;
	@SerializedName("time")
	private LocalDateTime time;
	@SerializedName("fromTime")
	private LocalDateTime fromTime;
	@SerializedName("uptoTime")
	private LocalDateTime uptoTime;
	@SerializedName("bookedFor")
	private String bookedFor;
	@SerializedName("countryCode")
	private String countryCode;
	@SerializedName("phone")
	private long phone;
	@SerializedName("email")
	private String email;
    @SerializedName("specialNote")
	private String specialNote;
	@SerializedName("status")
	private BookingStatus status;
	@SerializedName("discountAmount")
	private double discountAmount;
	@SerializedName("invoice")
	private Invoice invoice;
	@SerializedName("bookingReview")
	private BookingReview bookingReview;

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public BookingReview getBookingReview() {
		return bookingReview;
	}

	public void setBookingReview(BookingReview bookingReview) {
		this.bookingReview = bookingReview;
	}


	public String getIdString() {
		return String.format("%06d", id);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDateTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalDateTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalDateTime getUptoTime() {
		return uptoTime;
	}

	public void setUptoTime(LocalDateTime uptoTime) {
		this.uptoTime = uptoTime;
	}

	public String getBookedFor() {
		return bookedFor;
	}

	public void setBookedFor(String bookedFor) {
		this.bookedFor = bookedFor;
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

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}


}