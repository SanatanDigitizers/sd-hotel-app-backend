package com.sanatandigitizers.plustworoomsadmin.codec;

public enum BookingStatus {
  
	 NA(0), ACTIVE(1), ACKNOWLEDGED(2), NOT_REACHED(3), CANCELLED(4);
	
	private int code;
	private BookingStatus(int id) {
		this.code = id;
	}
	
	public int getCode() {
		return code;
	}
	
	public static BookingStatus getValue(int code) {
		switch(code) {
		case 1 : return ACTIVE;
		case 2 : return ACKNOWLEDGED;
		case 3 : return NOT_REACHED;
		case 4 : return CANCELLED;
		default : return NA; 
		}
	}
}
