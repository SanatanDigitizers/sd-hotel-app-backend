package com.sanatandigitizers.plustworoomsadmin.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class BookingReview {

	@SerializedName("booking")
	private Booking booking;
	@SerializedName("time")
	private LocalDateTime time;
	@SerializedName("rating")
	private int rating;
	@SerializedName("reviewText")
	private String reviewText;

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
