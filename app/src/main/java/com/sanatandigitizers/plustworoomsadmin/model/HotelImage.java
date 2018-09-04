package com.sanatandigitizers.plustworoomsadmin.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class HotelImage {

    @SerializedName("id")
    private int id;
    @SerializedName("url")
    private String url;
    @SerializedName("hotel")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
