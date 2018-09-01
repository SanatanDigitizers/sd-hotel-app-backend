package com.sanatandigitizers.plustworoomsadmin.codec;

public enum RoomCategory {
  
	 NA(0), ECONOMIC(1), BUDGET(2), BUSINESS(3);
	
	private int code;
	private RoomCategory(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static RoomCategory getValue(int code) {
		switch(code) {
		case 1 : return ECONOMIC;
		case 2 : return BUDGET;
		case 3 : return BUSINESS;
		default : return NA; 
		}
	}
}
