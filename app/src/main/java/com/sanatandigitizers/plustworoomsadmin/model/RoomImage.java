package com.sanatandigitizers.plustworoomsadmin.model;


import com.google.gson.annotations.SerializedName;

public class RoomImage {

    @SerializedName("id")
    private int id;
    @SerializedName("url")
    private String url;

    @SerializedName("roomId")
    Room roomId;

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

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }
}
