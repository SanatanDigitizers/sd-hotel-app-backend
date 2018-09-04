package com.sanatandigitizers.plustworoomsadmin.networkUtil;

import com.sanatandigitizers.plustworoomsadmin.model.Booking;
import com.sanatandigitizers.plustworoomsadmin.model.Hotel;
import com.sanatandigitizers.plustworoomsadmin.model.Room;
import com.sanatandigitizers.plustworoomsadmin.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SDMangaerApi {
    @POST("/hotels")
    public Call<Hotel> postHotelData(@Body Hotel data);

    @POST("/rooms")
    public Call<Room> postHotelData(@Body Room data);

    @GET("/hotels")
    public Call<List<Hotel>> getAllHotel();

    @GET("/rooms")
    public Call<List<Room>> getAllRooms();

    @GET("/room/bookings")
    public Call<List<Booking>> getAllBookings();

    @GET("/users")
    public Call<List<User>> getAllUsers();

    @GET("/hotels/{id}")
    public Call<Hotel> getHotel(@Path ("id") int id);




}
